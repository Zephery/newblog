<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/6/23
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="donateactive" value="active"/>
    <jsp:param name="title" value="捐赠"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>

    <script src="http://image.wenzhihuai.com/responsiveslides.min.js?ver=20171017"></script>
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
</head>
<body class="home blog hPC">
<section class="contentcontainer">
    <div class="pagewrapper clearfix">
        <header class="pageheader clearfix">
            <h1 class="pull-left">
                <a href="http://www.wenzhihuai.com/donate.html">赞助作者</a>
            </h1>
            <div class="pull-right">
                <!-- JiaThis Button BEGIN -->
                <div class="jiathis_style_32x32">
                    <a class="jiathis_button_qzone"></a>
                    <a class="jiathis_button_tsina"></a>
                    <a class="jiathis_button_tqq"></a>
                    <a class="jiathis_button_weixin"></a>
                    <a class="jiathis_button_renren"></a>
                    <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis"
                       target="_blank"></a>
                </div>
                <script type="text/javascript" src="http://v3.jiathis.com/code_mini/jia.js"
                        charset="utf-8"></script>
                <!-- JiaThis Button END -->
            </div>
        </header>
        <div class="article-content">
            <div style="text-indent:0px;">
                <p></p>
                <p style="text-align: center;">
                </p>
                <h2 style="line-height: 30px;text-align: center;margin: auto">
                    如果您喜欢我的文章，感觉我的文章对您有帮助，不妨动动您的金手指给予小额赞助，予人玫瑰，手有余香，不胜感激。</h2>
                <p></p>
                <div class="donate">
                    <div class="zhifubao">
                        <h3 style="text-align: center;">手机支付宝扫一扫</h3>
                        <img class="alignnone size-full wp-image-1100"
                             src="http://image.wenzhihuai.com/zhifubao.jpg"
                             alt="1424922459123" width="270px" height="270px"
                             style="width: 270px;">
                    </div>
                    <div class="wechat">
                        <h3 style="text-align: center;">使用微信扫一扫</h3>
                        <img class="alignnone size-full wp-image-1100" src="http://image.wenzhihuai.com/weixin.png"
                             alt="1424922459123" width="270px" height="270px"
                             style="width: 270px;">
                    </div>
                </div>
            </div>
            <a name="comments"></a>
            <!--畅言-->
            <!--PC和WAP自适应版-->
            <div id="SOHUCS" sid="8888888888888" style="margin-top: 40px"></div>
            <script type="text/javascript">
                (function () {
                    var appid = 'cyt4SnwiG';
                    var conf = '9c120d8118078b7ea3a728dc120a28da';
                    var width = window.innerWidth || document.documentElement.clientWidth;
                    if (width < 960) {
                        window.document.write('<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" src="https://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=' + appid + '&conf=' + conf + '"><\/script>');
                    } else {
                        var loadJs = function (d, a) {
                            var c = document.getElementsByTagName("head")[0] || document.head || document.documentElement;
                            var b = document.createElement("script");
                            b.setAttribute("type", "text/javascript");
                            b.setAttribute("charset", "UTF-8");
                            b.setAttribute("src", d);
                            if (typeof a === "function") {
                                if (window.attachEvent) {
                                    b.onreadystatechange = function () {
                                        var e = b.readyState;
                                        if (e === "loaded" || e === "complete") {
                                            b.onreadystatechange = null;
                                            a()
                                        }
                                    }
                                } else {
                                    b.onload = a
                                }
                            }
                            c.appendChild(b)
                        };
                        loadJs("https://changyan.sohu.com/upload/changyan.js", function () {
                            window.changyan.api.config({appid: appid, conf: conf})
                        });
                    }
                })();
            </script>
        </div>
    </div>
</section>
<%@include file="foot.jsp"%>
</body>
</html>