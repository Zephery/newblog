<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/6/21
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<aside class="sidebar">
    <div class="widget widget_text" style="height: 100px;">
        <div style="margin-left: 30px">
            <iframe src="http://tianqi.5ikfc.com:90/plugin-code/?style=11&dy=1" allowTransparency="true"
                    frameborder="0" scrolling="no" width="260" height="100" id="weather_frame"></iframe>
        </div>
    </div>
    <div class="widget d_postlist">
        <div class="title"><h2>猜你喜欢</h2></div>
        <ul>
            <c:forEach var="blog" items="${blogbyhits}">
                <li><a href="${pageContext.request.contextPath}/getblogdetail.html?blogid=${blog.blogid}"
                       title="${blog.title}">
                    <span class="thumbnail"><img src="${blog.imageurl}" alt="${blog.title}" width="100px"></span>
                    <span class="text">${blog.title}</span><span class="muted">${blog.createAt}</span>
                    <span class="muted">${blog.hits}浏览</span></a></li>
            </c:forEach>
        </ul>
    </div>

    <div class="widget d_tag">
        <div class="title"><h2>分类</h2></div>
        <div class="d_tags">
            <c:forEach var="category" items="${categories}">
                <a href="${pageContext.request.contextPath}/getbycategoryid.html?cid=${category.cId}">${category.cName}</a>
            </c:forEach>
            <a href="${pageContext.request.contextPath}/life.html">生活笔记</a>
        </div>
    </div>

    <div class="widget widget_text">
        <div class="title"><h2>新浪微博</h2></div>
        <div class="textwidget">
            <iframe width="100%" height="550" class="share_self" frameborder="0" scrolling="no"
                    src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=550&fansRow=2&ptype=1&speed=0&skin=1&isTitle=0&noborder=0&isWeibo=1&isFans=1&uid=1925306000&verifier=5d4515d6&dpc=1"></iframe>
        </div>
    </div>

    <div class="widgetRoller">
        <div class="widget d_tag">
            <div class="title"><h2>标签云</h2></div>
            <div class="d_tags">
                <c:forEach var="tag" items="${tags}">
                    <a title="" href="${pageContext.request.contextPath}/tech.html?tid=${tag.key}"
                       data-original-title="3个话题">${tag.value}</a>
                </c:forEach>
            </div>
        </div>
    </div>
</aside>