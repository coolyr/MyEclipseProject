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
<!-- 使用分派Action -->
	<form action="/TestStrutsTag/loginAndout.do?flag=login" method="post">
		用户名：<input type="text" name="username"/> <br /> 
		密　码: <input type="password" name="password"/><br /> 
		<input type="submit" value="确定"/>
		<input type="reset" value="重置"/><br /> 
	</form>
</body>
</html>
