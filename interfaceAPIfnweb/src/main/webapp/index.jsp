<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE>
<head>
    <title>Java后端WebSocket的Tomcat实现</title>
</head>
<body>
    Welcome<br/>
    <input id="text" type="text" style="width: 100%"/>
    <br/>
    <button onclick="send()">发送消息</button>
    <button onclick="debug()">调试</button>
    <button onclick="putOutCard()">打牌3000</button>
    <button onclick="login()">登录</button>
    <button onclick="createRoom()">创建房间</button>
    <button onclick="joinRoom()">加入房间</button>
    <button onclick="ready()">准备</button>
    <hr/>
    <button onclick="closeWebSocket()">关闭WebSocket连接</button>
    <button onclick="removeResponseContent()">清空响应内容</button>
    <hr/>
    <div id="message">

    </div>
</body>

<script type="text/javascript">
    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://127.0.0.1:8027/apiwebd/webSocketServer.do");
        // websocket = new WebSocket("ws://192.168.1.125:8041/apiweby/webSocketServer.do");
        // websocket = new WebSocket("ws://120.76.84.178:8080/apiweby/webSocketServer.do");
    }
    else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        setMessageInnerHTML("WebSocket连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += "<span style='color: blue'>服务器响应内容 ： </span>"+'<br/>'+innerHTML + '<br/>';
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //清除服务端响应内容
    function removeResponseContent(){
        document.getElementById("message").innerHTML = "";
    }

    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        webSocketSend(message);
    }

    //调试
    function debug() {
        var message = '{pid:1}';
        webSocketSend(message);
    }

   //登录
    function login() {
        var message = document.getElementById('text').value;
        webSocketSend(message);
    }

    //创建房间
    function createRoom() {
        var message = document.getElementById('text').value;
        webSocketSend(message);
    }

    //加入房间
    function joinRoom() {
        var message = document.getElementById('text').value;
        webSocketSend(message);
    }

    //准备
    function ready() {

    }

    //出牌
    function putOutCard() {
        var message = document.getElementById('text').value;
        webSocketSend(message);
    }



    function webSocketSend(message){
        document.getElementById('message').innerHTML += "<span style='color: red'>发送内容 ： </span>"+'<br/>'+message + '<br/>';
        websocket.send(message);
    }

</script>
</html>

