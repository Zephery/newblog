<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/7/19
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>

<c:forEach var="image" items="${images}">
    <img src="${image.imagepath}" alt="${image.imagename}" data-largesrc="${image.imagepath}">
</c:forEach>
