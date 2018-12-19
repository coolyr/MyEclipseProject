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

<title>My JSP 'oprateNode.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
.max {
	font-size: 36px;
	color: red;
	background-color: gray;
}

.medium {
	font-size: 16px;
	color: green;
	background-color: gray;
}

.small {
	font-size: 9px;
	color: blue;
	background-color: gray;
}

.open {
	overflow: visible;
	height: auto;
}

.close {
	overflow: hidden;
}

dl {
	overflow: hidden;
	height: 18px;
}

table {
	border: black 3px solid
}

table td {
	border: purple 1px solid;
}

table td div {
	background-color: #E8F2FE;
	display: none;
}

table td a:link,table td a:VISITED {
	text-decoration: none;
	color: blue;
}

.topen {
	display: block;
}

.tclose {
	display: none;
}
</style>
</head>

<body>
	<script type="text/javascript">
		function changeSize(cssName) {
			var divNode = document.getElementById("newsid");
			divNode.className = cssName;
		}

		//展开一个后其余的不闭合
		function list(dtNode) {
			var dlNode = dtNode.parentNode;
			if (dlNode.className == "open") {
				dlNode.className = "close";
			} else {
				dlNode.className = "open";
			}
		}
		//展开一个后其余打开的全部闭合
		function list2(dtNode) {
			var dlNode = dtNode.parentNode;
			var dlNodes = document.getElementsByTagName("dl");

			for (var x = 0; x < dlNodes.length; x++) {
				if (dlNodes[x] == dlNode) {
					if (dlNode.className == "open") {
						dlNode.className = "close";
					} else {
						dlNode.className = "open";
					}
				} else {
					dlNodes[x].className = "close";
				}
			}
		}
		//通过表格实现的展开闭合效果
		function list3(aNode) {
			var tdNode = aNode.parentNode;
			var divNode = tdNode.getElementsByTagName("div")[0];
			if (divNode.className == "topen") {
				divNode.className = "tclose";
			} else {
				divNode.className = "topen";
			}

		}

		//同过表格实现的展开闭合效果，点击一个表单同时关闭其他打开的表单
		function list3(aNode) {
			var tdNode = aNode.parentNode;
			var divNode = tdNode.getElementsByTagName("div")[0];
			var tabNode = tdNode.parentNode.parentNode;
			var divs = tabNode.getElementsByTagName("div");
			for (var i = 0; i < divs.length; ++i) {
				if (divNode == divs[i]) {
					if (divNode.className == "topen") {
						divNode.className = "tclose";
					} else {
						divNode.className = "topen";
					}
				}
				else
				{
					divs[i].className = "close";
				}
			}

		}
	</script>



	<h2>1.动态改变字体的样式</h2>
	<a href="javascript:void(0)" onclick="changeSize('max')">大</a>
	<!-- javascript代表启动浏览器的js引擎，void(0)函数表示取消超链接效果（使用#可以达到同样的效果） -->
	<a href="javascript:void(0)" onclick="changeSize('medium')">中</a>
	<a href="javascript:void(0)" onclick="changeSize('small')">小</a>

	<div id="newsid">
		通过点击改变新闻标题的字体的样式<br /> 通过点击改变新闻标题的字体的样式<br /> 通过点击改变新闻标题的字体的样式<br />
		通过点击改变新闻标题的字体的样式<br /> 通过点击改变新闻标题的字体的样式<br /> 通过点击改变新闻标题的字体的样式<br />

	</div>

	<h2>2.dl列表实现展开闭合的效果</h2>
	<dl>
		<dt onclick="list2(this)">水果</dt>
		<dd>苹果</dd>
		<dd>葡萄</dd>
		<dd>香蕉</dd>
	</dl>
	<dl>
		<dt onclick="list2(this)">
			<a href="javascript:void(0)">蔬菜</a>
		</dt>
		<dd>土豆</dd>
		<dd>芹菜</dd>
		<dd>西红柿</dd>
	</dl>
	<dl>
		<dt onclick="list2(this)">肉类</dt>
		<dd>猪肉</dd>
		<dd>牛肉</dd>
		<dd>鱼肉</dd>
	</dl>

	<h2>3.table表格实现展开闭合的效果</h2>
	<table>
		<tr>
			<td><a href="javascript:void(0)" onclick="list3(this)">菜单列表</a>
				<div>
					香菜<br /> 川菜<br /> 粤菜<br /> 云南菜<br /> 鲁菜<br />
				</div></td>
		</tr>
		<tr>
			<td><a href="javascript:void(0)" onclick="list3(this)">菜单列表</a>
				<div>
					香菜<br /> 川菜<br /> 粤菜<br /> 云南菜<br /> 鲁菜<br />
				</div></td>
		</tr>
		<tr>
			<td><a href="javascript:void(0)" onclick="list3(this)">菜单列表</a>
				<div>
					香菜<br /> 川菜<br /> 粤菜<br /> 云南菜<br /> 鲁菜<br />
				</div></td>
		</tr>
		<tr>
			<td><a href="javascript:void(0)" onclick="list3(this)">菜单列表</a>
				<div>
					香菜<br /> 川菜<br /> 粤菜<br /> 云南菜<br /> 鲁菜<br />
				</div></td>
		</tr>
		<tr>
			<td><a href="javascript:void(0)" onclick="list3(this)">菜单列表</a>
				<div>
					香菜<br /> 川菜<br /> 粤菜<br /> 云南菜<br /> 鲁菜<br />
				</div></td>
		</tr>
	</table>
</body>
</html>
