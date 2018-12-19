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

<title>My JSP 'ad.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
	var x = 1, y = 1, movex = 0, movey = 0;

	function movead() {
		var divObj = document.getElementById("adid");
		movex = movex + x * 3;
		movey = movey + y * 3;

		divObj.style.top = movey;
		divObj.style.left = movex;

		if (movex + divObj.offsetWidth >= document.body.clientWidth)
			x = -1;
		else if (movex <= 0)
			x = 1;

		if (movey + divObj.offsetHeight >= document.body.clientHeight)
			y = -1;
		else if (movey <= 0)
			y = 1;

	}

	var timeid;

	function over() {
		clearInterval(timeid);
	}

	function out() {
		fly();
	}

	function fly() {
		timeid = setInterval("movead()", 10);
	}

	window.onload = function() {
		fly();
	};

	function closead() {
		var divObj = document.getElementById("adid");
		divObj.style.display = "none";
		over();

	}
</script>
</head>

<body>
	<input type="button" value="手动广告" onclick="movead()" />
	<br />
	<div id="adid" style="position: absolute; top: 0px; left: 0px;"
		onmouseover="over()" onmouseout="out()">
		<a href="http://www.baidu.com" target="_blanck"><img
			src="imgs/ring.jpg" height="100px" width="100px" /></a><br /> <a
			href="javascript:void()" onclick="closead()">关闭</a>
	</div>
	<img src="imgs/Seaking.jpg" height="600px" width="850px" border="2px">
</body>
</html>
