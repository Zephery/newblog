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
    <jsp:param name="boardactive" value="active"/>
    <jsp:param name="title" value="留言板"/>
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
    <script>
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?e580b8db831811a4aaf4a8f3e30034dc";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
    <style>
        .article-content h1 {
            margin: 20px -20px 20px -20px;
            padding: 10px 20px 9px 10px;
            border-left: 4px solid #00a67c;
            background-color: #fbfbfb
        }

        a:hover {
            text-decoration: none;
        }

        #share a {
            width: 34px;
            height: 34px;
            padding: 0;
            margin: 6px;
            border-radius: 25px;
            transition: all .4s;
            /*cursor: url(http://www.mizuiren.com/wp-content/themes/QIUYE/images/link.cur), pointer;*/
        }

        .bdshare-button-style0-24 a:hover {
            box-shadow: 0 0 2px 2px #FFF;
            transition: all .4s !important;
        }

        #share a.bds_qzone {
            background: url(http://image.wenzhihuai.com/t_QQZone.png
            ) no-repeat;
            background-size: 34px 34px;
        }

        #share a.bds_tsina {
            background: url(http://image.wenzhihuai.com/t_XinLang.png
            ) no-repeat;
            background-size: 34px 34px;
        }

        #share a.bds_sqq {
            background: url(http://image.wenzhihuai.com/t_QQ.png
            ) no-repeat;
            background-size: 34px 34px;
        }

        #share a.bds_weixin {
            background: url(http://image.wenzhihuai.com/t_Friend.png
            ) no-repeat;
            background-size: 34px 34px;
        }

        #share a.bds_fbook {
            background: url(http://image.wenzhihuai.com/t_Fbook.png
            ) no-repeat;
            background-size: 34px 34px;
        }

        #share a.bds_copy {
            background: url(http://image.wenzhihuai.com/t_URL.png
            ) no-repeat;
            background-size: 34px 34px;
        }

        #share-box {
            float: right;
        }

        .article-tags {
            margin-top: 10px;
        }

        @media (max-width: 800px) {
            .article-tags {
                float: none;
                margin-bottom: 10px;
            }

            #share-box {
                float: left;
            }
        }

        .sharetitle {
            float: left;
        }
    </style>
    <script>window._bd_share_config = {
        "common": {
            "bdSnsKey": {},
            "bdText": "",
            "bdMini": "2",
            "bdMiniList": false,
            "bdPic": "",
            "bdStyle": "1",
            "bdSize": "32"
        },
        "share": {}
    };
    with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
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
                <h1 class="sharetitle" style="margin-top: 10px">
                    <a>分享到</a>
                </h1>
                <div id="share-box">
                    <div id="share">
                        <div class="bdsharebuttonbox bdshare-button-style0-24" data-bd-bind="1510388056059">
                            <a href="#" class="bds_qzone" data-cmd="qzone" title="Share to Qzone">
                            </a>
                            <a href="#" class="bds_tsina" data-cmd="tsina" title="Share to Sina">
                            </a>
                            <a href="#" class="bds_sqq" data-cmd="sqq" title="Share to QQ">
                            </a>
                            <a href="#" class="bds_weixin" data-cmd="weixin" title="Share to Wechat">
                            </a>
                            <a href="#" class="bds_more" data-cmd="more"></a>
                        </div>
                    </div>
                </div>
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
<%@include file="foot.jsp" %>
</body>
</html>