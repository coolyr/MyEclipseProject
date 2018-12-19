package cn.pku.coolyr.WebCount;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServletWC extends HttpServlet
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

		System.out.println("LoginServletWC  U :: " + u);
		System.out.println("LoginServletWC  P :: " + p);

		if ("123".equals(p))
		{
			String nums = (String) this.getServletContext().getAttribute("nums");
			System.out.println("num = " + nums);
			int n = Integer.parseInt(nums);
			++n;
			this.getServletContext().setAttribute("nums", n + "");
			// 使用请求转发，因为浏览器的地址还是LoginServletWC，所以存在刷新该页面会人数增加的bug
			// request.getRequestDispatcher("/ManagerWC").forward(request,
			// response);
			// 使用重定向，会让浏览器重新发送一次请求，从而地址栏为：ManagerWC，会修复该Bug
			response.sendRedirect("/TestWeb/ManagerWC");
		}
		else
		{
			request.getRequestDispatcher("/LoginWC").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
