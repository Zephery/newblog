<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<c:choose>
    <c:when test="${blog.category.cId==0}">
        <jsp:include page="head.jsp">
            <jsp:param name="lifeactive" value="active"/>
            <jsp:param name="title" value="${blog.title}"/>
        </jsp:include>
    </c:when>
    <c:otherwise>
        <jsp:include page="head.jsp">
            <jsp:param name="techactive" value="active"/>
            <jsp:param name="title" value="${blog.title}"/>
        </jsp:include>
    </c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
<head>
    <link href="http://image.wenzhihuai.com/lanrenzhijia.css?ver=20171017" type="text/css"
          rel="stylesheet"/>
    <!--畅言获取评论数，未来有可能变为https-->
    <script type="text/javascript"
            src="http://assets.changyan.sohu.com/upload/plugins/plugins.count.js">
    </script>
    <link rel="stylesheet" href="http://image.wenzhihuai.com/editormd.preview.css?ver=20171221"/>
    <script src="//lib.baomitu.com/marked/0.3.6/marked.min.js"></script>
    <script src="//lib.baomitu.com/prettify/r298/prettify.min.js"></script>
    <script src="//lib.baomitu.com/raphael/2.3.0/raphael.min.js"></script>
    <script src="http://image.wenzhihuai.com/editormd.js"></script>
    <script src="//lib.baomitu.com/highlight.js/9.18.5/highlight.min.js"></script>
    <script type="text/javascript">
        $(function () {
            editormd.markdownToHTML("test-editormd-view2", {
                htmlDecode: "style,script,iframe",  // you can filter tags decode
                emoji: true,
                taskList: true,
                tex: true  // 默认不解析
            });
        });
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
            cursor: url(http://www.mizuiren.com/wp-content/themes/QIUYE/images/link.cur), pointer;
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
    <div class="content-wrap">
        <div class="content">
            <div class="breadcrumbs">
                <a title="返回首页" href="${pageContext.request.contextPath}"> <i class="fa fa-home fa-2x"></i></a>
                <small>&gt;</small>
                <a href="${pageContext.request.contextPath}/tech.html?categoryid=${blog.category.cId}"
                   style="font-size: 17px">${blog.category.cName}</a>
                <small>&gt;</small>
                <span class="muted" style="font-size: 17px">${blog.title}</span>
            </div>
            <header class="article-header">
                <h1 class="article-title" style="text-align: center; margin: 15px 0"
                    href="${pageContext.request.contextPath}/getblogdetail.html?blogid=${blog.blogid}">${blog.title}
                </h1>
                <div class="meta" style="text-align: center">
                    <span id="mute-category" class="muted"><i class="fa fa-list-alt"></i><a
                            href="http://www.wenzhihuai.com"
                            style="padding-left: 7px">${blog.category.cName}</a></span>
                    <time class="muted"><i class="fa fa-clock-o"></i> ${blog.createAt}</time>
                    <span class="muted"><i class="fa fa-eye"></i> ${blog.hits}浏览</span>
                    <span class="muted"><i class="fa fa-comments-o"></i>
                        <a href="#SOHUCS" id="changyan_count_unit"></a>评论
                    </span>

                </div>
            </header>
            <article class="article-content">
                <div id="test-editormd-view2">
                <textarea id="append-test" style="display:none;">
${blog.content}
                </textarea>
                </div>
            </article>
            <div style="background:#fff;padding-bottom:20px;font-size:14px;">
                <p style="text-align: center;margin-bottom:20px;padding:0px 20px">
                    您的支持是博主写作最大的动力，如果您喜欢我的文章，感觉我的文章对您有帮助，请狠狠点击下面的</p>
                <p style="text-align: center;"><a href="${pageContext.request.contextPath}/donate.jsp"> <input
                        style="width: 310px; margin: 10px auto 0px; background-color: #f58a87; color: #ffffff; height: 40px; border: none; font-family: 'Microsoft Yahei'; font-size: 16px; letter-spacing: 2px;"
                        type="button" value="我要小额赞助"></a>
                </p></div>
            <footer class="article-footer">
                <div class="article-tags">
                    <i class="fa fa-tags"></i>
                    <c:forEach var="tag" items="${blog.tags}">
                        <a href="${pageContext.request.contextPath}/tech.html?t_id=${tag.tId}" rel="tag"
                           data-original-title="" title="">${tag.tName}</a>
                    </c:forEach>
                </div>
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
            </footer>
            <nav class="article-nav">
                <c:if test="${preblog!=null}">
                    <span class="article-nav-prev"><i class="fa fa-angle-double-left"></i> <a
                            href="${pageContext.request.contextPath}/getblogdetail.html?blogid=${preblog.blogid}"
                            rel="prev">${preblog.title}</a></span>
                </c:if>
                <c:if test="${nextblog!=null}">
                <span class="article-nav-next">
                        <a href="${pageContext.request.contextPath}/getblogdetail.html?blogid=${nextblog.blogid}"
                           rel="next">${nextblog.title}</a>
                    <i class="fa fa-angle-double-right"></i></span>
                </c:if>
            </nav>
            <a name="comments"></a>
            <!--畅言-->
            <!--PC和WAP自适应版-->
            <div id="SOHUCS" sid="${blog.blogid}"></div>
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
                })(); </script>
            <!--畅言end-->

        </div>
    </div>
    <%@include file="sidebar.jsp" %>
</section>
<%@include file="foot.jsp" %>
</body>
</html>