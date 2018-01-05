<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8"/>
    <title>周报</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="http://image.wenzhihuai.com/style.css?ver=201711151452" type="text/css" rel="stylesheet" media="all">
    <link rel="stylesheet" href="http://image.wenzhihuai.com/modifyeditormd.css"/>
    <link type="image/x-icon" rel="shortcut icon" href="http://image.wenzhihuai.com/66.jpg"/>
</head>
<body>
<div id="layout">
    <div class="editormd" id="test-editormd">
        <!--前面不能有空格-->
        <textarea class="editormd-markdown-textarea" name="content" id="content">
${content}</textarea>
        <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
        <textarea class="editormd-html-textarea" name="htmlcontent"></textarea>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="http://image.wenzhihuai.com/modifyeditormd.js?var=20180102"></script>
<script type="text/javascript">
    var testEditor;

    $(function () {
        testEditor = editormd("test-editormd", {
            // theme : "dark",
            // previewTheme : "dark",
            // editorTheme : "pastel-on-dark",
            //toolbar  : false,             //关闭工具栏
            onload: function () {
                this.fullscreen();
            }

            /*
            // or
            testEditor = editormd({
                id      : "test-editormd",
                width   : "90%",
                height  : 640,
                path    : "../lib/"
            });
            */
        })
    });

    window.onbeforeunload = function (event) {
        var content = $("#content").text();
        $.ajax({
            type: "POST",
            url: "/savezhoubao.do",
            data: {content: content},
            dataType: "json",
            success: function (data) {
            }
        })
    }
</script>
</body>
</html>