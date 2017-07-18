# 百度统计API的使用
在搭建自己博客的时候，希望自己能有个日志系统，能够看到PV、UV等信息，同时自己也搭建了个ELK系统，可惜服务器配置太低（1GHZ+1G内存），根本运行不起来。只能使用第三方的日志统计了，由于之前用过一点点的百度统计，所以本平台使用百度统计来进行日志分析。
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;![](http://ohlrxdl4p.bkt.clouddn.com/baidutongji3333.png?imageView2/2/w/300)
但是百度不提供数据图线给开发者使用，只能通过其TONGJI API对数据进行抓取。百度统计提供了两个接口（1.用户站点数据；2.站点报告数据），这里使用第二个接口来获取数据。
## 1.请求参数
链接：https://api.baidu.com/json/tongji/v1/ReportService/getData
所需参数（必须）：
|  参数名称 | 参数类型  |描述   |
| ------------ | ------------ | ------------ |
| site_id  | uint  | 站点ID|
|method|string|要查询的报告|
|start_date|string|查询起始时间|
|end_date|string|查询结束时间|
|metrics|string|自定义指标|

详细的官方报告请访问官网[TongjiApi](https://tongji.baidu.com/dataapi/file/TongjiApiFile.pdf "百度统计")

## 2.数据获取
官方同时提供了PHP和Java的版本，对于一个简简单单的获取数据的过程来说甚是繁琐，此处使用python来进行数据抓取最近七天的地域访问。
**2.1 时间的获取**
python中对于时间的处理找不到很好的库，所以使用了datetime，然后变成字符串。
```python
today = datetime.date.today()
yesterday = today - datetime.timedelta(days=1)
fifteenago = today - datetime.timedelta(days=7)
end, start = str(yesterday).replace("-", ""), str(fifteenago).replace("-", "")
```
**2.2 构建请求包**
说明：siteId可以根据个人百度统计的链接获取，也可以使用Tongji API的第一个接口列表获取用户的站点列表。
```python
body={
"header": {
"account_type": 1,
"password": "password",
"token": "开通统计API的token",
"username": "用户名"
},
"body": {
"siteId": "域名id",
"method": "visit/district/a", # 地域访问
"start_date": start,
"end_date": end,
"metrics": "pv_count,visitor_count,avg_visit_time" #所需要的指标
}
}
```
**2.3 解析返回来的json**
获取之后的json主要是在items中，其顺序为[[省份],[pv_count,visitor_count,avg_visit_time]]，但是在java中使用bean来封装的时候设计原则一般是“地名、pv_count、visitor_count、avg_visit_time”，所以需要经过一定的处理。
```json
{
"body": {
"data": [
{
"result": {
"offset": 0,
"total": 3,
"items": [
[
[
{
"area": "province,4",
"name": "广东"
}
],
[
{
"area": "province,28",
"name": "四川"
}
],
[
{
"area": "province,19",
"name": "江苏"
}
]
],
[
[
649,
7,
2684
],
[
2,
2,
76
],
[
1,
1,
3
]
],
...
...
}
```
对json进行处理
```python
the_page = response.read()
result=json.loads(the_page.decode("utf-8"))
print(json.dumps(result))
base=result["body"]["data"][0]["result"]["items"]
source=[]
for item in base[0]:
source.append(item[0]['name'])
count=0
detail=[]
for item in base[1]:
tojson={}
tojson['name']=source[count]
tojson['pv_count']=item[0]
tojson['pv_ratio']=item[1]
tojson['visitor_count']=item[2]
count=count+1
detail.append(tojson)
```
**2.4 完整代码**
```python
import urllib.request
import urllib.parse
import json
import time
import datetime

start_date = time.strftime("%Y%m%d", time.localtime())
today = datetime.date.today()
yesterday = today - datetime.timedelta(days=1)
fifteenago = today - datetime.timedelta(days=7)
end, start = str(yesterday).replace("-", ""), str(fifteenago).replace("-", "")
base_url = "https://api.baidu.com/json/tongji/v1/ReportService/getData"
body = {"header": {"account_type": 1, "password": "", "token": "",
"username": ""},
"body": {"siteId": , "method": "visit/district/a", "start_date": start, "end_date": end,
"metrics": "pv_count,visitor_count,avg_visit_time"}}
data = bytes(json.dumps(body), 'utf8')
req = urllib.request.Request(base_url, data)
response = urllib.request.urlopen(req)
the_page = response.read()
result=json.loads(the_page.decode("utf-8"))
base=result["body"]["data"][0]["result"]["items"]
source=[]
for item in base[0]:
source.append(item[0]['name'])
count=0
detail=[]
for item in base[1]:
tojson={}
tojson['name']=source[count]
tojson['pv_count']=item[0]
tojson['pv_ratio']=item[1]
tojson['visitor_count']=item[2]
count=count+1
detail.append(tojson)
```
**2.5 结果**
```json
[{'pv_count': 649, 'pv_ratio': 7, 'visitor_count': 2684, 'name': '广东'}, {'pv_count': 2, 'pv_ratio': 2, 'visitor_count': 76, 'name': '四川'}, {'pv_count': 1, 'pv_ratio': 1, 'visitor_count': 3, 'name': '江苏'}]
```
经过上述的处理之后，将json存入Redis中，使用的时候用JavaBean来封装，就能够很好的把数据传入highcharts、echarts等图表。
## 3.echarts展示地域图
展示**地域图**的时候需要获取下载两个文件，[china.js](http://echarts.baidu.com/download-map.html)（其提供了js和json，这里使用的js），[echarts.js](http://echarts.baidu.com/download.html)
部分代码：
```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>日志系统</title>
    <script src="js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" id="style-css" href="js/jingmi/jingmistyle.css" type="text/css" media="all">
    <script type="text/javascript" src="js/echarts.js"></script>
    <script type="text/javascript" src="js/china.js"></script>
</head>
<body>
<div id="diyu" style="width: 100%;height: 421px"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('diyu'));
    option = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left'
        },
        visualMap: {
            min: 0,
            max:${diyumax},
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: '访问量',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: [
                    <c:forEach var="diyu" items="${diyu}">
                    {name: '${diyu.name}', value: ${to.pv_count}},
                    </c:forEach>
                ]
            }
        ]
    };
    myChart.setOption(option);
</script>
</body>
</html>
<jsp:include page="foot.jsp"/>
```
结果如下：
![](http://ohlrxdl4p.bkt.clouddn.com/echartsfawe.png)