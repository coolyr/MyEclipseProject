package cn.pku.coolyr.checkCode;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginUICheckCode extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<form action='/TestWeb/LoginServletCheckCode' method='post'><br/>");
		out.println("�û���:<input type='text' name='username'/><br/>");
		out.println("�ܡ���:<input type='password' name='pwd'/><br/>");
		out.println("��֤��:<input type='text' name='checkCode'/><img src = '/TestWeb/CreateCheckCode'/><br/>");

		out.println("<input type='submit' value='�ύ��Ϣ'/><br/>");
		out.println("</form>");

		String ERROR = (String) request.getAttribute("ERROR");

		if (ERROR != null)
		{
			out.println(ERROR);
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);
	}

}
