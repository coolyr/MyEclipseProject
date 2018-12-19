<%@ page language="java" import="java.util.*,cn.pku.coolyr.Domain.User"
	pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 -->
<!-- 更换新的ＤＴＤ格式 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>My JSP 'C_out.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<%
		request.setAttribute("username", "coolyr");
	%>

	<c:out value="hello world"></c:out><br/>
	<h4>0.等同于request.getAttribute("username").toString();</h4>
	
	<c:out value="${username}" default="没有值" escapeXml="false"></c:out><br/>


	<h4>1. 如何输出request,session,application, pageContext域对象的数据 ,default默认为空时候的输出内容</h4>
	<h4>2.这里有个优先级的问题 : pageContext>request>session>application(从小到大)</h4>

	<%
		request.setAttribute("s", "request你好!");
		session.setAttribute("s", "session你好!");
		application.setAttribute("s", "application你好");
		pageContext.setAttribute("s", "pageContext你们<a href='http://www.baidu.com'>百度</a>");
	%>

	<h4>3.escapeXml表示是否按照html样式显示 。默认是true:表示以文本显示</h4>

	<c:out value="${s}" default="没有值" escapeXml="false"></c:out><br/>


	<h4>4. 如果是在同一个页面,那么这段代码输出 : "pageContext你们好百度"</h4>
	<%
		User u = new User();
		u.setUsername("admin");
		u.setPassword("admin");
		request.setAttribute("user", u);
	%>

	<h4>5.相当于(User) request.setAttribute("user").getUsername();</h4>
	<c:out value="name = ${user.username}"></c:out><br/>
	<c:out value="pwd  = ${user.password}"></c:out><br/>










</body>
</html>
