<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/6/29
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8"/>
    <title>markdown</title>
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
</head>
<body>
<div id="test-editormd-view2" style="width: 90%">
                <textarea id="append-test" style="display:none;">
${blog.content}
                </textarea>
</div>
</body>
</html>