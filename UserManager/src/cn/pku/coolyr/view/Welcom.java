package cn.pku.coolyr.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import cn.pku.coolyr.bean.User;
import cn.pku.coolyr.service.UserService;

public class Welcom extends HttpServlet
{
	/*
	 * ���Session(20��)ʱ�䵽�˻���ô��?
	 * 
	 * 1.��֤�ɹ�,����������,û�н���Welcom����,����Sessionʱ�䵽��.
	 * 
	 * 2.�������Cookie��û�б����û���������,�����ʾ�Ƿ���¼
	 * 
	 * 3.���Cookie������,��ʱ��������Welcom�������Զ�ȥ��֤Cookie�е���Ϣ, ��Ҫ˫������ ���ܽ���welcom����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		// ��������
		response.setContentType("text/html;charset=gbk");

		PrintWriter out = response.getWriter();

		// �õ��ڷ������˵�session�ڴ��е���Ϣ(3������),����Ϣ��
		// �ڵ�¼�ɹ�ʱ,���õ�
		HttpSession hs = request.getSession(true);
		String val = (String) hs.getAttribute("pass");
		String u = (String) hs.getAttribute("uname");
		String pwd = (String) hs.getAttribute("pwd");

		System.out.println("Welcom ?" + val);

		String myname = "";
		String mypwd = "";

		// û�о�����֤,��������Session��Ҳ��û����Ϣ
		if (val == null)
		{
			// �Ƿ���¼

			// ���sessionû���û���Ϣ���Ƿ���¼,�ڿ�����û��cookie��Ϣ,����У���2���ǲ��õ�¼��
			Cookie[] allCookies = request.getCookies();

			int i = 0;

			// ���allCookies��Ϊ��
			if (allCookies != null)
			{
				System.out.println("=========");
				// ����ȡ��cookie
				for (i = 0; i < allCookies.length; i++)
				{
					// ����ȡ��
					Cookie temp = allCookies[i];

					if (temp.getName().equals("myname"))
					{
						// �õ�cookie��ֵ
						myname = temp.getValue();

						System.out.println("Welcom name = " + myname + "keep ��Ϊ null");
					}
					else if (temp.getName().equals("mypwd"))
					{
						// �õ�cookie��ֵ
						mypwd = temp.getValue();

						System.out.println("Welcom pwd = " + mypwd);
					}

				}
				System.out.println("============");
				// Cookie��myname��mypwdֵ�в�Ϊ��,������,��Ҫȥ�Զ�ȥ��֤��¼����¼�ɹ�����������

				System.out.println("Cookie��myname��mypwdֵ�в�Ϊ��?  " + (!myname.equals("") && !mypwd.equals("")));

				if (!myname.equals("") && !mypwd.equals(""))
				{
					System.out.println("��loguiclȥ��֤Cookie");
					// ��loguiclȥ��֤--ʹ���ض����ص��ͻ���
					response.sendRedirect("logincl?username=" + myname + "&passwd=" + mypwd);

					return; // ��������,��������
							// ����Ҫ��return�������һֱ������ȥ
				}
			}
			// �Ƿ�����
			response.sendRedirect("login?info=warError");
			return; // ��������,��������
					// ����Ҫ��return�������һֱ������ȥ
		}

		out.println("<html>");
		out.println("<body bgcolor=#CED3FF><center>");

		out.println("��ӭ��:" + u + " <hr>");

		// out.println("<img src =images/3.jpg><br>");
		out.println("<h1>�����û�</h1>");

		out.println("<br><a href=main>����������</a>");
		out.println("<a href=login?info=begain>�������µ�¼</a>");
		out.println("<a href=leave>��ȫ�˳�</a><hr>");

		// �����ҳ���ʴ����Ĺ���

		// ==========================��ҳ����==========================
		int pageSize = 5;// һҳ��ʾ������¼
		int pageNow = 1;// ϣ����ʾ�ڼ�ҳ

		// ��̬�Ľ���pageNow
		String sPageNow = request.getParameter("pageNow");

		if (sPageNow != null)
		{
			pageNow = Integer.parseInt(sPageNow);
		}

		// ����UserDAO
		UserService udao = new UserService();

		List<?> all = udao.getResultByPage(pageNow, pageSize);

		out.println("<table border =1>");
		out.println("<tr bgcolor=pink><th>�û�ID</th><th>�û�����</th><th>�û�����</th><th>����</th><th>����</th><th>�޸��û�</th><th>ɾ���û�</th></tr>");

		// ����һ����ɫ����
		String[] myCol =
		{ "silver", "pink" };

		for (int i = 0; i < all.size(); i++)
		{
			User user = (User) all.get(i);
			out.println("<tr bgcolor=" + myCol[i % 2] + ">");
			out.println("<td>" + user.getUserId() + "</td>");
			out.println("<td>" + user.getUsername() + "</td>");
			out.println("<td>" + user.getPasswd() + "</td>");
			out.println("<td>" + user.getEmail() + "</td>");
			out.println("<td>" + user.getGrade() + "</td>");

			out.println("<td><a href=updateuser?userId=" + user.getUserId() + "&userNmae=" + user.getUsername() + "&pwd=" + user.getPasswd() + "&email=" + user.getEmail() + "&grade="
					+ user.getGrade() + ">�޸��û�</a></td>");

			out.println("<td><a href=deleteuser?userId=" + user.getUserId() + " onclick=\"return window.confirm('��ȷ��Ҫɾ�����û���?')\">ɾ���û�</a></td>");

			out.println("</tr>");
		}

		out.println("</table><br><br>");

		int pageCount = udao.getPageCount();

		// ��һҳ
		if (pageNow != 1)
			out.println("<a href=welcom?pageNow=" + (pageNow - 1) + " >��һҳ</a>");

		// ��ʾ������<����ط��и�СBug,��pageNow�����һҳʱ�����>
		int last = (pageNow + pageSize - 1);
		int size = last > pageCount ? pageCount : last;

		for (int j = pageNow; j <= size; j++)
		{
			out.println("<a href=welcom?pageNow=" + j + " > <" + j + "> </a>");
		}

		// ��һҳ
		if (pageNow != pageCount)
			out.println("<a href=welcom?pageNow=" + (pageNow + 1) + " >��һҳ</a><br><br>");

		// ָ����ת��ĳҳ,ʵ������һ����
		out.println("<form action=welcom>");
		out.println("<input type=text name=pageNow size=10>");
		out.println("<input type=submit value=go>");
		out.println("</form><hr>");

		out.println("<br>����ҳ��������" + this.getServletContext().getAttribute("visitTimes").toString() + "��<br>");
		// out.println("����IP="+request.getRemoteAddr()+"<br>");
		// out.println("���Ļ�����="+request.getRemoteHost()+"<br>");
		out.println("</center></body>");
		out.println("</html>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
