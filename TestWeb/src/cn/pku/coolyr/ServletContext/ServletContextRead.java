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

		// 读取文件(Tomcat服务器 下的 Web 和 WEB-INF 目录下)
		InputStream is = this.getServletContext().getResourceAsStream("dbinfor.properties");
		// 创建properties
		Properties pp = new Properties();
		pp.load(is);
		out.println("<br/>WebRoot  -> username=" + pp.getProperty("username"));

		// 如果文件在src目录下要用类加载器去读
		// InputStream is =
		// Servlet类名.class.getClassLoader().getResoureAsStream("dbinfo.properties");
		InputStream inputStream = ServletContextRead.class.getClassLoader().getResourceAsStream("dbinfor.properties");
		// 创建properties
		Properties properties = new Properties();
		properties.load(inputStream);
		out.println("<br/>src  -> username=" + properties.getProperty("username"));

		// 获取文件全路径
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
