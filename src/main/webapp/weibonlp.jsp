<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/8/19
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="interestactive" value="active"/>
    <jsp:param name="title" value="有点意思"/>
</jsp:include>

<!DOCTYPE html>
<html>
<head>
    <link href="http://image.wenzhihuai.com/blogbootstrap.css?ver=20171019" type="text/css" rel="stylesheet"
          media="all">
    <!--畅言获取评论数，未来有可能变为https-->
    <script type="text/javascript"
            src="http://assets.changyan.sohu.com/upload/plugins/plugins.count.js">
    </script>
    <script src="http://cdn.highcharts.com.cn/highcharts/10.0.0/highcharts.js"></script>
    <style>
        .article-content h1 {
            margin: 20px -20px 20px -20px;
            padding: 10px 20px 9px 10px;
            border-left: 4px solid #00a67c;
            background-color: #fbfbfb
        }
    </style>
    <script type="text/javascript">
        function submitword() {
            var sentence = $("#sentence").val();
            sentence = encodeURIComponent(sentence);
            window.location.href = '${pageContext.request.contextPath}/weibonlp.html?weibo=' + sentence;
        }
    </script>
</head>
<body class="home blog hPC">
<section class="contentcontainer" style="background-color: #FFFFFF">
    <div style="text-align: center">
        <img src="http://image.wenzhihuai.com/201510914124865365367.jpg" style="padding: 0 auto"/>
    </div>
    <div class="input-group"
         style="width: 85%;margin: -5% auto 3% auto;">
        <input type="text" class="form-control input-lg" name="sentence" id="sentence" placeholder="请输入一段句子"
               value="${sentence}">
        <span class="input-group-addon btn btn-primary" onclick="submitword()">情感分类</span>
    </div>
    <c:if test="${kvs!=null}">
        <div>
            <div id="eeecontainer" style="height: 421px;width: 50%;float: left"></div>
            <script>
                $(function () {
                    $('#eeecontainer').highcharts({
                        chart: {
                            plotBackgroundColor: null,
                            plotBorderWidth: null,
                            plotShadow: false
                        },
                        credits: {
                            enabled: false
                        },
                        title: {
                            text: '情感属性'
                        },
                        tooltip: {
                            headerFormat: '{series.name}<br>',
                            pointFormat: '{point.name}: <b>{point.percentage:.2f}%</b>'
                        },
                        plotOptions: {
                            pie: {
                                allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: true,
                                    format: '<b>{point.name}</b>: {point.percentage:.2f} %',
                                    style: {
                                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                    }
                                }
                            }
                        },
                        series: [{
                            type: 'pie',
                            name: '情感倾向占比',
                            data: [
                                <c:forEach var="kv" items="${kvs}">
                                ['${kv.key}', ${kv.value}],
                                </c:forEach>
                            ]
                        }]
                    });
                });
            </script>
            <div style="float: right;width: 30%;margin: 10% 10%">
                <table class="table table-responsive">
                    <tbody>
                    <tr>
                        <th style="text-align: center">所属类别：</th>
                        <td style="text-align: center">${kvs.get(0).key}</td>
                    </tr>
                    <c:forEach var="kv" items="${kvs}">
                        <tr>
                            <th style="text-align: center">${kv.key}</th>
                            <td style="text-align: center">${kv.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
    <table class="table table-bordered" style="width: 90%;margin: 0 auto;word-break:break-all; word-wrap: break-word;">
        <thead>
        <tr>
            <th style="width: 5%;text-align: center">序号</th>
            <th style="text-align: center">博主</th>
            <th style="text-align: center">微博内容</th>
            <th style="width: 5%;text-align: center">类型</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="weibo" items="${weibos}" varStatus="s">
            <tr>
                <td style="text-align: center">${s.index+1}</td>
                <td>${weibo.name}</td>
                <td>${weibo.text}</td>
                <td style="text-align: center">${weibo.typename}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
</section>
<br>
<%@include file="foot.jsp" %>
</body>
</html>