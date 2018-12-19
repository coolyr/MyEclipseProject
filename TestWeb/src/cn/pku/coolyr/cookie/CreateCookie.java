package cn.pku.coolyr.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCookie extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		// 告诉浏览器使用charset=utf-8格式解析；
		response.setContentType("text/html;charset=utf-8");
		// 返回格式使用utf-8编码； --> 使用的时候两种都写上兼容性更好
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// 将用户名和密码保存到客户端
		// 创建cookie
		Cookie name = new Cookie("name", "coolyr");
		Cookie pwd = new Cookie("passwd", "123");

		// 设置时间
		name.setMaxAge(100);
		pwd.setMaxAge(100);

		// 写回到客户端 - (cookie保存：一个应用保存一份cookie文本。
		//同一个网站创建的所有cookie（name-value）会保存到一个cookie文本中)
		response.addCookie(name);
		response.addCookie(pwd);

		out.println("创建Cookies成功！！");
		out.println("</br></br><a href = '/TestWeb/ReadCookie'>读取cookie</a>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
