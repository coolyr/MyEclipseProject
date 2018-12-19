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

<title>My JSP 'email.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
table {
	border: #0000FF 1px solid;
	width: 50%;
}

table td,table th {
	border: #0099FF 1px solid;
}

table th {
	background-color: #009900;
}

.one {
	background-color: #FFFFF99;
}

.two {
	background-color: #33FFFF;
}

.over {
	background-color: #006699;
}
</style>
<script type="text/javascript">
	var name;
	//行的颜色间隔显示并高良
	function trColor() {
		var tabNode = document.getElementsByTagName("table")[0];

		var trs = tabNode.rows;

		for (var x = 0; x < trs.length; x++) {
			if (x % 2 == 1)
				trs[x].className = "one";
			else
				trs[x].className = "two";

			trs[x].onmouseover = function() {
				name = this.className;
				this.className = "over";
			};
			trs[x].onmouseout = function() {
				this.className = name;
			};
		}
	}

	window.onload = function() {

		trColor();
	};

	function checkAll(index) {
		var allNode = document.getElementsByName("all")[index];

		var mails = document.getElementsByName("email");

		for (var x = 0; x < mails.length; ++x) {
			mails[x].checked = allNode.checked;
		}
	}
	//完成按钮的选取
	function checkByButton(num) {

		var mails = document.getElementsByName("email");
		for (var x = 0; x < mails.length; x++) {
			if (num > 1) {
				mails[x].checked = !mails[x].checked;
			} else {
				//1 = true   0 = false
				mails[x].checked = num;
			}
		}

	}
	//删除所选邮件
	function delMail() {

		if (!window.confirm("你真的要删除所选择的邮件吗？"))
			return;//结束当前函数的运行
		var mails = document.getElementsByName("email");
		var arr = new Array();
		var pos = 0;

		for (var x = 0; x < mails.length; x++) {
			if (mails[x].checked) {
				var trNode = mails[x].parentNode.parentNode;
				arr[pos++] = trNode;
			}
		}
		for (var x = 0; x < arr.length; x++) {
			var trNode = arr[x];
			trNode.parentNode.removeChild(trNode);
		}
		trColor();

	}
</script>

</head>

<body>
	<table>
		<tr>
			<th><input type="checkbox" name="all" onclick="checkAll(0)" />全选</th>
			<th>发件人</th>
			<th>邮件名称</th>
		</tr>
		<tr>
			<td><input type="checkbox" name="email" /></td>
			<td>张三00</td>
			<td>新邮件</td>
		</tr>
		<tr>
			<td><input type="checkbox" name="email" /></td>
			<td>张三01</td>
			<td>新邮件</td>
		</tr>
		<tr>
			<td><input type="checkbox" name="email" /></td>
			<td>张三02</td>
			<td>新邮件</td>
		</tr>
		<tr>
			<td><input type="checkbox" name="email" /></td>
			<td>张三03</td>
			<td>新邮件</td>
		</tr>
		<tr>
			<td><input type="checkbox" name="email" /></td>
			<td>张三04</td>
			<td>新邮件</td>
		</tr>
		<tr>
			<td><input type="checkbox" name="email" /></td>
			<td>张三05</td>
			<td>新邮件</td>
		</tr>

		<tr>
			<th><input type="checkbox" name="all" onclick="checkAll(1)" />全选</th>
			<th colspan="2"><input type="button" value="全选"
				onclick="checkByButton(1)" /> <input type="button" value="取消全选"
				onclick="checkByButton(0)" /> <input type="button" value="反选"
				onclick="checkByButton(2)" /> <input type="button" value="删除所选邮件"
				onclick="delMail()" /></th>
		</tr>


	</table>
</body>
</html>








