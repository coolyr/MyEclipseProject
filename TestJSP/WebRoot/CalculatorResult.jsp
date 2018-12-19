<!--得到数据计算，并且显示-->
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<html>
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

		//输出结果
		out.println("结果是" + result);
	%>
</body>
</html>
