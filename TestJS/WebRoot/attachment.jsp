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

<title>My JSP 'attachh.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
table td a:LINK,table td a:VISITED {
	text-decoration: none;
	color: #0099FF;
}

table,table td {
	border: #0099CC 1px solid;
}
</style>

<script type="text/javascript">

var count = 1;
	function addFile(){
		var tabNode = document.getElementsByTagName("table")[0];
		
		var trNode = tabNode.insertRow();
		trNode.id = "tr_"+count;
		
		var tdNode_file = trNode.insertCell();
		var tdNode_del = trNode.insertCell();
		
		tdNode_file.innerHTML = "<input type='file'>";
		tdNode_del.innerHTML = "<a href='javascript:void(0)' onclick='delFile(this)'>删除附件"+count+"</a>";
		
		count++;
	}
	
	function delFile(node){

		var trNode = node.parentNode.parentNode;
		trNode.parentNode.removeChild(trNode);
	}
</script>
</head>

<body>
	<table>
		<tr>
			<td><a href="javascript:void()" onclick="addFile()">添加附件</a></td>
		</tr>
	</table>
</body>
</html>









