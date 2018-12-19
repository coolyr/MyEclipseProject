<%@ page language="java" import="java.util.*,cn.pku.coolyr.model.domain.*,java.util.*,cn.pku.coolyr.model.service.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//订单的详细信息
OrderInfoBean oib = (OrderInfoBean)request.getAttribute("orderInfoBean");
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
 <tr>
    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="abc">
      <tr>
        <td colspan="9" align="center"><img  src="images/cartnavi-4.gif"> </td>
        </tr>
      <tr>
        <td colspan="9" align="center">订单详细信息</td>
        </tr>
      <tr>
        <td align="center">订单号</td>
        <td align="center">收货人</td>
        <td align="center">收货地址</td>
        <td align="center">邮编</td>
        <td align="center">电话</td>
        <td align="center">总价</td>
        <td align="center">用户名</td>
        <td align="center">电子邮件</td>
        <td align="center">&nbsp;</td>
      </tr>
      <tr>
        <td align="center"><%=oib.getOrdersId() %></td>
        <td align="center"><%=oib.getTruename() %></td>
        <td align="center"><%=oib.getAddress() %></td>
        <td align="center"><%=oib.getPostcode() %></td>
        <td align="center"><%=oib.getPhone() %></td>
        <td align="center">￥<%=oib.getTotalPrice() %></td>
        <td align="center"><%=oib.getUsername() %></td>
        <td align="center"><%=oib.getEmail() %></td>
        <td align="center"><a href="#">查看详情</a></td>
      </tr>
      <tr>
        <td colspan="9" align="center">您的订单已经完成，服务器将在稍后向您的电子邮箱发送确认信息，并确认订单信息。</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center">
    <jsp:include page="tail.jsp"></jsp:include>
    </td>
  </tr></table>
  </body>
</html>
