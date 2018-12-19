<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'C_remove.jsp' starting page</title>

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

	<c:set var="name" value="coolyr" scope="request"></c:set>
	<h4>0.等同于request.setAttribute("name","coolyr");</h4>

	<c:out value=" name = ${name}" default="没有值"></c:out>
	<br />
	<h4>1.等同于request.removeAttribute("name");</h4>
	<h4>2.如果不写scope，默认删除所有域中的对象</h4>
	<c:remove var="name" scope="request"/>
	<h4>删除后</h4>
	<c:out value=" name = ${name}" default="nothing！"></c:out>
</body>
</html>
