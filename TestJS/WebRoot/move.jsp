<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'move.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
#bodyid {
	border: #000000 1px solid;
	height: 600px;
	width: 800px;
}
</style>

<script type="text/javascript">
	window.onload = function() {
		document.body.onmousemove = function() {
			var adNode = document.getElementById("ad");

			adNode.style.left = event.x-10;
			adNode.style.top = event.y-10;
		};
	};
	
	function closead(){
	var adNode = document.getElementById("ad");
	adNode.style.display = "none";
	}
</script>
</head>

<body>

	<div id="ad" style="position: absolute;left: 0px; top: 0px;" onclick="closead()">
		<a href="http://www.baidu.com" target="_blank"><img alt="picture" src="imgs/ring.jpg" height="80px" width="100px"></a>
	</div>
	<div id="bodyid">body 区域</div>

</body>
</html>




