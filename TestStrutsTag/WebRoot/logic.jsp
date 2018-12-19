<%@page import="cn.pku.coolyr.Domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入Struts标签库中的logic标签库 -->
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!-- 引入Struts标签库中的bean标签库 -->
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'html.jsp' starting page</title>

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
	<h4>0.logic:iterate迭代输出： users：要输出的集合 user:每个对象</h4>
	<%
		User u1 = new User();
		u1.setUsername("coolyr");
		u1.setPassword("123");
		User u2 = new User();
		u2.setUsername("hades");
		u2.setPassword("456");
		User u3 = new User();
		u3.setUsername("poochai");
		u3.setPassword("789");
		ArrayList<User> al = new ArrayList<User>();
		al.add(u1);
		al.add(u2);
		al.add(u3);
		request.setAttribute("users", al);
	%>
	<logic:iterate id="user" name="users">
		<!-- 输出每个user对象的username和password属性 -->
		<bean:write name="user" property="username" />
		<bean:write name="user" property="password" />
		<br />
	</logic:iterate>

	<h4>1.logic:empty测试域对象中的属相对象ee是否为空</h4>
	<logic:empty name="ee">
     	 属性ee不存在
   </logic:empty>
   
	<h4>2.logic:notEmpty测试域对象中的属性对象users是否存在</h4>
	<logic:notEmpty name="users">
      users属性存在
   </logic:notEmpty>

	<h4>3.logic:greaterThan测试</h4>
	<!-- 迭代users集合对象 -->
	<logic:iterate id="myuser" name="users">
		<!-- 比较每个对象myuser的password属性的值与200的大小 -->
		<logic:greaterThan value="200" property="password" name="myuser">
			<bean:write name="myuser" property="username" /> 密码大于二十十<br />
		</logic:greaterThan>
	</logic:iterate>
</body>
</html>


