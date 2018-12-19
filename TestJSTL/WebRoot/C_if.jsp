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

	<h4>0.test中使用的EL表达式的形式。只有if没有else逻辑,所以一般要多次判断。</h4>
	<c:if test="${ 2<30 ? true:false}">
	ok
	</c:if>

	<%
		request.setAttribute("a", "hello");
		request.setAttribute("age", "22");
		User u = new User();
		u.setUsername("coolyr");
		u.setPassword("123");
		request.setAttribute("u", u);
	%>

	<h4>1.判断字符串</h4>
	<c:if test="${a=='hello'}">
 	  hello
	</c:if>

	<h4>2.判断数值</h4>
	<c:if test="${age>12  and age<30 or age==22}">
 	 年龄大于12 小于30 ${age}
    </c:if>

	<h4>3.判断对象</h4>
	<c:if test="${u.password == '123'}">
 	  ${u.username }
	</c:if>

</body>
</html>
