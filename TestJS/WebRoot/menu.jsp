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

<title>My JSP 'menu.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
	function selCity() {
		var arrCity = [ [ "--选择城市--" ], [ "海淀", "朝阳", "东城", "西城" ],
				[ "虹口", "杨浦", "闸北", "长宁" ], [ "济南", "青岛", "烟台", "威海" ],
				[ "洛阳", "郑州", "开封", "安阳" ] ];

		var index = document.getElementById("selid").selectedIndex;
		//window.alert(index);
		var subSelNode = document.getElementById("subselid");
		var citys = arrCity[index];
		
		//方法1：直接把长度置为0就可以一次项删除所有原先的s节点
		subSelNode.options.length = 0;
		//方法2：通过for循环删除，但是要注意，没删除一个节点列表的长度都是在动态减小的
		/*
		for(var x=0; x<subSelNode.options.length;)
		{
			subSelNode.removeChild(subSelNode.options[x]);
		}
		*/
		for(var x=0; x<citys.length; ++x)
		{
			var optNode = document.createElement("option");
			optNode.innerText = citys[x];
			subSelNode.appendChild(optNode);
		}
	}
</script>
</head>

<body>
	<select id="selid" onchange="selCity()">
		<option>--选择省份--</option>
		<option>北京</option>
		<option>上海</option>
		<option>山东</option>
		<option>河南</option>
	</select>

	<select id="subselid">
		<option>--选择城市--</option>
	</select>
</body>
</html>









