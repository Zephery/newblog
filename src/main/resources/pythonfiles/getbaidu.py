# encoding: utf-8
import datetime
import json
import logging
import urllib.parse
import urllib.request

import redis
import time

# 日期开始
start_date = time.strftime("%Y%m%d", time.localtime())
today = datetime.date.today()
yesterday = today - datetime.timedelta(days=1)
fifteenago = today - datetime.timedelta(days=11)
print(str(yesterday).replace("-", ""), str(fifteenago).replace("-", ""))
end, start = str(yesterday).replace("-", ""), str(fifteenago).replace("-", "")
# 日期结束
base_url = "https://api.baidu.com/json/tongji/v1/ReportService/getData"
pool = redis.ConnectionPool(host='106.52.188.206', port=31857, password='0CVF5Dsh79')  # TODO redis地址
r = redis.Redis(connection_pool=pool)
# logging
logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(filename)s[line:%(lineno)d] %(levelname)s %(message)s',
                    datefmt='%a, %d %b %Y %H:%M:%S',
                    filename='myapp.log',
                    filemode='w')
console = logging.StreamHandler()
console.setLevel(logging.INFO)
formatter = logging.Formatter('%(name)-12s: %(levelname)-8s %(message)s')
console.setFormatter(formatter)
logging.getLogger('').addHandler(console)


class Baidu(object):
    def __init__(self, siteId, username, password, token):
        self.siteId = siteId
        self.username = username
        self.password = password
        self.token = token

    def getresult(self, start_date, end_date, method, metrics, **kw):
        base_url = "https://api.baidu.com/json/tongji/v1/ReportService/getData"
        body = {"header": {"account_type": 1, "password": self.password, "token": self.token,
                           "username": self.username},
                "body": {"siteId": self.siteId, "method": method, "start_date": start_date,
                         "end_date": end_date, "metrics": metrics}}
        for key in kw:
            body['body'][key] = kw[key]
        data = bytes(json.dumps(body), 'utf8')
        req = urllib.request.Request(base_url, data)
        response = urllib.request.urlopen(req)
        the_page = response.read()
        logging.info("从百度返回结果")
        return the_page.decode("utf-8")

    def getPvUvAvgTime(self):  # 获取PV、UV、AvgTime
        result = self.getresult(start, end, "overview/getTimeTrendRpt",
                                "pv_count,visitor_count,ip_count,bounce_ratio,avg_visit_time")
        result = json.loads(result)["body"]["data"][0]["result"]["items"]
        data = result[0]
        daterange = [str(x[0]).replace("2017/", "") for x in data]
        pv_count = [x[0] if x[0] != '--' else 0 for x in result[1]]
        visitor_count = [x[1] if x[1] != '--'  else 0 for x in result[1]]
        ip_count = [x[2] if x[2] != '--' else 0 for x in result[1]]
        bounce_ratio = [x[3] if x[3] != '--' else 0 for x in result[1]]
        avg_visit_time = [round(x[4] / 60, 2) if x[4] != '--' else 0 for x in result[1]]
        pv_sum = sum(pv_count)
        uv_sum = sum(ip_count)
        r.set("pv_sum", pv_sum)
        r.set("uv_sum", uv_sum)
        r.set("daterange", str(daterange))
        r.set("pv_count", str(pv_count))
        r.set("visitor_count", str(visitor_count))
        r.set("ip_count", str(ip_count))
        r.set("bounce_ratio", str(bounce_ratio))
        r.set("avg_visit_time", str(avg_visit_time))
        logging.info("PV，UV，AvgTime")

    def getRukouYeMian(self):  # 前十入口页面
        result = self.getresult(start, end, "source/all/a",
                                "pv_count,visitor_count,avg_visit_time", viewType='visitor')
        data = json.loads(result)["body"]["data"][0]["result"]["items"]
        name = [item[0]['name'] for item in data[0]]
        count = 0
        tojson = []
        for item in data[1]:
            temp = {}
            temp["name"] = name[count]
            temp["pv_count"] = item[0]
            temp["visitor_count"] = item[1]
            temp["average_stay_time"] = item[2]
            tojson.append(temp)
            count = count + 1
        r.set("rukouyemian", json.dumps(tojson[:5]))
        logging.info("前十入口页面")

    def getAllSource(self):  # 获取所有来源
        result = self.getresult(start, end, "source/all/a",
                                "pv_count,pv_ratio,visitor_count")
        base = json.loads(result)["body"]["data"][0]["result"]["items"]
        source = [item[0]['name'] for item in base[0]]
        count = 0
        detail = []
        for item in base[1]:
            tojson = {}
            tojson['name'] = source[count]
            tojson['pv_count'] = item[0]
            tojson['pv_ratio'] = item[1]
            tojson['visitor_count'] = item[2]
            count = count + 1
            detail.append(tojson)
        r.set("source", json.dumps(detail))
        logging.info("所有来源")

    def getDiYu(self):  # 地域
        result = self.getresult(start, end, "visit/district/a",
                                "pv_count,visitor_count,avg_visit_time")
        base = json.loads(result)["body"]["data"][0]["result"]["items"]
        source = [item[0]['name'] for item in base[0]]
        count = 0
        detail = []
        for item in base[1]:
            tojson = {}
            tojson['name'] = source[count]
            tojson['pv_count'] = item[0]
            tojson['pv_ratio'] = item[1]
            tojson['visitor_count'] = item[2]
            count = count + 1
            detail.append(tojson)
        r.set("diyu", json.dumps(detail))
        logging.info("地域")

    def getTopTen(self):  # 前十
        result = self.getresult(start, end, "visit/toppage/a",
                                "pv_count,visitor_count,average_stay_time")
        base = json.loads(result)["body"]["data"][0]["result"]["items"]
        name = [item[0]['name'] for item in base[0]]
        count = 0
        tojson = []
        for item in base[1]:
            temp = {}
            temp["name"] = name[count]
            temp["pv_count"] = item[0]
            temp["visitor_count"] = item[1]
            temp["average_stay_time"] = item[2]
            tojson.append(temp)
            count = count + 1
        r.set("top_ten", json.dumps(tojson[:5]))
        logging.info("前十访问页面")


if __name__ == '__main__':
    bd = Baidu(10879516, "ZepheryWen", "wenzhihuai2017", "bad4fda9a063476f2976c24416ec02ad")
    bd.getPvUvAvgTime()
    bd.getRukouYeMian()
    bd.getAllSource()
    bd.getDiYu()
    bd.getTopTen()
    print("finish")
