package cn.pku.coolyr.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadCookie extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		//告诉浏览器使用charset=utf-8格式解析；
		response.setContentType("text/html;charset=utf-8");
		//返回格式使用utf-8编码；   --> 使用的时候两种都写上兼容性更好
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		//服务器只会接受本应用的所有cookie
		Cookie []allCookies = request.getCookies();

		//如果没有cookie，则为null
		if(allCookies!= null)
		{
			//迭代筛选出自己需要的cookie信息
			for(int i=0; i<allCookies.length; i++)
			{
				System.out.println("name: " + allCookies[i].getName() + " & " + "value: " + allCookies[i].getValue());
				out.println("</br></br>name: " + allCookies[i].getName() + " & " + "value: " + allCookies[i].getValue());
			}

		}
		
		
		out.flush();
		out.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
