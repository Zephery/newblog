import base64
import binascii
import io
import json
import logging
import random
import re
import sys
import time
import urllib
import urllib.parse
from urllib.parse import urlencode

import httplib2
import pymysql
import requests
import rsa

# 设置编码
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf8')


class Client(object):
    def __init__(self, api_key, api_secret, redirect_uri, token=None,
                 username=None, password=None):
        # const define
        self.site = 'https://api.weibo.com/'
        self.authorization_url = self.site + 'oauth2/authorize'
        self.token_url = self.site + 'oauth2/access_token'
        self.api_url = self.site + '2/'

        # init basic info
        self.client_id = api_key
        self.client_secret = api_secret
        self.redirect_uri = redirect_uri

        self.session = requests.session()
        if username and password:
            self.session.auth = username, password

        # activate client directly if given token
        if token:
            self.set_token(token)

    @property
    def authorize_url(self):
        params = {
            'client_id': self.client_id,
            'redirect_uri': self.redirect_uri
        }
        return "{0}?{1}".format(self.authorization_url, urlencode(params))

    @property
    def alive(self):
        if self.expires_at:
            return self.expires_at > time.time()
        else:
            return False

    def set_code(self, authorization_code):
        """Activate client by authorization_code.
        """
        params = {
            'client_id': self.client_id,
            'client_secret': self.client_secret,
            'grant_type': 'authorization_code',
            'code': authorization_code,
            'redirect_uri': self.redirect_uri
        }
        res = requests.post(self.token_url, data=params)
        token = json.loads(res.text)
        self._assert_error(token)

        token[u'expires_at'] = int(time.time()) + int(token.pop(u'expires_in'))
        self.set_token(token)

    def set_token(self, token):
        """Directly activate client by access_token.
        """
        self.token = token

        self.uid = token['uid']
        self.access_token = token['access_token']
        self.expires_at = token['expires_at']

        self.session.params = {'access_token': self.access_token}

    def _assert_error(self, d):
        """Assert if json response is error.
        """
        if 'error_code' in d and 'error' in d:
            raise RuntimeError("{0} {1}".format(
                d.get("error_code", ""), d.get("error", "")))

    def get(self, uri, **kwargs):
        """Request resource by get method.
        """
        url = "{0}{1}.json".format(self.api_url, uri)

        # for username/password client auth
        if self.session.auth:
            kwargs['source'] = self.client_id

        res = json.loads(self.session.get(url, params=kwargs).text)
        self._assert_error(res)
        return res

    def post(self, uri, **kwargs):
        """Request resource by post method.
        """
        url = "{0}{1}.json".format(self.api_url, uri)

        # for username/password client auth
        if self.session.auth:
            kwargs['source'] = self.client_id

        if "pic" not in kwargs:
            res = json.loads(self.session.post(url, data=kwargs).text)
        else:
            files = {"pic": kwargs.pop("pic")}
            res = json.loads(self.session.post(url,
                                               data=kwargs,
                                               files=files).text)
        self._assert_error(res)
        return res


connection = pymysql.connect(host="119.23.46.71", user="root", passwd="helloroot", db="myblog", port=3306,
                             charset="utf8")
cur = connection.cursor()
APP_KEY = '415390189'
APP_SECRET = '958ea2c93dcad4ab45a99098b44b016a'
REDIRECT_URI = 'https://api.weibo.com/oauth2/authorize'
client = Client(APP_KEY, APP_SECRET, REDIRECT_URI)
url = client.authorize_url
'https://api.weibo.com/oauth2/authorize?client_id=415390189&response_type=code&redirect_uri=958ea2c93dcad4ab45a99098b44b016a'


class AppClient:
    def __init__(self):
        self._appKey = APP_KEY  # your app key
        self._appSecret = APP_SECRET  # your app secret
        self._callbackUrl = REDIRECT_URI  # your callback url
        self._account = ''  # your weibo user name (eg.email)
        self._password = ''  # your weibo pwd
        self.AppCli = client
        self._author_url = self.AppCli.authorize_url

    def insert(self, user_id, name, location, url, text, created_at, type):
        try:
            ddd = time.mktime(time.strptime(created_at, "%a %b %d %H:%M:%S +0800 %Y"))
            created_at = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(ddd))
            if len(text) > 1000:
                return
            print(text)
            insert_sql = """insert into myblog.weibo(uid,`name`,location,url,text,created_at,`type`)
 VALUES (%s,%s,%s,%s,%s,%s,%s)"""
            cur.execute(insert_sql, (user_id, name, location, url, text, created_at, int(type)))
            connection.commit()
        except Exception as e:
            print(e)

    def get_code(self, session):  # 使用该函数避免了手动输入code，实现了模拟用户授权后获得code的功能
        conn = httplib2.HTTPSConnectionWithTimeout('api.weibo.com')
        postdict = {"client_id": self._appKey,
                    "redirect_uri": self._callbackUrl,
                    "userId": self._account,
                    "passwd": self._password,
                    "isLoginSina": "0",
                    "action": "submit",
                    "response_type": "code",
                    }
        postdata = urllib.parse.urlencode(postdict)
        session.headers.update(
            {"Referer": self._author_url,
             'Content-Type': 'application/x-www-form-urlencoded',
             'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36'
             }
        )
        res = session.post('https://api.weibo.com/oauth2/authorize', postdata)
        location = res.url
        code = location.split('=')[1]
        conn.close()
        return code

    def getAuthorization(self, session):  # 将上面函数获得的code再发送给新浪认证服务器，返回给客户端access_token和expires_in，有了这两个东西，咱就可以调用api了
        code = self.get_code(session)
        self.AppCli.set_code(code)
        uuid = self.AppCli.uid
        mid_list = []
        first_str = client.post('statuses/share',
                                status=str(random.randrange(1000000)) + 'aafirefst,http://www.wenzhihuai.com/')
        first_weibo = json.dumps(first_str)
        first_weibo = json.loads(first_weibo)
        mid_list.append(first_weibo['mid'])
        time.sleep(random.randint(5, 20))
        print("second weibo")
        second_str = client.post('statuses/share',
                                 status=str(random.randrange(1000000)) + 'aasecfweond,http://www.wenzhihuai.com/')
        second_weibo = json.dumps(second_str)
        second_weibo = json.loads(second_weibo)
        mid_list.append(second_weibo['mid'])
        time.sleep(random.randint(5, 20))
        print("third weibo")
        third_str = client.post('statuses/share',
                                status=str(random.randrange(1000000)) + 'aathigerd,http://www.wenzhihuai.com/')
        third_weibo = json.dumps(third_str)
        third_weibo = json.loads(third_weibo)
        mid_list.append(third_weibo['mid'])
        time.sleep(random.randint(5, 20))
        logging.info(mid_list)
        return mid_list


class WeiBoLogin(object):
    def __init__(self):
        self.user_name = None
        self.pass_word = None
        self.user_uniqueid = None
        self.user_nick = None
        self.session = requests.Session()
        self.session.headers.update(
            {"User-Agent": "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:41.0) Gecko/20100101 Firefox/41.0"})
        self.session.get("http://weibo.com/login.php")
        return

    def login(self, user_name, pass_word):
        self.user_name = user_name
        self.pass_word = pass_word
        self.user_uniqueid = None
        self.user_nick = None

        # get json data
        s_user_name = self.get_username()
        json_data = self.get_json_data(su_value=s_user_name)
        if not json_data:
            return False
        s_pass_word = self.get_password(json_data["servertime"], json_data["nonce"], json_data["pubkey"])

        # make post_data
        post_data = {
            "entry": "account",
            "gateway": "1",
            "from": "",
            "savestate": "30",
            "userticket": "0",
            "vsnf": "1",
            "service": "account",
            "encoding": "UTF-8",
            "pwencode": "rsa2",
            "sr": "1366*768",
            "prelt": "529",
            "url": "http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack",
            "rsakv": json_data["rsakv"],
            "servertime": json_data["servertime"],
            "nonce": json_data["nonce"],
            "su": s_user_name,
            "sp": s_pass_word,
            "returntype": "TEXT",
        }

        # get captcha code
        if json_data["showpin"] == 1:
            url = "http://login.sina.com.cn/cgi/pin.php?r=%d&s=0&p=%s" % (int(time.time()), json_data["pcid"])
            code = requests.session().get(
                "http://www.wenzhihuai.com/baidu/word.do?" + urllib.parse.quote(url)).content
            code = str(code)
            print(code)
            if code is None:
                return
            print(code)
            post_data["pcid"] = json_data["pcid"]
            post_data["door"] = code
        print(post_data)
        # login weibo.com
        login_url_1 = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.18)&_=%d" % int(time.time())
        json_data_1 = self.session.post(login_url_1, data=post_data).json()
        if json_data_1["retcode"] == "0":
            params = {
                "callback": "sinaSSOController.callbackLoginStatus",
                "client": "ssologin.js(v1.4.18)",
                "ticket": json_data_1["ticket"],
                "ssosavestate": int(time.time()),
                "_": int(time.time() * 1000),
            }
            response = self.session.get("https://passport.weibo.com/wbsso/login", params=params)
            json_data_2 = json.loads(re.search(r"\((?P<result>.*)\)", response.text).group("result"))
            if json_data_2["result"] is True:
                self.user_uniqueid = json_data_2["userinfo"]["uniqueid"]
                self.user_nick = json_data_2["userinfo"]["displayname"]
            else:
                logging.warning("WeiBoLogin failed: %s", json_data_2)
            app = AppClient()
            mid_list = app.getAuthorization(self.session)
            # delete a weibo
            self.session.headers.update(
                {"Referer": "http://weibo.com/1925306000/profile?topnav=1&wvr=6&is_all=1",
                 "host": "weibo.com",
                 "Origin": "http://weibo.com"}
            )
            for mid in mid_list:
                data_for_del = {
                    "mid": mid,
                }
                rep = self.session.post('https://weibo.com/aj/mblog/del?ajwvr=6', data=data_for_del)
                logging.info(mid)
                try:
                    time.sleep(random.randint(5, 20))
                except Exception as eee:
                    print(eee)
                print("delete" + mid)

        elif json_data_1["retcode"] == "2070":
            raise Exception("retcode：验证码错误")
        else:
            logging.warning("WeiBoLogin failed: %s", json_data_1)
        return True if self.user_uniqueid and self.user_nick else False

    def get_username(self):
        """
        get legal username
        """
        username_quote = urllib.parse.quote_plus(self.user_name)
        username_base64 = base64.b64encode(username_quote.encode("utf-8"))
        return username_base64.decode("utf-8")

    def get_json_data(self, su_value):
        """
        get the value of "servertime", "nonce", "pubkey", "rsakv" and "showpin", etc
        """
        params = {
            "entry": "weibo",
            "callback": "sinaSSOController.preloginCallBack",
            "rsakt": "mod",
            "checkpin": "1",
            "client": "ssologin.js(v1.4.18)",
            "su": su_value,
            "_": int(time.time() * 1000),
        }
        try:
            response = self.session.get("http://login.sina.com.cn/sso/prelogin.php", params=params)
            json_data = json.loads(re.search(r"\((?P<data>.*)\)", response.text).group("data"))
        except Exception as excep:
            json_data = {}
            logging.error("WeiBoLogin get_json_data error: %s", excep)

        return json_data

    def get_password(self, servertime, nonce, pubkey):
        """
        get legal password
        """
        string = (str(servertime) + "\t" + str(nonce) + "\n" + str(self.pass_word)).encode("utf-8")
        public_key = rsa.PublicKey(int(pubkey, 16), int("10001", 16))
        password = rsa.encrypt(string, public_key)
        password = binascii.b2a_hex(password)
        return password.decode()


if __name__ == '__main__':
    logging.basicConfig(level=logging.DEBUG,
                        format='%(asctime)s %(filename)s[line:%(lineno)d] %(levelname)s %(message)s',
                        datefmt='%a, %d %b %Y %H:%M:%S',
                        filename='myweibo.log',
                        filemode='a')

    weibo = WeiBoLogin()
    weibo.login("w1570631036@sina.com", "wenzhihuai2015.")
