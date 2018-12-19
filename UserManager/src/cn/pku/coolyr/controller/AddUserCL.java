package cn.pku.coolyr.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pku.coolyr.service.UserService;

public class AddUserCL extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=gbk");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");

		// ����UserDAO�е������û��ķ���
		if (new UserService().addUser(username, passwd, email, grade))
		{
			// �޸ĳɹ�
			response.sendRedirect("ok");
		}
		else
		{
			// �޸�ʧ��
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
