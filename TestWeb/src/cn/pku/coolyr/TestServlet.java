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

		//���������ʹ��charset=utf-8��ʽ������
		response.setContentType("text/html;charset=utf-8");
		//���ظ�ʽʹ��utf-8���룻   --> ʹ�õ�ʱ�����ֶ�д�ϼ����Ը���
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		++i;
		out.println("������    :: "+ i +" :: "+ new java.util.Date());
		out.flush();
		out.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
