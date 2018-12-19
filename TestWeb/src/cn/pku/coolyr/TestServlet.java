package cn.pku.coolyr;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet
{

	int i = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		//告诉浏览器使用charset=utf-8格式解析；
		response.setContentType("text/html;charset=utf-8");
		//返回格式使用utf-8编码；   --> 使用的时候两种都写上兼容性更好
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		++i;
		out.println("王云荣    :: "+ i +" :: "+ new java.util.Date());
		out.flush();
		out.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
