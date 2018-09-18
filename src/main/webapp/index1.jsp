<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div id="display" style="width: 500px; height: 500px;"></div>
	<input type="text" id="message">
	<button onclick="send()">Send</button>
	 <script>
		var websocket = null;
		if ("WebSocket" in window) {
			websocket = new WebSocket("ws://localhost:8080/chatroom");
		} else {
			alert("websocket not supported");
		}
		websocket.onopen = function() {
			setMessageInnerHTML("open");
		};
		websocket.onmessage = function(event) {
			setMessageInnerHTML(event.data);
		};
		websocket.onclose = function() {
			setMessageInnerHTML("close");
		};
		websocket.onbeforeunload = function() {
			websocket.close();
		};

		function send() {
			var message = document.getElementById("message").value;
			websocket.send(message);
			document.getElementById("message").value = "";
		}

		function setMessageInnerHTML(message) {
			document.getElementById("display").innerHTML += message + "<br/>";
		}
	</script> 
</body>
</html>