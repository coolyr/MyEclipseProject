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
		
		//��ȡServletContext�����ַ���
		ServletContext context1 = this.getServletContext(); 
		
		ServletContext context2 = this.getServletConfig().getServletContext();
		//�������
		context1.setAttribute("name" , "servletContext");
		//ȡ������
		String name = (String) context2.getAttribute("name");
		out.println("name = " + name);
		out.println("<br/>");
		out.println("<br/>");
		//ɾ��
		context2.removeAttribute("name");
		out.println(" After Del<br/> : name = " + context2.getAttribute("name"));
		
		out.println("<br/>");
		//<!-- ���ϣ�����е�Servlet�����Է��ʸ�����-->
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
