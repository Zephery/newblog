<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="${pageContext.request.contextPath}/js/mousepicture/css/lanrenzhijia.css" type="text/css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"/>
    <meta charset="UTF-8">
    <link rel="stylesheet" id="style-css" href="js/jingmi/jingmistyle.css" type="text/css" media="all">
    <!--畅言获取评论数，未来有可能变为https-->
    <script type="text/javascript"
            src="http://assets.changyan.sohu.com/upload/plugins/plugins.count.js">
    </script>
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
    <style>
        .article-content h1 {
            margin: 20px -20px 20px -20px;
            padding: 10px 20px 9px 10px;
            border-left: 4px solid #00a67c;
            background-color: #fbfbfb
        }
    </style>
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
                            href="http://cuiqingcai.com/category/technique/python"
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
                <div style="padding-left: 580px">
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
            </footer>
            <nav class="article-nav">
                <c:if test="${preblog!=null}">
                    <span class="article-nav-prev"><i class="fa fa-angle-double-left"></i> <a
                            href="${pageContext.request.contextPath}/getblogdetail.html?blogid=${preblog.blogid}"
                            rel="prev">上一篇：${preblog.title}</a></span>
                </c:if>
                <c:if test="${nextblog!=null}">
                <span class="article-nav-next">
                        <a href="${pageContext.request.contextPath}/getblogdetail.html?blogid=${nextblog.blogid}"
                           rel="next">下一篇： ${nextblog.title}</a>
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
    <c:import url="/sidebar.html"/>
</section>
</body>
</html>
<jsp:include page="foot.jsp"/>
