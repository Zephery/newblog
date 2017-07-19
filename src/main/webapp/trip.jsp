<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="tripactive" value="active"/>
    <jsp:param name="title" value="旅行|多出门走走"/>
</jsp:include>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/chromagallery.css">
    <style type="text/css">
        .mygallery {
            margin: 0 auto;
        }
    </style>
    <link href="${pageContext.request.contextPath}/js/mousepicture/css/lanrenzhijia.css" type="text/css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/wowslider/engine1/style.css"/>
    <script src="${pageContext.request.contextPath}/js/responsiveslides.min.js"></script>
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
    <script src="js/jingmi/wp-emoji-release.min.js" type="text/javascript" defer=""></script>
    <link rel="stylesheet" id="style-css" href="js/jingmi/jingmistyle.css" type="text/css" media="all">
</head>
<body>
<article style="margin-left: 13px">
    <div class="content" style="margin: 0 auto;width: 90%">
        <div class="chroma-gallery mygallery">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">

        </div>
    </div>

</article>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/chromagallery.pkgd.min.js"></script>
<script type="text/javascript">
    // $(document).ready(function()
    // {
    //     $(".mygallery").chromaGallery();
    // });
    $(document).ready(function(){
        $(".mygallery").chromaGallery
        ({
            color:'#000',
            gridMargin:15,
            maxColumns:5,
            dof:true,
            screenOpacity:0.8
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function(){
        var html="<!--畅言-->\n" +
            "            <!--PC和WAP自适应版-->\n" +
            "            <div id=\"SOHUCS\" sid=\"8888888888888\"></div>\n" +
            "            <script type=\"text/javascript\">\n" +
            "                (function () {\n" +
            "                    var appid = 'cyt4SnwiG';\n" +
            "                    var conf = '9c120d8118078b7ea3a728dc120a28da';\n" +
            "                    var width = window.innerWidth || document.documentElement.clientWidth;\n" +
            "                    if (width < 960) {\n" +
            "                        window.document.write('<script id=\"changyan_mobile_js\" charset=\"utf-8\" type=\"text/javascript\" src=\"https://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=' + appid + '&conf=' + conf + '\"><\\/script>');\n" +
            "                    } else {\n" +
            "                        var loadJs = function (d, a) {\n" +
            "                            var c = document.getElementsByTagName(\"head\")[0] || document.head || document.documentElement;\n" +
            "                            var b = document.createElement(\"script\");\n" +
            "                            b.setAttribute(\"type\", \"text/javascript\");\n" +
            "                            b.setAttribute(\"charset\", \"UTF-8\");\n" +
            "                            b.setAttribute(\"src\", d);\n" +
            "                            if (typeof a === \"function\") {\n" +
            "                                if (window.attachEvent) {\n" +
            "                                    b.onreadystatechange = function () {\n" +
            "                                        var e = b.readyState;\n" +
            "                                        if (e === \"loaded\" || e === \"complete\") {\n" +
            "                                            b.onreadystatechange = null;\n" +
            "                                            a()\n" +
            "                                        }\n" +
            "                                    }\n" +
            "                                } else {\n" +
            "                                    b.onload = a\n" +
            "                                }\n" +
            "                            }\n" +
            "                            c.appendChild(b)\n" +
            "                        };\n" +
            "                        loadJs(\"https://changyan.sohu.com/upload/changyan.js\", function () {\n" +
            "                            window.changyan.api.config({appid: appid, conf: conf})\n" +
            "                        });\n" +
            "                    }\n" +
            "                })();\n" +
            "            <\/script>";
        $(".chrg-content").append(html)
    });
</script>
</body>
</html>
<jsp:include page="foot.jsp"/>