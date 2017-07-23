<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="css/chromagallery.css">

</head>
<body>
<div class="temp">
    <c:forEach var="image" items="${images}">
        <img src="${image.imagepath}" alt="${image.imagename}" data-largesrc="${image.imagepath}">
    </c:forEach>

</div>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/chromagallery.pkgd.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(".temp").chromaGallery();
    });
</script>
</body>
</html>

