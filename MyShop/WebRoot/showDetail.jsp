<%@ page language="java" import="java.util.*,cn.pku.coolyr.model.domain.*,java.util.*,cn.pku.coolyr.model.service.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//取出要显示的信息
GoodsBean gb = (GoodsBean)request.getAttribute("goodsInfo");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/my.css">
	<script type="text/javascript">
		<!--
			//响应点击返回购物大厅的事件
			function returnHail(){

				window.open("index.jsp","_self");

			}
			//响应点击购买的事件
			function addGoods(goodsId){

				window.open("ShoppingServlet?goodsId="+goodsId+"&type=addGoods","_self");
				
			
			}
		-->
	</script>

  </head>
  
  <body  background="images/bg.bmp">
    <table align="center" width="80%" border="1" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="center">
    	<jsp:include page="head.jsp"></jsp:include>
    </td>
  </tr>
  <tr>
    <td align="center"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="abc">
      <tr>
        <td colspan="2">&nbsp;</td>
        </tr>
      <tr>
        <td width="25%" rowspan="8"><img src="images/<%=gb.getPhoto() %>" width="169" height="169" /></td>
        <td align="center"><%=gb.getGoodsName() %></td>
      </tr>
      <tr>
        <td>价格：￥<%=gb.getGoodsPrice() %></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>ISBN：<%=gb.getGoodsId() %></td>
      </tr>
      <tr>
        <td>类型：<%=gb.getType() %></td>
      </tr>
      <tr>
        <td>出版商：<%=gb.getPublisher() %></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="81" valign="top"><%=gb.getGoodsIntro() %></td>
      </tr>
      <tr>
        <td colspan="2"><form id="form1" name="form1" method="post" action="">
          <input type="button" name="button" id="button" onclick="addGoods(<%=gb.getGoodsId() %>)" value="购买" />
          <input type="button" name="button2" id="button2" onclick="returnHail()" value="返回购物大厅" />
        </form></td>
        </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center">
    <jsp:include page="tail.jsp"></jsp:include>
    </td>
  </tr>
</table>
  </body>
</html>
