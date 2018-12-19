package cn.pku.coolyr.WebCount;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServletWC extends HttpServlet
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

		System.out.println("LoginServletWC  U :: " + u);
		System.out.println("LoginServletWC  P :: " + p);

		if ("123".equals(p))
		{
			String nums = (String) this.getServletContext().getAttribute("nums");
			System.out.println("num = " + nums);
			int n = Integer.parseInt(nums);
			++n;
			this.getServletContext().setAttribute("nums", n + "");
			// ʹ������ת������Ϊ������ĵ�ַ����LoginServletWC�����Դ���ˢ�¸�ҳ����������ӵ�bug
			// request.getRequestDispatcher("/ManagerWC").forward(request,
			// response);
			// ʹ���ض��򣬻�����������·���һ�����󣬴Ӷ���ַ��Ϊ��ManagerWC�����޸���Bug
			response.sendRedirect("/TestWeb/ManagerWC");
		}
		else
		{
			request.getRequestDispatcher("/LoginWC").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
