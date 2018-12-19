package cn.pku.coolyr.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��¼���� �ý���ᱻ����ʹ��,
 * ������֤ʧ�ܻ᷵�ظý���,����ִ�� 
 * ��Ƿ���¼Ҳ�᷵�ظý���,����ִ�С�
 */
public class Login extends HttpServlet
{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{

		//��������������ⷽʽ֮һ
		res.setContentType("text/html;charset=utf-8");

		//���ڴ���д����Ϣ,���û����ĵ���
		PrintWriter out = res.getWriter();

		out.println("<html>");

		// ���ñ�����ɫ
		out.println("<body bgcolor=#CED3FF>");

		// ����,������һ��ˮƽ��
		out.println("<center><hr>");

		out.println("<h1>��¼����</h1><hr>");

		// ��¼��֤ʧ��: response.sendRedirect("login?info=nameError")
		// ��ͼ�Ƿ���¼��response.sendRedirect("login?info=warError")
		
		String info = req.getParameter("info");

		if (info != null)	//һ��ʼ��¼infoһ��Ϊ��,��������֤ʧ�ܻ�Ƿ���¼��Ϊ��
		{
			if (info.equals("nameError"))
			{
				out.println("�û������������!");
			}
			else if (info.equals("warError"))
			{
				out.println("�Ƿ���¼��");
			}
			else if (info.equals("begain"))
			{
				out.println("<font color=Gray><sub>�����µ�¼!</sub></font><br>");
			}
			else if (info.equals("leave"))
			{
				out.println("<font color=Gray><sub>���Ѱ�ȫ�˳�!</sub></font><br>");
			}
		}

		// �û���¼�� �ύ��logincl������
		out.println("<form action=logincl method=post>");

		// name=username
		out.println("�û�����<input type=text name=username><br><br>");

		// name=passwd
		out.println("���룺	&nbsp;&nbsp;<input type=password name=passwd ><br><br>");

		// ��ѡ�� �������Զ���¼
		// name=keep
		out.println("<input type=checkbox name=keep value=2>�����ڲ������µ�¼<br><br>");

		out.println("<input type=submit value=����>");

		out.println("&nbsp;&nbsp;&nbsp;&nbsp;");
		out.println("<input type=reset value=����><br>");
		out.println("</form>");
		out.println("<hr></center></body>");
		out.println("</html>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
}
