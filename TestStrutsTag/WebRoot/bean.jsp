<%@page import="cn.pku.coolyr.Domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入Struts标签库中的bean标签库 -->
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'index.jsp' starting page</title>
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
		request.setAttribute("abc", "abc");
		User u1 = new User();
		u1.setUsername("coolyr");
		u1.setPassword("123");
		request.setAttribute("user", u1);
	%>
	<h4>0.bean:write标签, 用于输出变量信息</h4>
	<bean:write name="abc" />

	<h4>1.bean:write标签, 用于输出对象信息</h4>
	<bean:write name="user" property="username" scope="request" />

	<h4>bean:message用于和资源文件配合实现国际化和框架优化</h4>
	<bean:message key="err" arg0="用户名错误！" arg1="密码错误！" />

</body>
</html>
