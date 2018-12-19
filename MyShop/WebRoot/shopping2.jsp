<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shopping2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/my.css">

  </head>
  
  <body background="images/bg.bmp">
   <table width="80%" border="1" align="center" cellpadding="0" cellspacing="0" class="abc">
  <tr>
    <td align="center">
    	<jsp:include page="head.jsp"></jsp:include>
    </td>
  </tr>
  
  <tr>
    <td align="center"><img src="images/cartnavi-2.gif" height="49" /></td>
  </tr>
  
  <tr>
    <td align="center">
   	<form action="LoginCL" method="post">
    <table width="50%" border="1" bordercolor="#33FF00" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" align="center"><img src="images/dl.gif" width="149" height="63" /></td>
      </tr>
        <tr>
          <td width="50%" align="right">用户名：</td>
          <td width="68%" align="left"><input type="text" name="username"/></td>
        </tr>
        <tr>
          <td align="right">密 码：</td>
          <td align="left"><input type="text" name="password"/></td>
        </tr>
        <tr>
          <td align="right"><input type="submit" value="用户登录"/></td>
          <td align="left"><input name="zhuce" type="button" value="用户注册"/></td>
        </tr>
    </table>
    </form>
    </td>
  </tr>
 <tr>
    <td align="right"><img src="images/cartnext.gif" width="87" height="19" /></td>
  </tr>
  <tr>
    <td align="center">
    <jsp:include page="tail.jsp"></jsp:include>
    </td>
  </tr></table>
  </body>
</html>
