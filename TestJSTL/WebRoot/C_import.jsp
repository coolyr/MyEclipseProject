<%@page import="cn.pku.coolyr.Domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>

<title>My JSP 'C_if.jsp' starting page</title>

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

<h4>使用import导入a.jsp页面的内容</h4>
<h4>使用param传入参数</h4>

	<c:import url="a.jsp">
		<c:param name="name" value="coolyr" />
	</c:import>

</body>
</html>
