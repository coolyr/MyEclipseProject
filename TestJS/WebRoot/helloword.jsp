<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'helloword.jsp' starting page</title>

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
	<h1>JavaScript简单的变量和语句练习</h1>

	<script type="text/javascript">
		/*
		//(1)  if语句的使用，非0为真，0为假。true=1, false=0. 布尔变量可以进行运算。
			var x = 3;
			if (x == 3)
				alert(x);
			else
				alert(x);
		 */

		/*
		
		// (2) swith语句的使用：可以比较任意类型
		var x = 'abc';
		switch (x) {
		case 'a':
		alert('a');
		case 'ab':
		alert('ab');
		case 'abc':
		alert('abc');
		}
		 */

		/*
		
		//(3) for语句的使用
		 var sum = '';
		 for (var x = 1; x <= 5; ++x) {
		 if (x == 5)
		 sum = sum + "x=" + x;
		 else
		 sum = sum + "x=" + x + ',';
		 }
		 document.write("<font color='red'>"+sum+ "</font>");*/
		 
		 
		 /*
		 //(4)数组的使用
		 //数组里面可以混合使用任意类型，并且可以随意添加的数据，它是动态实现的。
		 var arys = [1,2.5,'c',true];
		 
		 arys[5] = 6;
		 //普通方式
		 for(var x=0; x<arys.length; ++x)
		 {
		 	alert(arys[x]);
		 }
		 //增强for循环
		 for(var x in arys)
		 {
		 	alert(arys[x]);
		 }*/
	</script>


</body>
</html>
