<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2018/2/26
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="myresumeactive" value="active"/>
    <jsp:param name="title" value="我的简历"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>我的简历</title>
</head>
<body class="home blog hPC">
<section class="container">
    <img src="${pageContext.request.contextPath}/images/0001.jpg"/>
</section>
<%@include file="foot.jsp" %>
</body>
</html>