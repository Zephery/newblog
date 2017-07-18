<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="tripactive" value="active"/>
    <jsp:param name="title" value="旅行|多出门走走"/>
</jsp:include>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/chromagallery.css">
    <style type="text/css">
        .mygallery {
            margin: 0 auto;
        }
    </style>
    <link href="${pageContext.request.contextPath}/js/mousepicture/css/lanrenzhijia.css" type="text/css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/wowslider/engine1/style.css"/>
    <script src="${pageContext.request.contextPath}/js/responsiveslides.min.js"></script>
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
    <meta charset="UTF-8">
    <script src="js/jingmi/hm.js"></script>
    <script src="js/jingmi/wp-emoji-release.min.js" type="text/javascript" defer=""></script>
    <link rel="stylesheet" id="style-css" href="js/jingmi/jingmistyle.css" type="text/css" media="all">
</head>
<body>
<article style="margin-left: 13px">
    <div class="content" style="margin: 0 auto;width: 90%">
        <div class="chroma-gallery mygallery">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">
            <img src="images/thumbs/1.jpg" alt="Pic 1" data-largesrc="images/1.jpg">
            <img src="images/thumbs/2.jpg" alt="Pic 2" data-largesrc="images/2.jpg">
            <img src="images/thumbs/3.jpg" alt="Pic 3" data-largesrc="images/3.jpg">
            <img src="images/thumbs/4.jpg" alt="Pic 4" data-largesrc="images/4.jpg">
            <img src="images/thumbs/5.jpg" alt="Pic 5" data-largesrc="images/5.jpg">
            <img src="images/thumbs/6.jpg" alt="Pic 6" data-largesrc="images/6.jpg">
            <img src="images/thumbs/7.jpg" alt="Pic 7" data-largesrc="images/7.jpg">
            <img src="images/thumbs/8.jpg" alt="Pic 8" data-largesrc="images/8.jpg">
            <img src="images/thumbs/9.jpg" alt="Pic 9" data-largesrc="images/9.jpg">
            <img src="images/thumbs/10.jpg" alt="Pic 10" data-largesrc="images/10.jpg">

        </div>
    </div>

</article>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/chromagallery.pkgd.min.js"></script>
<script type="text/javascript">
    // $(document).ready(function()
    // {
    //     $(".mygallery").chromaGallery();
    // });
    $(document).ready(function(){
        $(".mygallery").chromaGallery
        ({
            color:'#000',
            gridMargin:15,
            maxColumns:5,
            dof:true,
            screenOpacity:0.8
        });
    });

</script>
</body>
</html>
<jsp:include page="foot.jsp"/>