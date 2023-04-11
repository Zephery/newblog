<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/8/5
  Time: 0:31
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
    <!--畅言获取评论数，未来有可能变为https-->
    <script type="text/javascript"
            src="http://assets.changyan.sohu.com/upload/plugins/plugins.count.js">
    </script>
    <style>
        .article-content h1 {
            margin: 20px -20px 20px -20px;
            padding: 10px 20px 9px 10px;
            border-left: 4px solid #00a67c;
            background-color: #fbfbfb
        }
    </style>
</head>
<body class="home blog hPC">
<section class="contentcontainer">
<h1>Hello world</h1>
</section>
<%@include file="foot.jsp"%>
</body>
</html>