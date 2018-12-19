package cn.pku.coolyr.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pku.coolyr.service.UserService;

public class DeleteUser extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=gbk");
		PrintWriter out = response.getWriter();

		String id = request.getParameter("userId");

		// ����UserDAO�е�ɾ���û��ķ���
		if (new UserService().deleteUser(id))
		{
			// ɾ���ɹ�
			response.sendRedirect("ok");
		}
		else
		{
			// ɾ��ʧ��
			response.sendRedirect("error");
		}

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
