package cn.pku.coolyr.Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginDispatcherServlet extends HttpServlet
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
		
		System.out.println("LoginDispatcherServlet  U :: " + u);
		System.out.println("LoginDispatcherServlet  P :: " + p);
		
		out.println("用户名 = " + u + "<br/>");
		out.println("密　码 = " + p + "<br/>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}


}
