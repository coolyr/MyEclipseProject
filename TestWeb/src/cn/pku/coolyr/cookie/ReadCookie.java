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

		//���������ʹ��charset=utf-8��ʽ������
		response.setContentType("text/html;charset=utf-8");
		//���ظ�ʽʹ��utf-8���룻   --> ʹ�õ�ʱ�����ֶ�д�ϼ����Ը���
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		//������ֻ����ܱ�Ӧ�õ�����cookie
		Cookie []allCookies = request.getCookies();

		//���û��cookie����Ϊnull
		if(allCookies!= null)
		{
			//����ɸѡ���Լ���Ҫ��cookie��Ϣ
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
