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

<title>My JSP 'checkBox.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function getSum() {
		var sum = 0;
		var items = document.getElementsByName("item");
		for (var x = 0; x < items.length; ++x) {
			if (items[x].checked) {
				sum += parseInt(items[x].value);
			}
		}
		var spanNode = document.getElementById("sum");
		var sumstr = sum + "元";
		spanNode.innerHTML = sumstr.fontsize("18px");
	}
	
	function checkAll(){
	 var allNode = document.getElementsByName("all")[0];
	 var items = document.getElementsByName("item");
	 for(var x=0; x<items.length; ++x){
	 	items[x].checked = allNode.checked;
	 }
	}
</script>
</head>

<body>
	<input type="checkbox" name="all" onclick="checkAll()"/>全选
	<br />
	<input type="checkbox" name="item" value="3000" />笔记本电脑
	<br />
	<input type="checkbox" name="item" value="3000" />笔记本电脑
	<br />
	<input type="checkbox" name="item" value="3000" />笔记本电脑
	<br />
	<input type="checkbox" name="item" value="3000" />笔记本电脑
	<br />
	<input type="checkbox" name="item" value="3000" />笔记本电脑
	<br />
	<input type="checkbox" name="item" value="3000" />笔记本电脑
	<br />
	<input type="checkbox" name="item" value="3000" />笔记本电脑
	<br />
	<input type="button" value="获取总金额" onclick="getSum()" />
	<br />
	<span id="sum"></span>

</body>
</html>
