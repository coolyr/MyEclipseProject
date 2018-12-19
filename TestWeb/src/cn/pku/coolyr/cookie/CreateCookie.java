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

		// ���������ʹ��charset=utf-8��ʽ������
		response.setContentType("text/html;charset=utf-8");
		// ���ظ�ʽʹ��utf-8���룻 --> ʹ�õ�ʱ�����ֶ�д�ϼ����Ը���
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// ���û��������뱣�浽�ͻ���
		// ����cookie
		Cookie name = new Cookie("name", "coolyr");
		Cookie pwd = new Cookie("passwd", "123");

		// ����ʱ��
		name.setMaxAge(100);
		pwd.setMaxAge(100);

		// д�ص��ͻ��� - (cookie���棺һ��Ӧ�ñ���һ��cookie�ı���
		//ͬһ����վ����������cookie��name-value���ᱣ�浽һ��cookie�ı���)
		response.addCookie(name);
		response.addCookie(pwd);

		out.println("����Cookies�ɹ�����");
		out.println("</br></br><a href = '/TestWeb/ReadCookie'>��ȡcookie</a>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
