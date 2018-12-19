<%@ page language="java" import="java.util.*,cn.pku.coolyr.model.domain.*,java.util.*,cn.pku.coolyr.model.service.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//取出放在request中的list
List<GoodsBean> list = (List<GoodsBean>)request.getAttribute("list");
//从session中取出购物车
MyCartBO mbo = (MyCartBO)session.getAttribute("mycarbo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showMyCar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/my.css">
<script type="text/javascript">

<!--
	//响应删除所有的事件
	function deleteAll(){

		window.open("ShoppingServlet?type=deleteAll","_self");
	
	}

-->


</script>

  </head>
  
  <body background="images/bg.bmp">
    <table width="80%" border="1" align="center" cellpadding="0" cellspacing="0" class="abc" >
  <tr>
    <td align="center">
    	<jsp:include page="head.jsp"></jsp:include>
    </td>
  </tr>
  <tr>
    <td align="center"><img src="images/cartnavi-1.gif"  height="49" /></td>
  </tr>
  <tr>
    <td>
    <form action="ShoppingServlet?type=update" method="post">
    <table width="100%" border="1" cellspacing="0" cellpadding="0" class="abc">
      <tr>
        <td width="25%" align="center">编号</td>
        <td width="25%" align="center">名称</td>
        <td width="25%" align="center">单价</td>
        <td colspan="3" align="center">数量</td>
      </tr>
    <%
    	for(int i=0;i<list.size();i++){
    		
    		GoodsBean gb = list.get(i);
    		
    		%>
    		
    	<tr>
        <td align="center"><%=gb.getGoodsId() %></td>
        <td align="center"><%=gb.getGoodsName() %></td>
        <td align="center">￥<%=gb.getGoodsPrice() %></td>
        <td width="8%" align="center">
          <input type="hidden" name="goodsId" value="<%=gb.getGoodsId() %>">	
          <input name="newNum" type="text" id="textfield" size="12" value="<%=mbo.getGoodsNum(gb.getGoodsId()+"") %>" /></td>
        <td width="8%" align="center"><a href="ShoppingServlet?type=delete&goodsId=<%=gb.getGoodsId() %>">删除</a></td>
        <td width="8%" align="center"><a href="ShowGoodsServlet?type=showDetail&id=<%=gb.getGoodsId() %>">查看</a></td>
      </tr>
    		
    		<%
    		
    	}
    
    %>
     
      <tr>
        <td>&nbsp;</td>
        <td align="center"><input type="button" name="button3" id="button3" onclick="deleteAll()"  value="删除全部书籍" /></td>
        <td align="center"><input type="submit" name="button4" id="button4" value="修 改 数 量" /></td>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="6">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="2">您共选择了价值￥<%=mbo.getAllPrice() %>的商品，点<a href="index.jsp">此处</a>继续购买</td>
        <td colspan="4" align="right"><a href="ShoppingCL"><img src="images/cartnext.gif" width="87" height="19" /></a></td>
        </tr>
    </table>
    </form>
    </td>
  </tr>
  <tr>
    <td align="center">
    <jsp:include page="tail.jsp"></jsp:include>
    </td>
  </tr>
</table>
  </body>
</html>
