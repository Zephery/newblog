<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/7/21
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="aboutmeactive" value="active"/>
    <jsp:param name="title" value="关于我"/>
</jsp:include>

<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/js/mousepicture/css/lanrenzhijia.css" type="text/css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"/>
    <meta charset="UTF-8">
    <link rel="stylesheet" id="style-css" href="js/jingmi/jingmistyle.css" type="text/css" media="all">
    <!--畅言获取评论数，未来有可能变为https-->
    <script type="text/javascript"
            src="http://assets.changyan.sohu.com/upload/plugins/plugins.count.js">
    </script>
    <link rel="stylesheet" href="js/mdeditor/css/editormd.preview.css"/>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/mdeditor/lib/marked.min.js"></script>
    <script src="js/mdeditor/lib/prettify.min.js"></script>
    <script src="js/mdeditor/lib/raphael.min.js"></script>
    <script src="js/mdeditor/lib/underscore.min.js"></script>
    <script src="js/mdeditor/lib/sequence-diagram.min.js"></script>
    <script src="js/mdeditor/lib/flowchart.min.js"></script>
    <script src="js/mdeditor/lib/jquery.flowchart.min.js"></script>
    <script src="js/mdeditor/editormd.js"></script>
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

</section>
</body>
</html>
<jsp:include page="foot.jsp"/>

