<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="/css/my.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	margin-top: 0px;
	margin-bottom: 0px;
}
</style>

<table  width="100%%" height="53%"  class="abc" cellpadding="0" cellspacing="0">
  <tr>
    <td height="10" colspan="3" bgcolor="#FF9999">&nbsp;</td>
  </tr>
  <tr>
    <td width="20%" height="60"><img src="images/logo.gif"  width="128" height="97" /></td>
    <td width="63%"><img src="images/test.gif" width="300"  height="99" /></td>
    <td width="200"><p><img src="images/account.gif" width="19" height="14" /><a href="login.jsp">【我的账号】</a></p>
    <p><img src="images/cart.gif" width="20" height="15" /><a href="ShoppingServlet?type=show">【我的购物车】</a></p></td>
  </tr>
  <tr>
    <td height="10" colspan="3" bgcolor="#FF9999">&nbsp;</td>
  </tr>
  <tr>
    <td height="21"  colspan="3"><table width="100%"  cellpadding="0" cellspacing="0">
      <tr>
        <td width="11%" valign="top">&nbsp;</td>
        <td width="11%" class="color"><a href="index.jsp">首页</a></td>
        <td width="11%">&nbsp;</td>
        <td width="11%" class="color">香港电影</td>
        <td width="11%">&nbsp;</td>
        <td width="11%" class="color">大陆电影</td>
        <td width="11%">&nbsp;</td>
        <td width="11%" class="color">关于我们</td>
        <td width="11%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>