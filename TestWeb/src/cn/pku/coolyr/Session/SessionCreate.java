package cn.pku.coolyr.Session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionCreate extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//����һ��session��������һ������
		HttpSession session = request.getSession();
		session.setAttribute("session", "create");

		//Ĭ��30min
		
		//��session ID��д��cookie
		//cookie���ֱ���Ϊ 'JSESSION'  ���ִ�Сд
		Cookie cookie = new Cookie("JSESSION",session.getId());
		cookie.setMaxAge(60*30);
		response.addCookie(cookie);
		
		out.println("����Session�������� session = create");
		out.println("<br/><a href='/TestWeb/SessionShow'>����session</a>");
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);
	}

}
