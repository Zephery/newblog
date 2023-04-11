<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="liveactive" value="active"/>
    <jsp:param name="title" value="直播"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>

    <script src="http://image.wenzhihuai.com/responsiveslides.min.js?ver=20171017"></script>
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
    <embed width="100%" height="720" allownetworking="all" allowscriptaccess="always" src="https://staticlive.douyucdn.cn/common/share/play.swf?room_id=3144478" quality="high" bgcolor="#000" wmode="window" allowfullscreen="true"  allowFullScreenInteractive="true" type="application/x-shockwave-flash">
</section>
<%@include file="foot.jsp" %>
</body>
</html>