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

<title>My JSP 'table.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
	function createTable() {
		var rowNum = document.getElementsByName("rownum")[0].value;
		var colNum = document.getElementsByName("colnum")[0].value;
		var tableNode = document.createElement("table");
		tableNode.setAttribute("id", "tabid");
		for (var x = 1; x <= rowNum; x++) {
			var trNode = tableNode.insertRow();
			for (var y = 1; y <= colNum; y++) {
				var tdNode = trNode.insertCell();
				tdNode.innerHTML = "(" + x + "," + y + ")";
			}
		}

		document.getElementsByTagName("div")[0].appendChild(tableNode);
		event.srcElement.disabled = "true";
	}

	function delRow() {
		var tabNode = document.getElementById("tabid");
		if (tabNode == null) {
			alert("表格不存在！");
			return;//终止函数的执行
		}

		var delRowNum = document.getElementById("delrow").value;

		//alert(delRowNum);
		if (delRowNum > 0 && delRowNum <= tabNode.rows.length) {
			tabNode.deleteRow(delRowNum - 1);//从第0行开始
		} else {
			alert("删除的行不存在！");
		}
	}

	function delCol() {
		var tabNode = document.getElementById("tabid");
		if (tabNode == null) {
			alert("表格不存在！");
			return;//终止函数的执行
		}

		var delColNum = document.getElementById("delrcol").value;

		//alert(delColNum);
		if (delColNum > 0 && delColNum <= tabNode.rows[0].cells.length) {
			for (var x = 0; x < tabNode.rows.length; ++x) {
				tabNode.rows[x].deleteCell(delColNum - 1);
			}

		} else {
			alert("删除的列不存在！");
		}
	}
</script>

<style type="text/css">
table {
	border: black 3px solid
}

table td {
	border: purple 1px solid;
}
</style>
</head>

<body>
	<!-- 通过页面的按钮可以动态的创建一个表格 -->
	行：
	<input type="text" name="rownum" />
	<br /> 列：
	<input type="text" name="colnum" />
	<br />
	<input type="button" value="创建表格" onclick="createTable()" />
	<br />

	<input type="text" id="delrow" />
	<input type="button" value="删除行" onclick="delRow()" />
	<br />
	<input type="text" id="delrcol" />
	<input type="button" value="删除列" onclick="delCol()" />
	<br />
	<div></div>

</body>
</html>
