package cn.pku.coolyr.ServletContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextRead extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// ��ȡ�ļ�(Tomcat������ �µ� Web �� WEB-INF Ŀ¼��)
		InputStream is = this.getServletContext().getResourceAsStream("dbinfor.properties");
		// ����properties
		Properties pp = new Properties();
		pp.load(is);
		out.println("<br/>WebRoot  -> username=" + pp.getProperty("username"));

		// ����ļ���srcĿ¼��Ҫ���������ȥ��
		// InputStream is =
		// Servlet����.class.getClassLoader().getResoureAsStream("dbinfo.properties");
		InputStream inputStream = ServletContextRead.class.getClassLoader().getResourceAsStream("dbinfor.properties");
		// ����properties
		Properties properties = new Properties();
		properties.load(inputStream);
		out.println("<br/>src  -> username=" + properties.getProperty("username"));

		// ��ȡ�ļ�ȫ·��
		String path = this.getServletContext().getRealPath("/imgs/AD.png");
		out.println("<br/>/imgs/AD.png  - > path=" + path);

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);
	}
}
