package cn.pku.coolyr.Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// ���ý����ͻ���ʹ�õı��뷽ʽΪ��UTF-8
		request.setCharacterEncoding("utf-8");
		// ���߿ͻ��������ʹ��UTF-8��ʽ��������
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		// String u = (String) request.getAttribute("username");//�÷�����ȡ����
		String u = request.getParameter("username");
		String p = request.getParameter("pwd");
		
		System.out.println("LoginServlet  U :: " + u);
		System.out.println("LoginServlet  P :: " + p);
		
		request.getRequestDispatcher("/LoginDispatcherServlet").forward(request, response);
		
//		out.println("�û��� = " + u + "<br/>");
//		out.println("�ܡ��� = " + p + "<br/>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
