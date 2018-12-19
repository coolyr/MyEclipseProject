<!--这个是计算器的界面-->
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<h1>我的计算器</h1>
<hr>
<head>

<script type="text/javascript" src="js/myJS.js"></script>

</head>
<body>
	<form action="/TestJSP/CalculatorResult.jsp" method="post">

		请输入第一个数：<input type="text" id="num1" name="num1"><br> 
		请选择运算符号：<select
			name="flag">
			<option value="+">+</option>
			<option value="-">-</option>
			<option value="*">*</option>
			<option value="/">/</option>
		</select>
		<br>
		 请输入第二个数：<input type="text" id="num2" name="num2"> <br> 
		 
		<input type="submit" value="计算" onclick="return checkNum()">
	</form>
	<hr>
</body>
</html>