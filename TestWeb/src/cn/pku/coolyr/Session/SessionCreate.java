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
		
		//创建一个session，并放入一个属性
		HttpSession session = request.getSession();
		session.setAttribute("session", "create");

		//默认30min
		
		//把session ID回写到cookie
		//cookie名字必须为 'JSESSION'  区分大小写
		Cookie cookie = new Cookie("JSESSION",session.getId());
		cookie.setMaxAge(60*30);
		response.addCookie(cookie);
		
		out.println("创建Session，并放入 session = create");
		out.println("<br/><a href='/TestWeb/SessionShow'>访问session</a>");
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);
	}

}
