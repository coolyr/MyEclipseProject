<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/my.css">

</head>

<body topmargin="0" bottommargin="0" background="images/bg.bmp">
	<h6 style="color: red"></h6>
	<table width="80%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="160" colspan="2" align="center">
				<jsp:include page="head.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td width="170" height="173" align="center" valign="top">
				<jsp:include page="left.jsp"></jsp:include>
			</td>
			<td width="85%" valign="top" align="center">
				<jsp:include page="right.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td height="79" colspan="2" valign="top" align="center">
				<jsp:include page="tail.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>
