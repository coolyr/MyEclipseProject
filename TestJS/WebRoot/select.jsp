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

<title>My JSP 'select.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
	function change() {
		var selNode = document.getElementById("selid");
		var value = selNode.options[selNode.selectedIndex].value;
		//alert(value);
		var divNode1 = document.getElementById("cssid");
		var divNode2 = document.getElementById("textid");
		
		divNode1.style.textTransform = value;
		divNode2.innerHTML = "text-transform : " + value + ";";
		
	}
</script>

<style type="text/css">
#cssid {
	background-color: yellow;
	height: 150px;
	width: 250px;
}
#textid{
	background-color: gray;
	height: 18p;
	width: 300px;
}
</style>
</head>

<body>
	<div id="cssid">good good study, Day day up!!</div>

	<select id="selid" onchange="change()">
		<option value="none">--text-transform--</option>
		<option value="capitalize">首字母大写</option>
		<option value="uppercase">所有字母大写</option>
		<option value="lowercase">所有字母小写</option>
	</select>

	<p></p>

	<div id="textid">text-transform : none;</div>
</body>
</html>
