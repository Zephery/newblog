<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8"/>
    <title>笔记</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="http://image.wenzhihuai.com/style.css?ver=201711151452" type="text/css" rel="stylesheet" media="all">
    <link rel="stylesheet" href="http://image.wenzhihuai.com/modifyeditormd.css"/>
    <link rel="stylesheet" href="http://image.wenzhihuai.com/css/monokai.css"/>
    <link rel="stylesheet" href="http://image.wenzhihuai.com/css/pastel-on-dark.css"/>
    <link type="image/x-icon" rel="shortcut icon" href="http://image.wenzhihuai.com/66.jpg"/>
    <style type="text/css">
        .editormd img {
            display: block;
            margin: 0 auto;
        }
    </style>
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
<script src="https://cdn.bootcdn.net/jquery/3.2.1/jquery.min.js"></script>
<script src="http://image.wenzhihuai.com/modifyeditormd.js?var=20180618"></script>
<%--<script src="${pageContext.request.contextPath}/js/modifyeditormd.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/lrz.bundle.js"></script>--%>
<script src="http://image.wenzhihuai.com/lrz.bundle.js"></script>
<script type="text/javascript">
    var testEditor;

    // 时间
    function curentTime() {
        var now = new Date();

        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日

        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
        var ss = now.getSeconds();           //秒
        return year + '' + month + '' + day + '' + hh + '' + mm + '' + ss;
    }

    /**
     *  参考自：http://www.chairis.cn/blog/article/15
     * @param event
     */
    function paste(event) {
        var clipboardData = event.clipboardData;
        var items, item, types;
        if (clipboardData) {
            items = clipboardData.items;
            if (!items) {
                return;
            }
            // 保存在剪贴板中的数据类型
            types = clipboardData.types || [];
            for (var i = 0; i < types.length; i++) {
                if (types[i] === 'Files') {
                    item = items[i];
                    break;
                }
            }
            // 判断是否为图片数据
            if (item && item.kind === 'file' && item.type.match(/^image\//i)) {
                // 读取该图片
                var file = item.getAsFile(),
                    reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    var name = curentTime() + ".png";
                    var domain = parseInt((Math.random() * 10) % 3);
                    var targetURL = '';
                    if (domain == 1) {
                        targetURL = '![](http://image.wenzhihuai.com/images/' + name + ')';
                    } else {
                        targetURL = '![](https://upyuncdn.wenzhihuai.com/' + name + ')';
                    }
                    testEditor.insertValue(targetURL);
                    //前端压缩
                    lrz(reader.result, {width: 1080, quality: 1}).then(function (res) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/uploadImage.do",
                            type: 'post',
                            data: {
                                "base64Data": res.base64,
                                "tempFileName": name,
                                "domain": domain
                            },
                            contentType: 'application/x-www-form-urlencoded;charest=UTF-8',
                            success: function (data) {
                            }
                        })
                    });
                }
            }
        }
    }

    function uploadContent() {
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

    document.addEventListener('paste', function (event) {
        paste(event);
    });

    $(function () {
        setInterval(uploadContent, 60 * 1000);

        testEditor = editormd("test-editormd", {
            theme: (localStorage.theme) ? localStorage.theme : "dark",

            // Preview container theme, added v1.5.0
            // You can also custom css class .editormd-preview-theme-xxxx
            previewTheme: (localStorage.previewTheme) ? localStorage.previewTheme : "dark",

            // Added @v1.5.0 & after version is CodeMirror (editor area) theme
            editorTheme: "monokai",
            // theme : "dark",
            // previewTheme : "dark",
            // editorTheme : "pastel-on-dark",
            // toolbar: false,             //关闭工具栏
            onload: function () {
                this.fullscreen();
            }
        })
    });

    window.onbeforeunload = function (event) {
        uploadContent();
    }
</script>
</body>
</html>