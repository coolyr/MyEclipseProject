package cn.pku.coolyr.Form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInfoForm extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<form action='/TestWeb/MyInfoServlet' method='post'><br/>");
		out.println("�û���:<input type='text' name='username'/><br/>");
		out.println("�ܡ���:<input type='password' name='pwd'/><br/>");
		out.println("�ԡ���:<input type='radio' name='sex' value='��'/>�� <input type='radio' name='sex' value='Ů'/>Ů<br/>");
		out.println("��İ���:<input type='checkbox' name='hobby' value='����'>���� <input type='checkbox' name='hobby' value='����'>���� <input type='checkbox' name='hobby' value=\"����\">����<br/>");
		out.println("���ڳ���:<select name='city'><option value='bj'>����</option><option value='cq'>����</option></select><br/>");
		out.println("��Ľ���:<textarea cols='20' rows='10' name='intro' >���������..</textarea><br/>");
		out.println("�ύ��Ƭ:<input type='file' name='photo'><br/>");
		// ʲôʱ��ʹ��hidden(����λ��)�������� 1.��ϣ���û����������� 2. ��ϣ��Ӱ��ṹ���֣�ͬʱʹ�ø�����
		out.println("<input type='hidden' value='abc' name='hidden1'/>");
		out.println("<input type='submit' value='�ύ��Ϣ'/>");
		out.println("</form>");

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);
	}
}
