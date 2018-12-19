<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'CalculatorAll.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/myJS.js"></script>
</head>

<body>

	<%
		//接收第一个数
		String s_num1 = request.getParameter("num1");
		//接收第二个数字
		String s_num2 = request.getParameter("num2");
		//接收运算符
		String flag = request.getParameter("flag");

		float num1 = 0;
		float num2 = 0;

		float result = 0;
		//判断接收的数据是不是null; -> 第一次登入
		if (s_num1 != null && s_num2 != null && flag != null)
		{
			//运行
			num1 = Float.parseFloat(s_num1);
			num2 = Float.parseFloat(s_num2);
			//计算
			if (flag.equals("+"))
			{
				result = num1 + num2;
			}
			else if (flag.equals("-"))
			{
				result = num1 - num2;
			}
			else if (flag.equals("*"))
			{
				result = num1 * num2;
			}
			else
			{
				result = num1 / num2;
			}
		}

		//输出结果
		//out.println("结果是" + result);
	%>
	<form action="/TestJSP/CalculatorAll.jsp" method="post">

		请输入第一个数：<input type="text" id="num1" name="num1" value="<%=num1%>"><br>
		请选择运算符号：<select name="flag">
			<option value="+">+</option>
			<option value="-">-</option>
			<option value="*">*</option>
			<option value="/">/</option>
		</select> <br> 请输入第二个数：<input type="text" id="num2" name="num2" value="<%=num2%>"> <br>

		<input type="submit" value="计算" onclick="return checkNum()">
	</form>
	<hr>
	运算结果是:<%=result%>

</body>
</html>
