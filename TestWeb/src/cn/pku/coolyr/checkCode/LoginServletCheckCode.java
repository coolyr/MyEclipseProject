package cn.pku.coolyr.checkCode;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServletCheckCode extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 设置解析客户端使用的编码方式为：UTF-8
		request.setCharacterEncoding("utf-8");
		// 告诉客户端浏览器使用UTF-8格式解析数据
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// String u = (String) request.getAttribute("username");//该方法获取不到
		String u = request.getParameter("username");
		String p = request.getParameter("pwd");
		// 获取用户输入的验证码
		String checkCode = request.getParameter("checkCode");

		System.out.println("LoginServletCheckCode  U :: " + u);
		System.out.println("LoginServletCheckCode  P :: " + p);
		System.out.println("LoginServletCheckCode  C :: " + checkCode);

		// 从Session中获取验证码
		String sessionCheckCode = (String) request.getSession().getAttribute("checkCode");

		if (sessionCheckCode.equals(checkCode))
		{
			// 验证码输入正确->再验证用户名和密码
			request.setAttribute("OK", "验证码输入正确！！");
			request.getRequestDispatcher("/OK").forward(request, response);

		}
		else
		{
			// 验证码输入错误->返回登入界面
			request.setAttribute("ERROR", "验证码输入错误！！");
			request.getRequestDispatcher("/LoginUICheckCode").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
