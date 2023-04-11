<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="techactive" value="active"/>
    <jsp:param name="title" value="技术杂谈"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
    <script src="${pageContext.request.contextPath}/js/responsiveslides.min.js"></script>
</head>
<body class="home blog hPC">
<section class="contentcontainer">
    <div class="content-wrap">
        <div class="content">
            <div class="breadcrumbs" style="margin-bottom: 10px;">
                <a title="返回首页" href="${pageContext.request.contextPath}">
                    <i class="fa fa-home fa-2x"></i>
                </a>
                <small>&gt;</small>
                <a href="${pageContext.request.contextPath}/tech.html?pagenum=1" style="font-size: 17px">技术杂谈</a>
                <c:if test="${category.cName!=null}">
                    <small>&gt;</small>
                    <a href="${pageContext.request.contextPath}/tech.html?pagenum=1&categoryid=${category.cId}">类别：${category.cName}</a>
                </c:if>
                <c:if test="${tag.tName!=null}">
                    <small>&gt;</small>
                    <a href="${pageContext.request.contextPath}/tech.html?pagenum=1&tid=${tag.tId}">标签：${tag.tName}</a>
                </c:if>
            </div>
            <c:forEach var="blog" items="${blogs}">
                <article class="excerpt">
                    <header>
                        <a class="label label-important"
                           href="${pageContext.request.contextPath}/getcategory.html?categoryid=${blog.categoryid}">${blog.category.cName}<i
                                class="label-arrow"></i></a>
                        <h2><a href="${pageContext.request.contextPath}/getblogdetail.html?blogid=${blog.blogid}"
                               title="${blog.title}">${blog.title}</a>
                        </h2>
                    </header>
                    <div class="focus">
                        <a href="getblogdetail.html?blogid=${blog.blogid}">
                            <img class="thumb" src="${blog.imageurl}"
                                 width="200" height="123"
                                 alt="${blog.title}">
                        </a>
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
                            <a href="${pageContext.request.contextPath}/tech.html?pagenum=1&tid=${tag.tId}&categoryid=${category.cId}">首页</a>
                        </li>
                        <li class="prev-page"><a
                                href="${pageContext.request.contextPath}/tech.html?pagenum=${pageNum - 1}&tid=${tag.tId}&categoryid=${category.cId}">上一页</a>
                        </li>
                        <c:forEach var="pageIndex" begin="${startpage}" end="${endpage}">
                            <c:choose>
                                <c:when test="${pageNum == pageIndex}">
                                    <li class="active"><span> ${pageIndex} </span></li>
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/tech.html?pagenum=${pageIndex}&tid=${tag.tId}&categoryid=${category.cId}">${pageIndex}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${pageNum != totalpages}">
                            <li class="next-page"><a
                                    href="${pageContext.request.contextPath}/tech.html?pagenum=${pageNum+1}&tid=${tag.tId}&categoryid=${category.cId}">下一页</a>
                            </li>
                        </c:if>
                        <li>
                            <a href="${pageContext.request.contextPath}/tech.html?pagenum=${totalpages}&tid=${tag.tId}&categoryid=${category.cId}">末页</a>
                        </li>
                        <li>
                            <a>共${totalpages}页</a>
                        </li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
    <%@include file="sidebar.jsp" %>
</section>
<%@include file="foot.jsp" %>
</body>
</html>