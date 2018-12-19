<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="http://libs.useso.com/js/bootstrap/3.2.0/css/bootstrap.min.css"
	type="text/css" />
<style>
div {
	border: 1px solid #cccccc;
}
</style>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<p class="text--warning">头部</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">侧边栏</div>
			<div class="col-md-9">右边</div>
		</div>
		<div class="row">
			<div class="col-md-12">底部</div>
		</div>
	</div>
</body>
</html>
