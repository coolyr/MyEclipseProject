<%@ page language="java" import="java.util.*,cn.pku.coolyr.model.domain.*,java.util.*,cn.pku.coolyr.model.service.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//得到用户信息
UsersBean ub = (UsersBean)session.getAttribute("userInfo");
//得到购物车的信息
List list= (List)request.getAttribute("list");
//得到购物车
MyCartBO mcb = (MyCartBO)session.getAttribute("mycarbo");
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
    <td align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center"><img src="images/cartnavi-3.gif"  height="49" /></td>
      </tr>
      <tr>
        <td align="center"><table width="70%" border="1" cellpadding="0" cellspacing="0" class="abc">
          <tr>
            <td colspan="2" align="center">购买人的信息</td>
            </tr>
          <tr>
            <td width="50%" align="right">用 户 名：</td>
            <td align="left"><input type="text" name="textfield" id="textfield" value="<%=ub.getUsername() %>"/></td>
          </tr>
          <tr>
            <td align="right">*真实姓名：</td>
            <td align="left"><input type="text" name="textfield2" id="textfield2" value="<%=ub.getTruename() %>"/></td>
          </tr>
          <tr>
            <td align="right">*家庭住址：</td>
            <td align="left"><input type="text" name="textfield3" id="textfield3" value="<%=ub.getAddress() %>"/></td>
          </tr>
          <tr>
            <td align="right">*联系电话：</td>
            <td align="left"><input type="text" name="textfield4" id="textfield4" value="<%=ub.getPhone() %>"/></td>
          </tr>
          <tr>
            <td align="right">*电子邮件：</td>
            <td align="left"><input type="text" name="textfield5" id="textfield5" value="<%=ub.getEmail() %>"/></td>
          </tr>
          <tr>
            <td align="right">*邮   编：</td>
            <td align="left"><input type="text" name="textfield6" id="textfield6" value="<%=ub.getPostcode() %>"/></td>
          </tr>
          <tr>
            <td align="right"><input type="button" name="button" id="button" value="完成订单" /></td>
            <td align="left"><input type="button" name="button2" id="button2" value="修改个人信息" /></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td align="center"><table width="70%" border="1" cellpadding="0" cellspacing="0" class="abc">
          <tr>
            <td colspan="4" align="center">我的购物车情况</td>
            </tr>
          <tr>
            <td width="25%" align="center">编号</td>
            <td width="25%" align="center">商品名称</td>
            <td width="25%" align="center">单价</td>
            <td width="25%" align="center">数量</td>
          </tr>
          <%
          
          	for(int i=0;i<list.size();i++){
          		GoodsBean gb = (GoodsBean)list.get(i);
          		%>
          		
		          <tr>
		            <td align="center"><%=gb.getGoodsId() %></td>
		            <td align="center"><%=gb.getGoodsName() %></td>
		            <td align="center">￥<%=gb.getGoodsPrice() %></td>
		            <td align="center"><%=mcb.getGoodsNum(gb.getGoodsId()+"") %></td>
		          </tr>
          		
          		<%
          	}
          
          %>
          
          <tr>
            <td colspan="4" align="left">您共选择了价值￥<%=mcb.getAllPrice() %>的商品</td>
            </tr>
          <tr>
            <td colspan="4" align="center"><input type="button" name="button3" id="button3" value="返回修改我的购物车" /></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td align="right"><img src="images/cartpre.gif" width="87" height="19" /><a href="OrderCL"><img border="0" src="images/cartnext.gif" width="87" height="19" /></a></td>
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
