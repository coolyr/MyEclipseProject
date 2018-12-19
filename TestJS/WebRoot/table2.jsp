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

<title>My JSP 'table2.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	//实现排序
	var sortDir = true;
	function sortTab() {
		var tabNode = document.getElementsByTagName("table")[0];
		var trs = tabNode.rows;
		//alert(trs.length);

		var arr = new Array();
		for (var x = 1; x < trs.length; x++) {
			arr[x - 1] = trs[x];
		}
		//alert(arr.length);
		mySort(arr);//排序

		var tbd = tabNode.getElementsByTagName("tbody")[0];
		//alert(tbd.nodeName);
		if (sortDir) {
			sortDir = false;
			for (var x = 0; x < arr.length; x++) {
				//alert(arr[x].cells[1].innerText);
				//此处直接添加就可以，以内arr内存储的是trs的引用，在对arr进行排序的时候是对引用的排序，
				//当执行下面的添加的操作的时候，其实是往里面从小到大添加了一遍排好序的引用，
				//所以每添加一个相当于从原先的表格中删除相应的数据，然后添加到表格的末尾（删除一条，添加一条），
				//执行完毕的时候就是全部排好序的
				tbd.appendChild(arr[x]);
			}
		} else {
			sortDir = true;
			for (var x = arr.length - 1; x >= 0; x--) {
				tbd.appendChild(arr[x]);
			}
		}
		
		//重新调用填充颜色的函数
		trColor();
	}

	//自定义排序函数
	function mySort(arr) {
		for (var x = 0; x < arr.length; x++) {
			for (var y = x + 1; y < arr.length; y++) {
				//默认的是按ASCII排序的，所以要首先转换成整数
				if (parseInt(arr[x].cells[1].innerText) > parseInt(arr[y].cells[1].innerText)) {
					var temp = arr[x];
					arr[x] = arr[y];
					arr[y] = temp;
				}
			}
		}
	}
	//实现行的间隔颜色显示

	function trColor() {
		var tabNode = document.getElementsByTagName("table")[0];
		var trs = tabNode.rows;
		//alert(trs.length);

		for (var x = 1; x < trs.length; ++x) {
			if (x % 2 == 1) {
				trs[x].className = "one";
			} else {
				trs[x].className = "two";
			}
			//使用匿名函数动态绑定事件处理函数
			trs[x].onmouseover = function(){
				cName = this.className;
				this.className = "over";
			};
			//使用匿名函数动态绑定事件处理函数
			trs[x].onmouseout = function(){
				this.className = cName;
			};
		}
	}

	var cName;
	/*
	 function over(tr) {
	 cName = tr.className;
	 tr.className = "over";
	 }

	 function out(tr) {
	 tr.className = cName;
	 }
	 */
	window.onload = trColor;
</script>

<style type="text/css">
table {
	border: black 3px solid
}

table td {
	border: purple 1px solid;
}

.one {
	background-color: #CAD8E6;
}

.two {
	background-color: #F8D080;
}

.over {
	background-color: pink;
}
</style>
</head>

<body>
	<h2>实现对表格的排序</h2>

	<table>
		<tr>
			<th>姓名</th>
			<th><a href="javascript:void(0)" onclick="sortTab()">年龄</a></th>
			<th>地址</th>
		</tr>
		<tr onmouseover="over(this)" onmouseout="out(this)">
			<td>张三</td>
			<td>30</td>
			<td>北京</td>
		<tr>
			<td>李四</td>
			<td>35</td>
			<td>上海</td>
		<tr>
			<td>王五</td>
			<td>30</td>
			<td>山东</td>
		<tr>
			<td>周六</td>
			<td>25</td>
			<td>武汉</td>
		<tr>
			<td>赵丽</td>
			<td>32</td>
			<td>厦门</td>
		</tr>
	</table>

</body>
</html>



