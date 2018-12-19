package cn.pku.coolyr.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pku.coolyr.model.domain.UsersBean;

public class ShoppingCL extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html");
		// ���servlet���ڼ����û��Ƿ��¼�������û�оͽ����¼ҳ��
		// �����¼����ֱ�ӽ�����ʾ�û���Ϣ�͹��ﳵ�����ҳ��

		// ����session���Ƿ����û���¼����Ϣ
		UsersBean ub = (UsersBean) request.getSession().getAttribute("userInfo");
		if (ub == null)
		{
			// ˵���û�û�е�¼��
			// ����ת��shopping2.jspȥ��¼
			request.getRequestDispatcher("shopping2.jsp").forward(request, response);
		}
		else
		{
			// ��¼��
			// ����ת��shopping3.jsp
			request.getRequestDispatcher("shopping3.jsp").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
