<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2018/1/28
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="openactive" value="active"/>
    <jsp:param name="title" value="ELK系统"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>ELK系统</title>
    <script src="https://cdn.bootcss.com/highcharts/5.0.14/highcharts.js"></script>
    <script src="https://cdn.bootcss.com/echarts/3.6.2/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
    <script src="https://cdn.bootcss.com/sockjs-client/1.1.4/sockjs.js"></script>
    <!--不要导入jquery，扇形图有冲突-->
    <style>
        th {
            text-align: center;
        }

        td {
            text-align: center;
        }
    </style>
</head>
<body class="home blog hPC">
<section class="container">
    <div id="page-inner">
        <iframe src="http://es.wenzhihuai.com/app/kibana#/dashboard/08611960-040c-11e8-a694-c9092c6a2120?embed=true&_g=(refreshInterval%3A('%24%24hashKey'%3A'object%3A601'%2Cdisplay%3A'5%20seconds'%2Cpause%3A!f%2Csection%3A1%2Cvalue%3A5000)%2Ctime%3A(from%3Anow-30m%2Cinterval%3Aauto%2Cmode%3Aquick%2Ctimezone%3AAsia%2FShanghai%2Cto%3Anow))" height="2530px" width="100%"></iframe>
    </div>
</section>
<%@include file="foot.jsp" %>
</body>
</html>