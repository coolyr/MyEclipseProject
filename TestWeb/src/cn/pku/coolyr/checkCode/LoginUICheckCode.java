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
		out.println("用户名:<input type='text' name='username'/><br/>");
		out.println("密　码:<input type='password' name='pwd'/><br/>");
		out.println("验证码:<input type='text' name='checkCode'/><img src = '/TestWeb/CreateCheckCode'/><br/>");

		out.println("<input type='submit' value='提交信息'/><br/>");
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
