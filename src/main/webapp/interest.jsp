<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/8/5
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="interestactive" value="active"/>
    <jsp:param name="title" value="有点意思"/>
</jsp:include>

<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/js/mousepicture/css/lanrenzhijia.css" type="text/css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"/>
    <meta charset="UTF-8">
    <link rel="stylesheet" id="style-css" href="http://ohlrxdl4p.bkt.clouddn.com/jingmistyle-1503806575354.css" type="text/css" media="all">
    <!--畅言获取评论数，未来有可能变为https-->
    <script type="text/javascript"
            src="http://assets.changyan.sohu.com/upload/plugins/plugins.count.js">
    </script>
    <link rel="stylesheet" href="http://ohlrxdl4p.bkt.clouddn.com/editormd.preview.css"/>
    <script src="https://cdn.bootcss.com/marked/0.3.6/marked.js"></script>
    <script src="https://cdn.bootcss.com/prettify/r224/prettify.min.js"></script>
    <script src="https://cdn.bootcss.com/raphael/2.2.7/raphael.min.js"></script>
    <script src="https://cdn.bootcss.com/js-sequence-diagrams/1.0.6/sequence-diagram-min.js"></script>
    <script src="http://ohlrxdl4p.bkt.clouddn.com/editormd.js"></script>
    <script type="text/javascript">
        $(function () {
            editormd.markdownToHTML("test-editormd-view2", {
                htmlDecode: "style,script,iframe",  // you can filter tags decode
                emoji: true,
                taskList: true,
                tex: true,  // 默认不解析
                flowChart: true,  // 默认不解析
                sequenceDiagram: true   // 默认不解析
            });
        });
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
</body>
</html>
<jsp:include page="foot.jsp"/>

