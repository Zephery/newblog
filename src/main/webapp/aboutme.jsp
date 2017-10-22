<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/7/21
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="head.jsp">
    <jsp:param name="aboutmeactive" value="active"/>
    <jsp:param name="title" value="关于我"/>
</jsp:include>

<!DOCTYPE html>
<html>
<head>
    <link href="http://image.wenzhihuai.com/lanrenzhijia.css?ver=20171017" type="text/css"
          rel="stylesheet"/>
    <!--畅言获取评论数，未来有可能变为https-->
    <script type="text/javascript"
            src="http://assets.changyan.sohu.com/upload/plugins/plugins.count.js">
    </script>
    <link rel="stylesheet" href="http://image.wenzhihuai.com/editormd.preview.css?ver=20171017"/>
    <link rel="stylesheet" href="http://image.wenzhihuai.com/reveal.css?ver=20171023">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reveal.css"/>--%>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://image.wenzhihuai.com/jquery.reveal.js?ver=20171021"></script>
    <style>
        p {
            text-align: center;
            font-size: 16px;
            color: white
        }
    </style>
</head>
<body class="home blog hPC">
<section class="contentcontainer">
    <div class="article-content" style="background-image: url(http://image.wenzhihuai.com/backgroundimage.jpg);
background-size: 100% 100%;background-attachment: fixed;">
        <div style="text-indent:0px;">
            <h1 style="text-align: center;margin: 0;background: transparent;color: white">个人简介</h1>
            <p><img src="http://image.wenzhihuai.com/66.jpg" style="border-radius:50%;width:100px"></p>
            <p>温志怀</p>
            <p>哈哈哈哈哈哈哈</p>
            <p></p>
            <p>人生百态，笑口常开，秉承自我，谨慎独行。</p>
            <p>专注高并发、高可用、分布式计算</p>
            <p>
                本站源码：
                <a href="https://github.com/Zephery/newblog" style="color: white">
                    <strong>https://github.com/Zephery/newblog</strong>
                </a>
            </p>
            <p style="text-align: center;">
            </p>
            <h2 style="text-align: center;margin: 0;background: transparent;color: white">与我联系</h2>
            <div class="icon-contact">
                <div class="col" id="icon">
                    <a href="http://www.weibo.com/1925306000/profile" target="_blank"
                       class="social-icon tool-tip sinaweibo"
                       title="" data-placement="top" data-original-title="新浪微博"><i class="fa fa-weibo"></i></a>
                </div>
                <div class="col" id="icon">
                    <a href="http://t.qq.com/w1570631036?preview" target="_blank"
                       class="social-icon tool-tip tencentweibo" title="" data-placement="top"
                       data-original-title="腾讯微博"><i class="fa fa-tencent-weibo"></i></a>
                </div>
                <div class="col" id="icon">
                    <a title="QQ" href="http://wpa.qq.com/msgrd?v=3&uin=1570631036&site=qq&menu=yes"
                       target="_blank" class="social-icon tool-tip qq" data-placement="top"
                       data-original-title="1570631036"><i class="fa fa-qq"></i></a>
                </div>
                <div class="col" id="icon">
                    <a href="https://github.com/Zephery" target="_blank" class="social-icon tool-tip github" title=""
                       data-placement="top" data-original-title="GitHub"><i class="fa fa-github"></i></a>
                </div>

                <div class="col" id="icon">
                    <a href="https://www.facebook.com/zephery.wen" target="_blank" class="social-icon tool-tip facebook"
                       title=""
                       data-placement="top" data-original-title="Facebook"><i class="fa fa-facebook"></i></a>
                </div>
                <div class="col colleft" id="icon">
                    <%--弹出层效果链接：http://www.jq22.com/jquery-info109--%>
                    <a href="http://image.wenzhihuai.com/mywechat.png" target="_blank"
                       class="social-icon tool-tip wechat big-link" title=""
                       data-placement="top" data-original-title="WeChat" data-reveal-id="myModal" data-animation="fade"><i
                            class="fa fa-wechat"></i></a>
                </div>
            </div>
            <p><!--end personal --></p>
            <p><!-- tech tree --></p>
            <h2 style="text-align: center;margin: 0;background: transparent;color: white">我的技能树</h2>
            <section class="tech">
                <div class="item">
                    <div class="describe" style="color: white">
                        Java
                    </div>
                    <div class="progress">
                        <span class="blue" style="width: 85%;"><span>85%</span></span>
                    </div>
                    <p></p>
                </div>
                <div class="item">
                    <div class="describe" style="color: white">
                        Python
                    </div>
                    <div class="progress">
                        <span class="green" style="width: 58%;"><span>58%</span></span>
                    </div>
                    <p></p>
                </div>
                <div class="item">
                    <div class="describe" style="color: white">
                        HTML/CSS/JS
                    </div>
                    <div class="progress">
                        <span class="green" style="width: 74%;"><span>74%</span></span>
                    </div>
                    <p></p>
                </div>
                <div class="item">
                    <div class="describe" style="color: white">
                        MySql
                    </div>
                    <div class="progress">
                        <span class="darkblue" style="width: 82%;"><span>82%</span></span>
                    </div>
                    <p></p>
                </div>
            </section>
            <p><!-- end tech --></p>
        </div>
        <%@include file="myreading.jsp" %>
        <br>
    </div>
</section>
<br>
<div id="myModal" class="reveal-modal">
    <a class="close-reveal-modal">&#215;</a>
    <img src="http://image.wenzhihuai.com/mywechat.png" width="100%" height="100%"/>
</div>
<%@include file="foot.jsp" %>
</body>
</html>