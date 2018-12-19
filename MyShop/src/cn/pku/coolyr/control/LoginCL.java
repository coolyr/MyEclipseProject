//���servlet������֤�û��������Ƿ���ȷ
package cn.pku.coolyr.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pku.coolyr.model.domain.GoodsBean;
import cn.pku.coolyr.model.domain.UsersBean;
import cn.pku.coolyr.model.service.MyCartBO;
import cn.pku.coolyr.model.service.UsersBeanBO;

public class LoginCL extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html");
		// �õ��û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// ��֤�û�
		UsersBeanBO ubo = new UsersBeanBO();
		if (ubo.checkUser(username, password))
		{
			// �û��Ϸ�
			// 1.���û��ɹ���½����Ϣ����session
			UsersBean ub = ubo.getUsersBean(username);
			request.getSession().setAttribute("userInfo", ub);
			// 2.�ѹ��ﳵȡ����׼������һҳ����ʾ??
			MyCartBO mcb = (MyCartBO) request.getSession().getAttribute("mycarbo");
			List<GoodsBean> list = mcb.showMyCar();
			// 3.�����ﳵ��Ϣ����request��
			request.setAttribute("list", list);

			request.getRequestDispatcher("shopping3.jsp").forward(request, response);
		}
		else
		{
			// �û����Ϸ�
			request.getRequestDispatcher("shopping2.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);
	}

}
