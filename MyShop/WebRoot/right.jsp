<%@ page language="java" import="java.util.*,cn.pku.coolyr.model.domain.*,java.util.*,cn.pku.coolyr.model.service.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//分页显示
GoodsBeanBO ggb = new GoodsBeanBO();
String s_pageNow = (String)request.getAttribute("abc");
int pageNow = 1;
if(s_pageNow!=null){
	pageNow = Integer.parseInt(s_pageNow);
}
//默认显示第一页
List list = ggb.getGoodsList(6,pageNow);
//共有多少页
int pageCount = ggb.getPageCount(6);
%>

<table  width="100%"  height="100%"   class="abc" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" height="31"><img src="images/untitled.bmp" width="100%" height="63" /></td>
  </tr>
    <%	
    	int time =0;
    	for(int i=0;i<2;i++){
    %>
    	<tr>
    <%
    		
    		for(int j=0;j<3;j++){
    			GoodsBean gb = new GoodsBean();
    			if(time>=list.size()){
    				gb.setGoodsId(0);
    				gb.setGoodsIntro("没有书了");
    				gb.setGoodsName("荷花图");
    				gb.setGoodsNum(0);
    				gb.setGoodsPrice(0);
    				gb.setPhoto("none.jpg");
    			}else{
    				gb = (GoodsBean)list.get(time);
        			time++;
    			}
    			
    			
    %>
    <td width="33%" height="154" align="center"><table width="100%" height="170"  cellpadding="0" cellspacing="0" class="abc">
      <tr>
        <td width="32%" rowspan="3"><img src="images/<%=gb.getPhoto() %>" alt="" width="112" height="112" /></td>
        <td width="68%">&nbsp;</td>
      </tr>
      <tr>
        <td><a href="ShowGoodsServlet?type=showDetail&id=<%=gb.getGoodsId() %>"><%=gb.getGoodsName() %></a></td>
      </tr>
      <tr>
        <td>价格：$<%=gb.getGoodsPrice() %></td>
      </tr>
      <tr>
        <td height="21" colspan="2">简单介绍：<%=gb.getGoodsIntro() %></td>
      </tr>
    </table></td>
    <%
    	}
    %>
    	</tr>
    <%
    	//判断是否是第二大行
    	if(i==0){
    		%>
			   <tr>
			    <td height="20" colspan="3" align="center" bgcolor="pink">&nbsp;</td>
			  </tr>
    		<%
    	}
    }
    %>
  
  

  <tr>
    <td colspan="3" align="center" height="25" >
  	<%
  		for(int i =1;i<=pageCount;i++){
  		%>
  			
  			<a href="ShowGoodsServlet?pageNow=<%=i %>&type=fenye">【<%=i %>】</a>
  		
  		<%
  			
  		}
  	%>  
   </td>
  </tr>
</table>