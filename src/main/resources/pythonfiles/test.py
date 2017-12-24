import urllib.parse

import requests

url = "http://login.sina.com.cn/cgi/pin.php"
url = urllib.parse.quote(url)
print(url)
code = requests.session().get(
    "http://localhost:9090/baidu/word.do?url=" + url).content
code = str(code)
print(code)
