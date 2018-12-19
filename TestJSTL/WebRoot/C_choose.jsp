<%@page import="cn.pku.coolyr.Domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'C_shose.jsp' starting page</title>

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

	<%
		int age = 3;
		request.setAttribute("age", age);
	%>

	<c:choose>
		<c:when test="${age < 6 }">
			<font color="red">儿童</font>
		</c:when>

		<c:when test="${age >6 and age < 18 }">
			<font color="green">青少年</font>
		</c:when>

		<c:otherwise>
			<font color="gold">成年</font>
		</c:otherwise>
	</c:choose>


</body>
</html>
