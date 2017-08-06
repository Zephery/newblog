<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2016/8/5
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title><%=request.getParameter("title")%>
    </title>
    <link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/images/66.jpg"/>
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="css/style.css" type="text/css" rel="stylesheet" media="all">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%--<link rel="stylesheet"--%>
    <%--href="${pageContext.request.contextPath}/js/font-awesome-4.7.0/css/font-awesome.min.css">--%>
    <%--font-awesome cdn--%>
    <script src="https://use.fontawesome.com/58ebe948e6.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/js/social-share-1.0.2/dist/social-share.min.css">
    <script src="${pageContext.request.contextPath}/js/social-share-1.0.2/dist/social-share.min.js"></script>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
    </script>
    <script>
        $(function () {
            var el = document.getElementById('share-area');
            var links = [{
                plugin: 'github',
                url: 'https://github.com/Zephery'
            }, {
                plugin: 'weibo',
                url: 'http://weibo.com/1925306000'
            }, {
                plugin: 'facebook',
                args: {
                    id: 'zephery.wen'           // Your facebook ID
                }
            }];
            var options = {
                size: 'sm'
            };
            window.socialShare(el, links, options);
        });
        $(document).ready(function () {
            var navOffset = $(".widgetRoller").offset().top;
            $(window).scroll(function () {
                var scrollPos = $(window).scrollTop();
                if (scrollPos >= navOffset) {
                    $(".widgetRoller").css({position: "fixed", top: 0, zIndex: 0, width: 330}).fadeIn(100);
                } else {
                    $(".widgetRoller").removeAttr("style");
                }
            });
        });
    </script>
    <!--//end-smoth-scrolling-->
    <META NAME="Title" CONTENT="Zephery | 温志怀的个人网站">
    <meta name="keywords" content="温志怀,wenzhihuai,java,机器学习,python,博客"/>
    <meta name="description" content="温志怀的个人网站"/>
    <meta name="author" content="温志怀"/>
    <!--百度统计-->
    <script type="text/javascript">
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?e580b8db831811a4aaf4a8f3e30034dc";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
    <!--百度站内搜索验证-->
    <meta name="baidu-site-verification" content="me5PEgngG6"/>
    <!--百度链接提交，自动推送-->
    <script>
        (function () {
            var bp = document.createElement('script');
            var curProtocol = window.location.protocol.split(':')[0];
            if (curProtocol === 'https') {
                bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
            }
            else {
                bp.src = 'http://push.zhanzhang.baidu.com/push.js';
            }
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(bp, s);
        })();
    </script>
    <!--360搜索-->
    <meta name="360-site-verification" content="db160af16a61f74e2657c0540f4ccd0d"/>
    <!--搜狗-->
    <meta name="sogou_site_verification" content="ASSLaoFirg"/>
</head>
<body>
<!--navigation-->
<div class="top-nav">
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-left">
                    <li><a href="${pageContext.request.contextPath}/index.html"
                           class="<%=request.getParameter("indexactive")%>">主页</a></li>
                    <li><a href="${pageContext.request.contextPath}/tech.html?pagenum=1"
                           class="<%=request.getParameter("techactive")%>">技术杂谈</a></li>
                    <li><a href="${pageContext.request.contextPath}/life.html?pagenum=1"
                           class="<%=request.getParameter("lifeactive")%>">生活笔记</a></li>
                    <li><a href="${pageContext.request.contextPath}/trip.html"
                           class="<%=request.getParameter("tripactive")%>">旅行</a></li>
                    <li><a href="${pageContext.request.contextPath}/log.html"
                           class="<%=request.getParameter("logactive")%>">日志系统</a></li>
                    <li><a href="${pageContext.request.contextPath}/board.html"
                           class="<%=request.getParameter("boardactive")%>">留言板</a></li>
                    <li><a href="${pageContext.request.contextPath}/aboutme.jsp"
                           class="<%=request.getParameter("aboutmeactive")%>">关于</a></li>
                    <li><a href="${pageContext.request.contextPath}/donate.jsp"
                           class="<%=request.getParameter("donateactive")%>">捐赠</a></li>
                    <li><a href="${pageContext.request.contextPath}/interest.html"
                           class="<%=request.getParameter("interestactive")%>">有点意思</a></li>
                    <li><a href="test.jsp" class="<%=request.getParameter("testactive")%>">Test</a></li>
                </ul>
                <div class="social-icons">
                    <div id="share-area" style="margin-top: -5px"></div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </nav>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $().UItoTop({easingType: 'easeOutQuart'});
    });
</script>
<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
<script src="js/bootstrap.js"></script>
<script type="text/javascript">
    function checkData() {
        var q = document.getElementById("remote_input").value.trim();
        if (q == null || q == "") {
            alert("请输入您要查询的关键字！");
            return false;
        } else {
            return true;
        }
    }
</script>
<!--header-->
<div class="header">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp"><img src="images/logo.png" alt=""></a>
        </div>
        <form action="${pageContext.request.contextPath}/search.html" method="post"
              class="navbar-form navbar-right" role="search">
            <div class="input-group" style="margin-bottom: -10px">
                <input type="text" name="keyword"
                       class="form-control" id="remote_input" placeholder="Searching...">
                <span class="input-group-btn">
                    <button id="open" class="btn btn-default" type="submit" onsubmit="checkData()">搜索</button>
			</span>
            </div>
        </form>
    </div>
</div>
</body>
<link rel="stylesheet" href="js/autocomplete/jquery.autocomplete.css">
<script src="js/autocomplete/jquery.autocomplete.js" type="text/javascript"></script>
<script type="text/javascript">
    /******************** remote start **********************/
    /**这里有个bug，页面加载的时候keyword为空也会发送请求**/
    $('#remote_input').autocomplete({
        source: [
            {
                url: "ajaxsearch.html?keyword=%QUERY%",
                type: 'remote'
            }
        ]
    });
    /********************* remote end ********************/
</script>
</html>