<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>写博客页面</title>
    <link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/images/66.jpg"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/mdeditor/css/editormd.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mdeditor/editormd.js"></script>
    <%--<script type="text/javascript"--%>
            <%--src="${pageContext.request.contextPath}/js/html2markdown/markdown_dom_parser.js"></script>--%>
    <%--<script type="text/javascript"--%>
            <%--src="${pageContext.request.contextPath}/js/html2markdown/html2markdown.js"></script>--%>
    <script type="text/javascript">
        $(function () {
            editormd("test-editormd", {
                width: "90%",
                height: 640,
                syncScrolling: "single",
                //你的lib目录的路径，
                path: "${pageContext.request.contextPath}/js/mdeditor/lib/",
                //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
                saveHTMLToTextarea: true,
                imageUpload: true,
                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL: "${pageContext.request.contextPath}/admin/uploadfile.do"
            });
        })
    </script>
</head>
<body>
    <div class="editormd" id="test-editormd">
        <!--前面不能有空格-->
        <textarea class="editormd-markdown-textarea" name="mdcontent" id="mdcontent" style="display: none">
${str}</textarea>
        <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
        <textarea class="editormd-html-textarea" name="htmlcontent"></textarea>
    </div>
</body>
</html>