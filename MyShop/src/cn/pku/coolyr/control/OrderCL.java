package cn.pku.coolyr.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pku.coolyr.model.domain.OrderInfoBean;
import cn.pku.coolyr.model.domain.UsersBean;
import cn.pku.coolyr.model.service.MyCartBO;
import cn.pku.coolyr.model.service.OrdersBeanBO;
import cn.pku.coolyr.model.util.SendMailToSomeone;

public class OrderCL extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html");

		// 处理完订单将订单写入数据库
		OrdersBeanBO obb = new OrdersBeanBO();
		// 得到购物车
		MyCartBO mbo = (MyCartBO) request.getSession().getAttribute("mycarbo");
		// 得到用户id号
		UsersBean ub = (UsersBean) request.getSession().getAttribute("userInfo");
		int usersId = ub.getUserid();

		// 得到orderInfoBean
		OrderInfoBean oib = obb.addOrder(mbo, usersId + "");

		if (oib != null)
		{
			// 添加成功
			// 准备显示订单的详细信息的数据，给shopping4.jsp显示
			// 发送电子邮件
			SendMailToSomeone smts = new SendMailToSomeone();

			String mailbody = "<table width=\"70%\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"comm\">" + "<tr>" + "<td align=\"center\">订单号</td>"
					+ "<td align=\"center\">用户名称</td>" + "<td align=\"center\">价格</td>" + "<td align=\"center\">用户电话</td>" + "</tr>";
			// String mail =null;
			// ArrayList al=obb.getOrderDetailBean(obb.getOrderId());
			// for(int i=0;i<al.size();i++){
			// OrderDetailBean odb=(OrderDetailBean)al.get(i);

			mailbody += "<tr>" + "<td align=\"center\">" + oib.getOrdersId() + "</td>" + "<td align=\"center\">" + oib.getTruename() + "</td>" + "<td align=\"center\">" + oib.getTotalPrice()
					+ "元</td>" + "<td align=\"center\">" + oib.getPhone() + "</td>" + "</tr>";
			// }

			mailbody += "</table>";
			// 发送的行为【这是真的把订单发送到客户注册的邮件中】

			//这有问题？！！
			//smts.send("你在时尚购物网有订单", mailbody, "1531271510@qq.com", "1531271510@qq.com", "coolyr@wyr+127122", "smtp.qq.com");
			request.setAttribute("orderInfoBean", oib);
			request.getRequestDispatcher("shopping4.jsp").forward(request, response);
		}
		else
		{
			// 添加失败
			request.getRequestDispatcher("shopping3.sp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);
	}

}
