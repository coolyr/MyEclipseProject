<%@ page language="java" import="java.util.*" isErrorPage="true" pageEncoding="UTF-8"%>
<%--该page标签是JSP的类型标签：    language:内嵌的（片段）语言类型为java   pageEncoding:编码格式 --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'Test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<%! int i = 10;//这是servlet中的成员变量%>

	<%
		int i = 100;//这是Servlet中的Service函数中的局部变量
		out.println("i = " + i);
		out.println("</br>");
	%>

	<%!public int sum(int i, int j) //定一个函数（相当于Servlet的成员函数）
	{
		return i + j;
	}%>

	<!-- 这是HTML格式的注释 -->
	<%--这是JSP专用注释 --%>
	
	<%
		//在<%之间写我们的Java代码，就和我们在java文件中写代码一样。
		out.println("path = " + path);
		out.println("</br>");
		out.println("basePath = " + basePath);
		out.println("</br>");
		
		//为什么可以直接使out对象：：是JSP的内置对象
		//（JSP在翻译成Servlet的时候自动定义并初始化的对象，所以可以直接使用）
		out.println("hello JSP  Time=" + new java.util.Date());
		out.println("</br>");
		
		//函数测试
		out.println("sum(1,2) =  " + sum(1, 2));
		out.println("</br>");
		
		//九大内置对象
		out.println("输出对象： out " + out.getClass().getName()+"<br/>");
		out.println("请求对象： request " + request.getClass().getName()+"<br/>");
		out.println("相应对象： response " + response.getClass().getName()+"<br/>");
		out.println("会话对象： session " + session.getClass().getName()+"<br/>");
		out.println("应用对象： application " + application.getClass().getName()+"<br/>");
		out.println("页面上下文对象： pageContext " + pageContext.getClass().getName()+"<br/>");
		//out.println("异常对象： exception " + exception.getClass().getName()+"<br/>");//需 要 配 置isErrorPage="true" 
		out.println("页面对象： page " + page.getClass().getName()+"<br/>");
		out.println("配置对象： config " + config.getClass().getName()+"<br/>");
	%>
</body>
</html>
