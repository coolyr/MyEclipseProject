package cn.pku.coolyr.Form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInfoServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//设置解析客户端使用的编码方式为：UTF-8
		request.setCharacterEncoding("utf-8");
		//告诉客户端浏览器使用UTF-8格式解析数据
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//String u = (String) request.getAttribute("username");//该方法获取不到
		String u = request.getParameter("username");
		String p = request.getParameter("pwd");
		String sex = request.getParameter("sex");
		// 如果接受复选框的内容，则使用getparameterValues
		String[] hobbies = request.getParameterValues("hobby");
		String city = request.getParameter("city");
		String intro = request.getParameter("intro");
		String hidden1 = request.getParameter("hidden1");
		out.println("用户名=" + u + "<br/>");
		out.println("密　码=" + p + "<br/>");
		out.println("性  别=" + sex + "<br/>");
		if (hobbies != null)
		{
			for (int i = 0; i < hobbies.length; i++)
			{
				out.println("爱好:" + hobbies[i]);
			}
		}
		else
		{
			out.println("你没有爱好");
		}
		out.println("<br/>所在城市:" + city);
		out.println("<br/>个人介绍:" + intro);
		out.println("<br/>隐藏控件数据:" + hidden1);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
}
