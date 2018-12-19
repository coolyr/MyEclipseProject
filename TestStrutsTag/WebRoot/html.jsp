<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入Struts标签库中的HTML标签库 -->
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'html.jsp' starting page</title>

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
	<h4>0.property就是普通HTML标签的name属性</h4>
	<html:button property="sure" value="确定"></html:button>

	<h4>1.超链接</h4>
	<html:link href="http://www.baidu.com">百度</html:link>

	<h4>3.这是一个图片按钮</h4>
	<html:image src="images/100.png"></html:image>

	<h4>4.这是真真正正的一i张图片</h4>
	<html:img src="images/100.png" />

	<h3>5.struts标签库如果使用提交信息的标签必须满足3个要求：</h3>
	<h4>(1)必须放到一个html:form表单中。</h4>
	<h4>(2)必须有一个Form对象与之对应，不然会报错。</h4>
	<h4>(3)action的路径只写资源名就可以，不用再写应用名。struts框架会记住应用名</h4>
	<html:form action="login.do" method="post">
		用户名：<html:text property="username"></html:text><br/>
		密　码：<html:text property="password"></html:text>
		<html:submit value="submit"></html:submit>
	</html:form>

</body>
</html>
