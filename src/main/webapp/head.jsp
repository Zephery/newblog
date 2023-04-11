<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2016/8/5
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title><%=request.getParameter("title")%>
    </title>
    <meta charset="UTF-8">
    <link type="image/x-icon" rel="shortcut icon" href="http://image.wenzhihuai.com/66.jpg"/>
    <link href="http://image.wenzhihuai.com/blogbootstrap.css?ver=20171019" type="text/css" rel="stylesheet"
          media="all">
    <link href="http://image.wenzhihuai.com/style.css?ver=201711151452" type="text/css" rel="stylesheet" media="all">
    <link rel="stylesheet" href="http://image.wenzhihuai.com/jingmi.css?ver=20171017"
          type="text/css" media="all">
    <link href="http://image.wenzhihuai.com/lanrenzhijia.css?ver=20171017" type="text/css"
          rel="stylesheet"/>
    <%--font-awesome cdn--%>
    <link href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet"
          href="http://image.wenzhihuai.com/social-share-1.0.2/dist/social-share.min.css?ver=20171017">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="http://image.wenzhihuai.com/social-share-1.0.2/dist/social-share.min.js?ver=20171017"></script>
    <%--下面这段代码会回到顶部--%>
    <%--<script type="application/x-javascript">--%>
    <%--addEventListener("load", function () {--%>
    <%--setTimeout(hideURLbar, 0);--%>
    <%--}, false);--%>

    <%--function hideURLbar() {--%>
    <%--window.scrollTo(0, 1);--%>
    <%--}--%>
    <%--</script>--%>
    <script type="text/javascript" src="http://image.wenzhihuai.com/move-top.js?ver=2017111411"></script>
    <script type="text/javascript" src="http://image.wenzhihuai.com/easing.js?ver=20171017"></script>
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
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
    </script>
    <!--//end-smoth-scrolling-->
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!--SEO-->
    <meta name="title" content="技术文档">
    <meta name="keywords" content="java,机器学习,python,日志"/>
    <meta name="description" content="Zephery的个人日志"/>
    <meta name="author" content="Zephery"/>
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
    <%--注释掉垃圾360自动提交代码--%>
    <%--<script>(function () {--%>
    <%--var src = (document.location.protocol == "http:") ? "http://js.passport.qihucdn.com/11.0.1.js?3c2ebef08880b4f3ed8dd43ac4361401" : "https://jspassport.ssl.qhimg.com/11.0.1.js?3c2ebef08880b4f3ed8dd43ac4361401";--%>
    <%--document.write('<script src="' + src + '" id="sozz"><\/script>');--%>
    <%--})();--%>
    <%--</script>--%>
    <!--搜狗-->
    <meta name="sogou_site_verification" content="Lzg0yrcxwy"/>
    <!--bing-->
    <meta name="msvalidate.01" content="68A11010C9AEE3FD3F5BD421EACC7499"/>
    <!-- Global site tag (gtag.js) - Google Analytics -->
    <%--<script async src="https://www.googletagmanager.com/gtag/js?id=UA-117168799-1"></script>--%>
    <%--<script>--%>
        <%--window.dataLayer = window.dataLayer || [];--%>
        <%--function gtag(){dataLayer.push(arguments);}--%>
        <%--gtag('js', new Date());--%>

        <%--gtag('config', 'UA-117168799-1');--%>
    <%--</script>--%>

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
                    <li><a href="${pageContext.request.contextPath}/log.html"
                           class="<%=request.getParameter("logactive")%>">日志系统</a></li>
                    <li><a href="${pageContext.request.contextPath}/board.html"
                           class="<%=request.getParameter("boardactive")%>">留言板</a></li>
                    <li><a href="${pageContext.request.contextPath}/aboutme.html"
                           class="<%=request.getParameter("aboutmeactive")%>">关于我</a></li>
                    <li><a href="${pageContext.request.contextPath}/trip.html"
                           class="<%=request.getParameter("tripactive")%>">旅行</a></li>
                    <li><a href="${pageContext.request.contextPath}/donate.html"
                           class="<%=request.getParameter("donateactive")%>">捐赠</a></li>
                    <li><a href="${pageContext.request.contextPath}/live.html"
                           class="<%=request.getParameter("liveactive")%>">直播</a></li>
                    <%--<li><a href="${pageContext.request.contextPath}/weibonlp.html"--%>
                           <%--class="<%=request.getParameter("interestactive")%>">有点意思</a></li>--%>
                    <%--<li><a href="${pageContext.request.contextPath}/myresume.html"--%>
                           <%--class="<%=request.getParameter("myresumeactive")%>">我的简历</a></li>--%>
                    <%--<li><a href="${pageContext.request.contextPath}/open.html"--%>
                           <%--class="<%=request.getParameter("openactive")%>">开放平台</a></li>--%>
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
<a href="#" id="toTop"></a>
<script src="//lib.baomitu.com/twitter-bootstrap/5.1.3/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
    function checkData() {
        var q = $("#remote_input").val();
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
            <a class="navbar-brand" href="index.html"><img src="http://image.wenzhihuai.com/logo.png?ver=20171017"
                                                           alt=""></a>
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
<link rel="stylesheet" href="http://image.wenzhihuai.com/jquery.autocomplete.css">
<script src="http://image.wenzhihuai.com/jquery.autocomplete.js" type="text/javascript"></script>
<script type="text/javascript">
    /******************** remote start **********************/
    var remote_input = $('#remote_input');
    remote_input.autocomplete({
        source: [
            {
                minChars: 1,
                url: "${pageContext.request.contextPath}/ajaxsearch.do?keyword=%QUERY%",
                type: 'remote'
            }
        ]
    });
    /********************* remote end ********************/
</script>
</html>
