<%--
  Created by IntelliJ IDEA.
  User: Zephery
  Date: 2017/10/15
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Web Socket JavaScript Echo Client</title>
    <script src="https://cdn.bootcdn.net/sockjs-client/1.1.4/sockjs.js"></script>
    <script language="javascript" type="text/javascript">
        var ws;
        var result;

        function init() {
            output = document.getElementById("output");
        }

        function send_echo() {
            var wsUri = "ws://localhost:9090/kafkaws.ws";
            writeToScreen("Connecting to " + wsUri);
            ws = new WebSocket(wsUri);
            ws.onopen = function (evt) {
                writeToScreen("Connected !");
                doSend(evt.value);
            };
            ws.onmessage = function (evt) {
                writeToScreen("Received message: " + evt.data);

            };
            ws.onclose = function (event) {
                alert("已经与服务器断开连接rn当前连接状态：" + this.readyState);
            };
            ws.onerror = function (evt) {
                writeToScreen('<span style="color: red;">ERROR:</span> '
                    + evt.data);
            };
        }

        function doSend(message) {
            ws.send(message);
            writeToScreen("Sent message: " + message);
        }

        function writeToScreen(message) {
            var pre = document.createElement("p");
            pre.style.wordWrap = "break-word";
            pre.innerHTML = message;
            output.appendChild(pre);
        }

        window.addEventListener("load", init, false);
    </script>
</head>
<body>
<h1>Echo Server</h1>
<div style="text-align: left;">
    <form action="">
        <input onclick="send_echo()" value="发送socket请求" type="button">
        <input id="textID" name="message" value="Hello World, Web Sockets" type="text">
        <br>
    </form>
</div>
<div id="output"></div>
</body>
</html>