package cn.pku.coolyr.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.pku.coolyr.bean.User;
import cn.pku.coolyr.service.UserService;

public class LoginCL extends HttpServlet
{
	// ��дinit����
	/**
	 * ��ȡ���Ѵ����ļ��е���Ϣ��ȡ���ڴ��� д�������ڴ��е���Ϣд���������ļ���
	 */
	public void init()
	{
		try
		{
			String path = this.getServletContext().getRealPath("/visitTimes.txt");

			FileReader fileReader = new FileReader(path);
			// ����һ�����ڴ����ļ��ж�ȡ��Ϣ�Ķ���
			BufferedReader br = new BufferedReader(fileReader);

			// �Ѵ����ļ��е���Ϣ��ȡ���ڴ���
			String numVal = br.readLine();

			br.close();

			// times �����������û����ʵ�
			// ��timesֵ�ŵ�servletcontext
			// servletcontext:�ڷ�������,���е�¼�����û�������ڴ�ռ�
			// ��init()����,��tomcat�������������,ֻ������һ��
			// �ڳ��ε���LoginCLʱִ��һ��,�ڶ��ε���LoinCLʱ��������
			this.getServletContext().setAttribute("visitTimes", numVal);

			System.out.println("LoginCL init ������ visitTimes=" + numVal);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// ��дdestroy����,�ر�tomcatʱ��ִ��һ��
	public void destroy()
	{
		try
		{
			// ���ڴ��е���Ϣд�ص��ļ���
			String path = this.getServletContext().getRealPath("/visitTimes.txt");

			FileWriter fw = new FileWriter(path);

			// ����һ���ܰ��ڴ��е���Ϣд�ص��ļ��еĶ���
			BufferedWriter bw = new BufferedWriter(fw);

			// ���ļ���д��һ������,��д�����ʲô��Ϣ��
			// д�����servletContext�ڴ��е���visitTiems��ʶ����Ϣ
			String visitNum = this.getServletContext().getAttribute("visitTimes").toString();
			bw.write(visitNum);

			bw.close();

			System.out.println("LoginCL destroy ������ visitTimes=" + visitNum);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ��¼�ɹ���
	 * 
	 * 1.���û���������,ͨ��Cookie�������浽�ͻ���
	 * 
	 * 2.�ڷ�����,����һ���ڴ�ռ�Session,������3������(pass,name,pwd)
	 * 
	 * 3.ȡ����һ�μ�����serveltContext�е�ֵ,����,�ٷŻص����ڴ�ռ���
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		// ���������������
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		// ����ͨ����¼��������Ͱ�ť�Զ����͹������û�����������Ϣ
		String u = request.getParameter("username");
		String p = request.getParameter("passwd");
		User user = new User();
		user.setUsername(u);
		user.setPasswd(p);

		UserService udao = new UserService();

		// ����UserDAO�е���֤����
		if (udao.checkUser(user))
		{
			// �����ʾ�û��Ϸ�

			// ���ж��û��Ƿ�ѡ����keep��ʶ�ĸ�ѡ��,ѡ����֤�ɹ�,���ͨ�����Զ����͸���Ϣ
			String keep = request.getParameter("keep");// ���� keep = 2

			System.out.println("keep ��ֵΪ:" + keep);

			if (keep != null)
			{
				// ʲô��cookie
				/**
				 * �������ڿͻ��˱�����û���Ϣ,���¼�� �����....����cookie ��������,�û������¼,�ѵ�¼��������,��ѡ����Ϣ
				 * ���뵽������,��֤�ɹ�,����û��������뱣�浽�� �ͻ���,�ڷ���������Ҫ��ʱ����Դӿͻ��˶�ȡ
				 * 
				 * ��;��
				 * 
				 * A.�����û���������,��һ��ʱ���ڲ������µ�¼
				 * 
				 * B.��¼�û�������վ��ϲ��(���ޱ�������)
				 * 
				 * C.��վ�ĸ��Ի�,���綨����վ�ķ���,����
				 * 
				 * ���裺
				 * 
				 * 1.�ڷ��������� Cookie c = new Coolie(String name,String val);
				 * 
				 * 2.����Cookie��ӵ��ͻ��� response.addCookie(c);
				 * 
				 * 3.�ӿͻ��˶�ȡ��Cookie���������� request.getCookies();
				 * 
				 * ע�⣺Cookie����:��Cookie������ʼ����ʱ�����,���ܸ�Cookie�Ƿ񱻷���
				 * 
				 * Cookie�Ǳ����ڿͻ��˵�,����A��cookie������A�ͻ���,B��cookie������B�ͻ���,�˴˲�Ӱ��
				 */

				// ���Ϸ��û��������뱣���ڿͻ���(cookie����)
				// 1.����
				Cookie name = new Cookie("myname", u);
				Cookie pwd = new Cookie("mypwd", p);

				// 2.����ʱ�� 2�� ��λ��
				name.setMaxAge(14 * 24 * 3600);
				pwd.setMaxAge(14 * 24 * 3600);

				// 3.��д���ͻ���
				response.addCookie(name);
				response.addCookie(pwd);
			}

			/**
			 * ʲô��session?
			 * 
			 * ���û��������,����ĳ����վʱ,�������ͻ��ڷ������ڴ� Ϊ�����������һ���ռ�,�ÿռ䱻����������ռ,���ڲ�ͬҳ�湲������
			 * 
			 * ��;��
			 * 
			 * A.���ﳵ (����ͬһ��������),��������Ա���,�Ա����ķ������ᴴ��һ��session�ڴ�ռ�,��������������һ��Session
			 * 
			 * B.�����¼�û�����Ϣ����ĳЩ���ݷ��뵽session�й�ͬһ�û��ĸ���ҳ��ʹ��
			 * 
			 * C. ��ֹ�û��Ƿ���¼��ĳ��ҳ��
			 * 
			 * ���ʹ��session
			 * 
			 * 1.�õ�session HttpSession hs = request.getSession(true);
			 * 
			 * 2.��session������� hs.setAttribute(String name,Object val);
			 * 
			 * 3.��session�еõ�ĳ������ String name = hs.getAttribute(String name);
			 * 
			 * 4.��session��ɾ��ĳ������ hs.removeAttribute(String name);
			 * 
			 * ע�⣺ session��Ϣ�Ǳ����ڷ������е�,����ͻ���A��session�����ڷ������еķ����һ��A�ڴ�ռ�,
			 * �ͻ���B��session�����ڸ÷������еķ����һ��B�ڴ�ռ�,���Ƕ��ڷ������ڴ�ռ�,�����Ǳ˴��Ƕ����� �໥��û�й����ġ�
			 * 
			 * session�Ĵ���ʱ�䣺session�ķ���ʱ��,�������ۼ�ʱ��
			 * 
			 * ͬһ���ͻ���,˫����һ����վ,��˫����ͬһ����վ,��������Ϊ���ǿ���������ͬ�� session�ڴ�ռ�
			 * 
			 * session�ĸ�������Ҫռ�÷������ڴ��,����򲻵��ѲŻ�ʹ��
			 */

			// ����֤�ɹ�����Ϣ,д��session
			// 1.�õ�session
			HttpSession hs = request.getSession(true);
			// �޸�session�Ĵ���ʱ��-1Сʱ
			hs.setMaxInactiveInterval(60 * 60);// ��

			// session�б�����3������ pass/uname/pwd
			hs.setAttribute("pass", "ok");
			hs.setAttribute("uname", u);
			hs.setAttribute("pwd", p);

			// ��¼��֤�ɹ���servletContext�ڴ��е�visiTime����Ӧ��ֵȡ��
			String times = this.getServletContext().getAttribute("visitTimes").toString();

			// ��times��ֵ++�����·Żص�seveltContext�ڴ���
			this.getServletContext().setAttribute("visitTimes", (Integer.parseInt(times) + 1) + "");

			// ��ת
			response.sendRedirect("main");// д��Ҫ����servlet���Ǹ�url
		}
		else
		{
			// ˵���û���������
			// ���Ϸ�
			// ��ת������ҳ��
			response.sendRedirect("login?info=nameError");// д��Ҫ����servlet���Ǹ�url
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
