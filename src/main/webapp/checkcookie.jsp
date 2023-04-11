<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/10/21
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
<c:forEach var="cookie" items="${cookies}">
    ${cookie.name}:${cookie.value}
</c:forEach>

${session.id}


</body>
</html>