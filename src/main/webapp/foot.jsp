<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/6/18
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" id="style-css" href="${pageContext.request.contextPath}/css/jingmi.css"
          type="text/css" media="all">
</head>
<body>
<footer class="footer">
    <div class="footer-inner">
        <div class="copyright pull-left">
            <a href="${pageContext.request.contextPath}" title="Zephery">Zephery</a> 版权所有丨采用<a
                href="http://yusi123.com/"> 欲思 </a>主题丨基于 Spring SpringMVC Mybatis 构建 © <script>
            var date = new Date();  //创建对象
            var y = date.getFullYear();     //获取年份
            document.write(y);
            </script> 丨托管于
            <a rel="nofollow" target="_blank" href="https://www.qcloud.com/">腾讯云主机</a>
            &amp; <a rel="nofollow" target="_blank" href="http://www.qiniu.com/">七牛云存储 </a>丨
            <a rel="nofollow" target="_blank" href="http://www.miitbeian.gov.cn">粤ICP备17092242号-1</a>
        </div>
    </div>
</footer>
</body>
</html>
