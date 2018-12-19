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

		// �����궩��������д�����ݿ�
		OrdersBeanBO obb = new OrdersBeanBO();
		// �õ����ﳵ
		MyCartBO mbo = (MyCartBO) request.getSession().getAttribute("mycarbo");
		// �õ��û�id��
		UsersBean ub = (UsersBean) request.getSession().getAttribute("userInfo");
		int usersId = ub.getUserid();

		// �õ�orderInfoBean
		OrderInfoBean oib = obb.addOrder(mbo, usersId + "");

		if (oib != null)
		{
			// ��ӳɹ�
			// ׼����ʾ��������ϸ��Ϣ�����ݣ���shopping4.jsp��ʾ
			// ���͵����ʼ�
			SendMailToSomeone smts = new SendMailToSomeone();

			String mailbody = "<table width=\"70%\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"comm\">" + "<tr>" + "<td align=\"center\">������</td>"
					+ "<td align=\"center\">�û�����</td>" + "<td align=\"center\">�۸�</td>" + "<td align=\"center\">�û��绰</td>" + "</tr>";
			// String mail =null;
			// ArrayList al=obb.getOrderDetailBean(obb.getOrderId());
			// for(int i=0;i<al.size();i++){
			// OrderDetailBean odb=(OrderDetailBean)al.get(i);

			mailbody += "<tr>" + "<td align=\"center\">" + oib.getOrdersId() + "</td>" + "<td align=\"center\">" + oib.getTruename() + "</td>" + "<td align=\"center\">" + oib.getTotalPrice()
					+ "Ԫ</td>" + "<td align=\"center\">" + oib.getPhone() + "</td>" + "</tr>";
			// }

			mailbody += "</table>";
			// ���͵���Ϊ��������İѶ������͵��ͻ�ע����ʼ��С�

			//�������⣿����
			//smts.send("����ʱ�й������ж���", mailbody, "1531271510@qq.com", "1531271510@qq.com", "coolyr@wyr+127122", "smtp.qq.com");
			request.setAttribute("orderInfoBean", oib);
			request.getRequestDispatcher("shopping4.jsp").forward(request, response);
		}
		else
		{
			// ���ʧ��
			request.getRequestDispatcher("shopping3.sp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);
	}

}
