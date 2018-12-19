package cn.pku.coolyr.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pku.coolyr.service.UserService;

public class UpdateCL extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=gbk");
		PrintWriter out = response.getWriter();

		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");

		// 调用UserService中的删除用户的方法
		if (new UserService().updateUser(userId, username, passwd, email, grade))
		{
			// 修改成功
			response.sendRedirect("ok");
		}
		else
		{
			// 修改失败
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
