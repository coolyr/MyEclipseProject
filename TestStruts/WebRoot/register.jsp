<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action="/TestStruts/register.do?flag=register" method="post">

		用户名：<input type="text" name="name"><br />
		密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="passwd" name="passwd"><br />
		email：<input type="text" name="email"><br /> <input
			type="submit" value="注册">

	</form>
</body>
</html>
