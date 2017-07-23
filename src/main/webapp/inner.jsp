<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/7/21
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" id="style-css" href="js/jingmi/jingmistyle.css" type="text/css" media="all">
    <script src="js/jquery-3.2.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/js/mousepicture/css/lanrenzhijia.css" type="text/css"
          rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/responsiveslides.min.js"></script>
    <style>
        * {
            margin: 0;
        }

        .jq22-container {
            margin-top: 50px;
        }

        #div1 {
            margin: auto;
            position: relative;
        }

        .box {
            float: left;
            padding: 10px;
            border: 1px solid #ccc;
            background: #f7f7f7;
            box-shadow: 0 0 8px #ccc;
        }

        .box:hover {
            box-shadow: 0 0 10px #999;
        }

        .box img {
            width: 240px;
        }
    </style>
    <!--[if IE]>
    <script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body class="home blog hPC">
<div>
    <div class="jq22-container" style="width: 100%">
        <div class="jq22-content bgcolor-3">
            <div id="div1" style="margin: 0 auto">
                <div class="box"><img src="images/thumbs/1.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/2.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/3.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/4.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/5.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/6.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/7.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/8.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/2.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/3.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/4.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/5.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/6.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/7.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/1.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/2.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/3.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/4.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/5.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/6.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/7.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/1.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/2.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/3.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/4.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/5.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/6.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/7.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/1.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/2.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/3.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/4.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/5.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/6.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/8.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/1.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/2.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/3.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/4.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/5.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/6.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/7.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/8.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/1.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/2.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/3.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/4.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/5.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/6.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/7.jpg" alt=""></div>
                <div class="box"><img src="images/thumbs/8.jpg" alt=""></div>
            </div>
        </div>
    </div>
    <script src="js/jquery.waterfall.js"></script>

    <script>
        $("#div1").waterfall({
            itemClass: ".box",
            minColCount: 2,
            spacingHeight: 10,
            resizeable: true,
            ajaxCallback: function (success, end) {
                var data = {
                    "data": [
                        {"src": "3.jpg"}, {"src": "4.jpg"}, {"src": "2.jpg"}, {"src": "5.jpg"}, {"src": "1.jpg"}, {"src": "6.jpg"}
                    ]
                };
                var str = "";
                var templ = '<div class="box" style="opacity:0;filter:alpha(opacity=0);"><div class="pic"><img src="images/thumbs/{{src}}" /></div></div>';

                for (var i = 0; i < data.data.length; i++) {
                    str += templ.replace("{{src}}", data.data[i].src);
                }
                $(str).appendTo($("#div1"));
                success();
                end();
            }
        });
    </script>
    <script type="text/javascript">
        $(function () {
            $("#slider").responsiveSlides({
                auto: true,
                nav: true,
                speed: 500,
                namespace: "callbacks",
                pager: false
            });
        });
    </script>
</div>
</body>
</html>
