<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'C_catch.jsp' starting page</title>

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

	<h4>0.用于捕获catch块内代码抛出的异常信息，会被自动封装在自定义的对象myException中</h4>
	<c:catch var="myException">
		<%
			int i = 3 / 0;
		%>
	</c:catch>
	<c:out value="${myException.message}"></c:out>

</body>
</html>
