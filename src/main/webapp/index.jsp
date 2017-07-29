<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="indexactive" value="active"/>
    <jsp:param name="title" value="Zephery | 温志怀的个人博客"/>
</jsp:include>
<!DOCTYPE html>
<!-- saved from url=(0022)http://cuiqingcai.com/ -->
<html>
<head>
    <link href="${pageContext.request.contextPath}/js/mousepicture/css/lanrenzhijia.css" type="text/css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/wowslider/engine1/style.css"/>
    <script src="${pageContext.request.contextPath}/js/responsiveslides.min.js"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" id="style-css" href="js/jingmi/jingmistyle.css" type="text/css" media="all">
    <!-- Start WOWSlider.com HEAD section -->
    <link rel="stylesheet" type="text/css" href="wowslider/engine1/style.css"/>
    <script type="text/javascript" src="wowslider/engine1/jquery.js"></script>
    <!-- End WOWSlider.com HEAD section -->
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
    <div class="content-wrap">
        <div class="content">
            <div>
                <!-- Start WOWSlider.com BODY section -->
                <div id="wowslider-container1">
                    <div class="ws_images">
                        <ul>
                            <c:forEach var="banner" items="${banners}">
                                <li><a href="getblogdetail.html?blogid=${banner.blogid}" target="_self">
                                    <img src="${banner.imageurl}"
                                         alt="${banner.title}" title="${banner.title}"
                                         id="wows1_0"/></a>${banner.title}
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="ws_bullets">
                        <div>
                            <c:forEach var="banner" items="${banners}">
                                <a href="${pageContext.request.contextPath}/getblogdetail.html?blogid=${banner.blogid}"
                                   title="${banner.title}">
                                    <span><img src="${banner.imageurl}?imageView2/1/w/120/h/90"
                                               alt="${banner.title}"/>${banner.title}</span></a>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="ws_shadow"></div>
                </div>
                <script type="text/javascript" src="wowslider/engine1/wowslider.js"></script>
                <script type="text/javascript" src="wowslider/engine1/script.js"></script>
                <!-- End WOWSlider.com BODY section -->
            </div>
            <div>
                <div class="left-ad"
                     style="clear: both;background-color: #fff; width: 30%;float: left;margin-right:2%;"></div>
                <div class="hot-posts">
                    <h2 class="title">热门排行</h2>
                    <ul>
                        <c:set var="count" value="1"/>
                        <c:forEach var="blog" items="${hotblogs}">
                            <li>
                                <p><span class="muted">
                            <a data-action="ding" data-id="1052" class="action">
                                <i class="fa fa-eye"></i>
                                <span class="count">${blog.hits}</span> 浏览</a></span>
                                </p><span class="label label-${count}">${count}</span>
                                <a href="${pageContext.request.contextPath}/getblogdetail.html?blogid=${blog.blogid}"
                                   title="${blog.title}">${blog.title}</a></li>
                            <c:set var="count" value="${count+1}"/>
                        </c:forEach>
                    </ul>
                </div>
            </div>


            <c:forEach var="blog" items="${blogs}">
                <article class="excerpt">
                    <header>
                        <a class="label label-important" style="padding-bottom: 5px;"
                           href="getcategory.html?categoryid=${blog.categoryid}">${blog.category.cName}<i
                                class="label-arrow"></i></a>
                        <h2><a href="getblogdetail.html?blogid=${blog.blogid}"
                               title="${blog.title}">${blog.title}</a>
                        </h2>
                    </header>
                    <div class="focus"><a href="getblogdetail.html?blogid=${blog.blogid}">
                        <img class="thumb" src="${blog.imageurl}?imageView2/1/w/200/h/123" alt="${blog.title}"></a>
                    </div>
                    <span class="note">${blog.summary}...</span>
                    <p class="auth-span">
                        <span class="muted"><i class="fa fa-clock-o"></i> ${blog.createAt}</span>
                        <span class="muted"><i class="fa fa-eye"></i> ${blog.hits}浏览</span>
                        <span class="muted">
                            <a href="${pageContext.request.contextPath}/getblogdetail.html?blogid=${blog.blogid}">
                            <span id="sourceId::${blog.blogid}" class="cy_cmt_count"></span>
                            <script id="cy_cmt_num"
                                    src="https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt4SnwiG">
                            </script>评论</a>
                        </span>

                </article>

            </c:forEach>
            <c:if test="${totalpages>1}">                <%--如果页码数大于1--%>
                <div class="pagination" style="background: transparent">
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/index.html?pagenum=1">首页</a>
                        </li>
                        <li class="prev-page"><a
                                href="${pageContext.request.contextPath}/index.html?pagenum=${pageNum - 1}">上一页</a>
                        </li>
                        <c:forEach var="pageIndex" begin="${startpage}" end="${endpage}">
                            <c:choose>
                                <c:when test="${pageNum == pageIndex}">
                                    <li class="active"><span> ${pageIndex} </span></li>
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/index.html?pagenum=${pageIndex}">${pageIndex}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${pageNum != totalpages}">
                            <li class="next-page"><a
                                    href="${pageContext.request.contextPath}/index.html?pagenum=${pageNum+1}">下一页</a>
                            </li>
                        </c:if>
                        <li>
                            <a href="${pageContext.request.contextPath}/index.html?pagenum=${totalpages}">末页</a>
                        </li>
                        <li>
                            <a>共${totalpages}页</a>
                        </li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
    <c:import url="/sidebar.html"/>
</section>
</body>
</html>
<jsp:include page="foot.jsp"/>