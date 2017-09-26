<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/6/23
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="boardactive" value="active"/>
    <jsp:param name="title" value="留言板"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/js/mousepicture/css/lanrenzhijia.css" type="text/css"
          rel="stylesheet"/>
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
    <meta charset="UTF-8">
    <link rel="stylesheet" id="style-css" href="http://ohlrxdl4p.bkt.clouddn.com/jingmistyle-1503806575354.css" type="text/css" media="all">
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?e580b8db831811a4aaf4a8f3e30034dc";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</head>
<body class="home blog hPC">
<section class="contentcontainer">
    <div class="pagewrapper clearfix">
        <header class="pageheader clearfix">
            <h1 class="pull-left">
                <a href="http://www.wenzhihuai.com/board.html">给我留言</a>
            </h1>
            <div class="pull-right"><!-- 百度分享 -->
                <span class="action action-share bdsharebuttonbox bdshare-button-style0-24"
                      data-bd-bind="1498225366914"><i class="fa fa-share-alt"></i>分享<div
                        class="action-popover"><div class="popover top in"><div class="arrow"></div><div
                        class="popover-content"><a href="#" class="sinaweibo fa fa-weibo" data-cmd="tsina"
                                                   title="分享到新浪微博"></a><a href="#" class="bds_qzone fa fa-star"
                                                                          data-cmd="qzone" title="分享到QQ空间"></a><a
                        href="#" class="tencentweibo fa fa-tencent-weibo" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#"
                                                                                                                class="qq fa fa-qq"
                                                                                                                data-cmd="sqq"
                                                                                                                title="分享到QQ好友"></a><a
                        href="#" class="bds_renren fa fa-renren" data-cmd="renren" title="分享到人人网"></a><a href="#"
                                                                                                         class="bds_weixin fa fa-weixin"
                                                                                                         data-cmd="weixin"
                                                                                                         title="分享到微信"></a><a
                        href="#" class="bds_more fa fa-ellipsis-h" data-cmd="more"></a></div></div></div></span>
                <script>window._bd_share_config = {
                    "common": {
                        "bdSnsKey": {},
                        "bdText": "",
                        "bdMini": "2",
                        "bdMiniList": false,
                        "bdPic": "",
                        "bdStyle": "0",
                        "bdSize": "32"
                    },
                    "share": {},
                    "image": {
                        "viewList": ["qzone", "tsina", "tqq", "renren", "weixin"],
                        "viewText": "分享到：",
                        "viewSize": "32"
                    },
                    "selectShare": {
                        "bdContainerClass": null,
                        "bdSelectMiniList": ["qzone", "tsina", "tqq", "renren", "weixin"]
                    }
                };
                with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];</script>
            </div>
        </header>
        <div class="article-content">
        </div>
        <a name="comments"></a>
        <!--畅言-->
        <!--PC和WAP自适应版-->
        <div id="SOHUCS" sid="9999999"></div>
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
</section>
</body>
</html>
<jsp:include page="foot.jsp"/>

