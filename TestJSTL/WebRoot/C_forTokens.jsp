<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'forTokens .jsp' starting page</title>

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


	<h4>用于分隔字符:</h4>
	<c:forTokens items="11;33;44;52;" delims=";" var="temp">
	${temp}
	</c:forTokens>

	<h2 style="color:red">什么时候用$符,什么时候不用$ ?</h2>

	<h4>如果是从某个域对象中取出值,取的是一个变量就要用$ ,取的是一个固定的值就不要$</h4>


</body>
</html>
