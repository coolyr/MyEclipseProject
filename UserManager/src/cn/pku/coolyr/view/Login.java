package cn.pku.coolyr.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录界面 该界面会被反复使用,
 * 例如验证失败会返回该界面,重新执行 
 * 或非法登录也会返回该界面,重新执行。
 */
public class Login extends HttpServlet
{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{

		//解决中文乱码问题方式之一
		res.setContentType("text/html;charset=utf-8");

		//从内存中写出信息,让用户看的到。
		PrintWriter out = res.getWriter();

		out.println("<html>");

		// 设置背景颜色
		out.println("<body bgcolor=#CED3FF>");

		// 居中,并加了一条水平线
		out.println("<center><hr>");

		out.println("<h1>登录界面</h1><hr>");

		// 登录验证失败: response.sendRedirect("login?info=nameError")
		// 试图非法登录：response.sendRedirect("login?info=warError")
		
		String info = req.getParameter("info");

		if (info != null)	//一开始登录info一定为空,当出现验证失败或非法登录不为空
		{
			if (info.equals("nameError"))
			{
				out.println("用户名或密码错误!");
			}
			else if (info.equals("warError"))
			{
				out.println("非法登录！");
			}
			else if (info.equals("begain"))
			{
				out.println("<font color=Gray><sub>请重新登录!</sub></font><br>");
			}
			else if (info.equals("leave"))
			{
				out.println("<font color=Gray><sub>您已安全退出!</sub></font><br>");
			}
		}

		// 用户登录表单 提交给logincl来处理
		out.println("<form action=logincl method=post>");

		// name=username
		out.println("用户名：<input type=text name=username><br><br>");

		// name=passwd
		out.println("密码：	&nbsp;&nbsp;<input type=password name=passwd ><br><br>");

		// 复选框 两周了自动登录
		// name=keep
		out.println("<input type=checkbox name=keep value=2>两周内不再重新登录<br><br>");

		out.println("<input type=submit value=发送>");

		out.println("&nbsp;&nbsp;&nbsp;&nbsp;");
		out.println("<input type=reset value=重设><br>");
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
