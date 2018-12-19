package cn.pku.coolyr.ServletContext;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextUI extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//获取ServletContext的两种方法
		ServletContext context1 = this.getServletContext(); 
		
		ServletContext context2 = this.getServletConfig().getServletContext();
		//添加属性
		context1.setAttribute("name" , "servletContext");
		//取出属性
		String name = (String) context2.getAttribute("name");
		out.println("name = " + name);
		out.println("<br/>");
		out.println("<br/>");
		//删除
		context2.removeAttribute("name");
		out.println(" After Del<br/> : name = " + context2.getAttribute("name"));
		
		out.println("<br/>");
		//<!-- 如果希望所有的Servlet都可以访问该配置-->
		String val = this.getServletContext().getInitParameter("context");
		out.println("context-param = " + val);
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);
	}
}
