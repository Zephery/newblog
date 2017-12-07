<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/11/29
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>汕头市12345政府服务热线|登录</title>
    <%@include file="/common/common.jsp" %>
</head>

<body class="bg-body">
<%@include file="/common/head_user.jsp" %>
<div class="container">
    <%@include file="/common/head.jsp" %>
    <div class="breadcrumb">
        <ul>
            <li><a href="${basePath}/">您现在的位置：首页</a><span class="split">></span></li>
            <li class="active">用户登录</li>
        </ul>
    </div>
    <div class="box">
        <div class="box-content">
            <form id="fm" action="${basePath }/public/login.do" method="post" class="form-horizontal">
                <input name="source" type="hidden" value="0"/>
                <div class="fix">
                    <span class="label">账号/手机号：</span>
                    <div class="cell">
                        <input class="form-control w300" type="text" name="account" id="account">
                    </div>
                </div>
                <div class="fix">
                    <span class="label">密码：</span>
                    <div class="cell">
                        <input class="form-control w300" type="password" name="password" id="password">
                    </div>
                </div>
                <div class="fix">
                    <span class="label"><span class="form-required">*</span>验证码：</span>
                    <div class="cell">
                        <input class="form-control w300" type="text" id="vcode" placeholder="请输入验证码">
                        <img id="captchaImg" class="vn"
                             src="${basePath}/public/vcode/random.do?t=<%= new Date().toString()%>"/><a
                            href="javascript:;" onclick="changeImg()" class="ml10">换一张</a>
                    </div>
                </div>
                <div class="fix">
                    <div class="cell offset-x">
                        <a class="btn btn-blue" href="javascript:;" onclick="login()">登录</a> <!-- 加类 disable 变成不可用状态-->
                        <a class="btn">取消</a>
                        <a class="btn" onclick="toHall()">使用网厅账号登陆</a>
                        <a class="btn" onclick="toQQ()">使用QQ账号登陆</a>
                        <p class="mt5"><a href="${basePath}/reset/pwd.do">忘记密码</a></p>
                        <p id="formCheckMsg" class="mt5" style="color: red;">${loginMsg}</p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function toQQ() {
        window.location.href = "/login.do";
    }

    function login() {
        var account = $.trim($("#account").val());
        if (!account || account == "") {
            alert("请输账号/手机号");
            /*  $("#formCheckMsg").text("请输入验证码"); */
            return;
        }
        var password = $.trim($("#password").val());
        if (!password || password == "") {
            alert("请输入正确密码");
            /*  $("#formCheckMsg").text("请输入验证码"); */
            return;
        }

        var idCode = $.trim($("#vcode").val());
        if (!idCode || idCode == "") {
            alert("请输入验证码");
            /*  $("#formCheckMsg").text("请输入验证码"); */
            return;
        }

        $.ajax({
            url: "${basePath}/public/vcode/validate.do",
            dataType: "json",
            data: {
                code: idCode
            },
            success: function (res) {
                if (!res.success) {
                    alert("验证码错误");
                    //$("#formCheckMsg").text("验证码错误");
                    changeImg();
                } else {
                    $('#fm').submit();
                }
            }
        });
    }

    function toHall() {
        window.location.href = "${basePath}/toHall/login.do";
    }

    function toQQ() {
        window.location.href = "${basePath}/ssoLogin/qqLogin.do";
    }

    function changeImg() {
        var img = $("#captchaImg");
        var url = "${basePath}/public/vcode/random.do?t=" + new Date();
        $(img).attr("src", url);
    }
</script>
</body>
</html>

