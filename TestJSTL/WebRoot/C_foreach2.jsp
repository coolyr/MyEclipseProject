<%@page import="cn.pku.coolyr.Domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'C_foreach.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>

	<h4>迭代Map集合</h4>
	<%
		Map map = new HashMap();
		//map.put("aa","admin");
		//map.put("bb","scott");
		User u1 = new User();
		u1.setUsername("coolyr");
		u1.setPassword("123");
		User u2 = new User();
		u2.setUsername("xiaoming");
		u2.setPassword("456");
		map.put("u1", u1);
		map.put("u2", u2);
		request.setAttribute("persons", map);
	%>
	<c:forEach items="${persons}" var="per">
      key:${per.key }<br />
      value-name: ${per.value.username }<br />
      value-password:${per.value.password }<br />
	</c:forEach>
	<br />
	
	<h4>迭代Set集合</h4>
	<%
		Set sets = new HashSet();
		User u3 = new User();
		User u4 = new User();
		u3.setUsername("hades");
		u3.setPassword("123");
		u4.setUsername("hades");
		u4.setPassword("123");
		sets.add(u3);
		sets.add(u4);
		request.setAttribute("userSets", sets);
	%>
	<c:forEach items="${userSets}" var="myusers">
      value-name: ${myusers.username }<br />
      value-password:${myusers.password }<br />
		<c:if test="${!empty userSets}">有人</c:if>
		<br />
	</c:forEach>



</body>
</html>
