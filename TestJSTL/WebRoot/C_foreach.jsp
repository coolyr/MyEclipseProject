<%@page import="cn.pku.coolyr.Domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'C_foreach.jsp' starting page</title>

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
		ArrayList<User> al = new ArrayList<User>();
		User u1 = new User();
		u1.setUsername("coolyr");
		u1.setPassword("123");
		User u2 = new User();
		u2.setUsername("system");
		u2.setPassword("manager");
		al.add(u1);
		al.add(u2);
		request.setAttribute("alluser", al);
	%>

	<h4>0.迭代的集合对象放入items中，每次迭代出的对象放入var定义的变量u中。</h4>
	<c:forEach items="${alluser}" var="u">
   	 ${u.username}  
     ${u.password} <br />
	</c:forEach>

	<h4>1.第一种迭代 -- 指定范围</h4>
	<c:forEach var="i" begin="1" end="10">
	${i}->
	</c:forEach>
	<br />

	<h4>2.第二种迭代 -- 指定范围和步长</h4>
	<c:forEach var="i" begin="1" end="10" step="3">
	${i}->
	</c:forEach>
	<br />



</body>
</html>
