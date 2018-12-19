package cn.pku.coolyr.checkCode;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServletCheckCode extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// ���ý����ͻ���ʹ�õı��뷽ʽΪ��UTF-8
		request.setCharacterEncoding("utf-8");
		// ���߿ͻ��������ʹ��UTF-8��ʽ��������
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// String u = (String) request.getAttribute("username");//�÷�����ȡ����
		String u = request.getParameter("username");
		String p = request.getParameter("pwd");
		// ��ȡ�û��������֤��
		String checkCode = request.getParameter("checkCode");

		System.out.println("LoginServletCheckCode  U :: " + u);
		System.out.println("LoginServletCheckCode  P :: " + p);
		System.out.println("LoginServletCheckCode  C :: " + checkCode);

		// ��Session�л�ȡ��֤��
		String sessionCheckCode = (String) request.getSession().getAttribute("checkCode");

		if (sessionCheckCode.equals(checkCode))
		{
			// ��֤��������ȷ->����֤�û���������
			request.setAttribute("OK", "��֤��������ȷ����");
			request.getRequestDispatcher("/OK").forward(request, response);

		}
		else
		{
			// ��֤���������->���ص������
			request.setAttribute("ERROR", "��֤��������󣡣�");
			request.getRequestDispatcher("/LoginUICheckCode").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
