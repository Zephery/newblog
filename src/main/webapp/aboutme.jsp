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
    <link href="${pageContext.request.contextPath}/js/mousepicture/css/lanrenzhijia.css" type="text/css"
          rel="stylesheet"/>
    <!--畅言获取评论数，未来有可能变为https-->
    <script type="text/javascript"
            src="http://assets.changyan.sohu.com/upload/plugins/plugins.count.js">
    </script>
    <link rel="stylesheet" href="http://image.wenzhihuai.com/editormd.preview.css"/>
    <style>
        * {
            font-family: "Microsoft Yahei", arial, sans-serif;
        }

        .article-content h1 {
            margin: 20px -20px 20px -20px;
            padding: 10px 20px 9px 10px;
            border-left: 4px solid #00a67c;
            background-color: #fbfbfb
        }

        /* 响应式布局 */
        @media (min-width: 768px) {
            .icon-contact {
                height: 80px;
                margin: auto;
                width: 100%;
                max-width: 700px;
            }

            .icon-contact .fa {
                line-height: 70px;
                font-size: 35px;
                width: 70px;
            }

            .icon-contact .social-icon {
                display: inline-block;
                width: 70px;
                height: 70px;
                color: #fff;
                border-radius: 100%;
                margin: 6px;
                text-align: center;
                text-indent: 0px;
            }
        }

        @media (max-width: 768px) {
            .icon-contact {
                height: 60px;
                margin: auto;
                width: 100%;
            }

            .icon-contact .fa {
                line-height: 50px;
                font-size: 20px;
                width: 50px;
            }

            .icon-contact .social-icon {
                display: inline-block;
                width: 50px;
                height: 50px;
                color: #fff;
                border-radius: 100%;
                margin: 6px;
                text-align: center;
                text-indent: 0px;
            }
        }

        .icon-contact .sinaweibo {
            background-color: #EC5B5B;
        }

        .icon-contact .tencentweibo {
            background-color: #2BC0B5;
        }

        .icon-contact .wechat {
            background-color: #49C085;
        }

        .icon-contact .qq {
            background-color: #6F92FF;
        }

        .icon-contact .renren {
            background-color: #7243D4;
        }

        .icon-contact .github {
            background-color: #7782D1;
        }

        .icon-contact a {
            color: #fff !important;
        }

        .icon-contact #icon {
            float: left;
        }

        .icon-contact .col {
            width: 16.666667%;
            float: left;
            position: relative;
            min-height: 1px;
        }

        .icon-contact {
            max-width: 700px;
            margin: auto;
            text-indent: 0px;
        }

        .friend-link a {
            color: #00a67c !important;
        }

        .tech {
            max-width: 700px;
            margin: auto;
        }

        .tech .item {
            width: 100%;
            clear: both;
            margin-right: auto;
            margin-left: auto;
            height: 35px;
        }

        .tech .describe {
            font-family: "Microsoft Yahei";
            margin-right: 12px;
            text-align: center;
        }

        .tech .progress {
            float: left;
            height: 25px;
            background: #f2f2f2;
            border-left: 1px solid transparent;
            border-right: 1px solid transparent;
            width: 70%;
        }

        .tech .progress > span {
            position: relative;
            float: left;
            margin: 0 -1px;
            min-width: 30px;
            height: 25px;
            line-height: 21px;
            text-align: right;
            background: #cccccc;
            border: none;
            border-color: #bfbfbf #b3b3b3 #9e9e9e;
            -webkit-box-shadow: inset 0 1px rgba(255, 255, 255, 0.3), 0 1px 2px rgba(0, 0, 0, 0.2);
            box-shadow: inset 0 1px rgba(255, 255, 255, 0.3), 0 1px 2px rgba(0, 0, 0, 0.2);
        }

        .tech .progress > span > span {
            padding: 0 8px;
            font-size: 14px;
            color: #404040;
            color: rgba(0, 0, 0, 0.7);
            font-family: "Microsoft Yahie";
            line-height: 25px;
        }

        .tech .progress > span:before {
            content: '';
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            z-index: 1;
            height: 25px;
            border-radius: 10px;
        }

        .tech .progress .green {
            background: #49C085;
        }

        .tech .progress .darkblue {
            background: #7782D1;
        }

        .tech .progress .red {
            background: rgb(245, 138, 135);
        }

        .tech .progress .orange {
            background: #f2b63c;
        }

        .tech .progress .blue {
            background: #6F92FF;
        }

        @media (min-width: 750px) {
            .tech .describe {
                width: 130px;
                float: left;
            }

            .tech .progress {
                width: 79%;
                float: left;
            }
        }

        @media (max-width: 750px) {
            .tech .describe {
                width: 100%;
                margin-bottom: 5px;
                text-align: left;
            }

            .tech .progress {
                width: 100%;
                margin-bottom: 5px;
            }
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
            <p style="text-align: center;font-size:16px;color: white">温志怀</p>
            <p style="text-align: center;color: white">爱生活、爱艺术、爱书画、爱音乐、爱设计、爱编程。</p>
            <p style="text-align: center;color: white">人生百态，笑口常开，秉承自我，谨慎独行。</p>
            <p style="text-align: center;">
            </p>
            <p><!--end personal --></p>
            <p><!-- tech tree --></p>
            <h2 style="text-align: center;margin: 0px;background: transparent;color: white">我的技能树</h2>
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
    </div>
</section>
<%@include file="foot.jsp"%>
</body>
</html>