package cn.pku.coolyr.Form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInfoServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//���ý����ͻ���ʹ�õı��뷽ʽΪ��UTF-8
		request.setCharacterEncoding("utf-8");
		//���߿ͻ��������ʹ��UTF-8��ʽ��������
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//String u = (String) request.getAttribute("username");//�÷�����ȡ����
		String u = request.getParameter("username");
		String p = request.getParameter("pwd");
		String sex = request.getParameter("sex");
		// ������ܸ�ѡ������ݣ���ʹ��getparameterValues
		String[] hobbies = request.getParameterValues("hobby");
		String city = request.getParameter("city");
		String intro = request.getParameter("intro");
		String hidden1 = request.getParameter("hidden1");
		out.println("�û���=" + u + "<br/>");
		out.println("�ܡ���=" + p + "<br/>");
		out.println("��  ��=" + sex + "<br/>");
		if (hobbies != null)
		{
			for (int i = 0; i < hobbies.length; i++)
			{
				out.println("����:" + hobbies[i]);
			}
		}
		else
		{
			out.println("��û�а���");
		}
		out.println("<br/>���ڳ���:" + city);
		out.println("<br/>���˽���:" + intro);
		out.println("<br/>���ؿؼ�����:" + hidden1);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
}
