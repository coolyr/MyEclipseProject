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
	 * 如果Session(20秒)时间到了会怎么样?
	 * 
	 * 1.验证成功,到了主界面,没有进入Welcom界面,这是Session时间到了.
	 * 
	 * 2.这是如果Cookie中没有保存用户名和密码,这回显示非法登录
	 * 
	 * 3.如果Cookie保存了,这时会点击进入Welcom界面是自动去验证Cookie中的信息, 即要双击两次 才能进入welcom界面
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		// 中文乱码
		response.setContentType("text/html;charset=gbk");

		PrintWriter out = response.getWriter();

		// 得到在服务器端的session内存中的信息(3个属性),该信息是
		// 在登录成功时,设置的
		HttpSession hs = request.getSession(true);
		String val = (String) hs.getAttribute("pass");
		String u = (String) hs.getAttribute("uname");
		String pwd = (String) hs.getAttribute("pwd");

		System.out.println("Welcom ?" + val);

		String myname = "";
		String mypwd = "";

		// 没有经过验证,服务器端Session中也就没有信息
		if (val == null)
		{
			// 非法登录

			// 如果session没有用户信息即非法登录,在看看有没有cookie信息,如果有，则2周是不用登录的
			Cookie[] allCookies = request.getCookies();

			int i = 0;

			// 如果allCookies不为空
			if (allCookies != null)
			{
				System.out.println("=========");
				// 从中取出cookie
				for (i = 0; i < allCookies.length; i++)
				{
					// 依次取出
					Cookie temp = allCookies[i];

					if (temp.getName().equals("myname"))
					{
						// 得到cookie的值
						myname = temp.getValue();

						System.out.println("Welcom name = " + myname + "keep 不为 null");
					}
					else if (temp.getName().equals("mypwd"))
					{
						// 得到cookie的值
						mypwd = temp.getValue();

						System.out.println("Welcom pwd = " + mypwd);
					}

				}
				System.out.println("============");
				// Cookie中myname和mypwd值中不为空,还不行,还要去自动去验证登录，登录成功返回主界面

				System.out.println("Cookie中myname和mypwd值中不为空?  " + (!myname.equals("") && !mypwd.equals("")));

				if (!myname.equals("") && !mypwd.equals(""))
				{
					System.out.println("到loguicl去验证Cookie");
					// 到loguicl去验证--使用重定向打回到客户端
					response.sendRedirect("logincl?username=" + myname + "&passwd=" + mypwd);

					return; // 立即返回,不往下走
							// 必须要有return，否则会一直运行下去
				}
			}
			// 非法登入
			response.sendRedirect("login?info=warError");
			return; // 立即返回,不往下走
					// 必须要有return，否则会一直运行下去
		}

		out.println("<html>");
		out.println("<body bgcolor=#CED3FF><center>");

		out.println("欢迎您:" + u + " <hr>");

		// out.println("<img src =images/3.jpg><br>");
		out.println("<h1>管理用户</h1>");

		out.println("<br><a href=main>返回主界面</a>");
		out.println("<a href=login?info=begain>返回重新登录</a>");
		out.println("<a href=leave>安全退出</a><hr>");

		// 添加网页访问次数的功能

		// ==========================分页功能==========================
		int pageSize = 5;// 一页显示几条记录
		int pageNow = 1;// 希望显示第几页

		// 动态的接收pageNow
		String sPageNow = request.getParameter("pageNow");

		if (sPageNow != null)
		{
			pageNow = Integer.parseInt(sPageNow);
		}

		// 调用UserDAO
		UserService udao = new UserService();

		List<?> all = udao.getResultByPage(pageNow, pageSize);

		out.println("<table border =1>");
		out.println("<tr bgcolor=pink><th>用户ID</th><th>用户名称</th><th>用户密码</th><th>邮箱</th><th>级别</th><th>修改用户</th><th>删除用户</th></tr>");

		// 定义一个颜色数组
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
					+ user.getGrade() + ">修改用户</a></td>");

			out.println("<td><a href=deleteuser?userId=" + user.getUserId() + " onclick=\"return window.confirm('您确认要删除该用户吗?')\">删除用户</a></td>");

			out.println("</tr>");
		}

		out.println("</table><br><br>");

		int pageCount = udao.getPageCount();

		// 上一页
		if (pageNow != 1)
			out.println("<a href=welcom?pageNow=" + (pageNow - 1) + " >上一页</a>");

		// 显示超链接<这个地方有个小Bug,当pageNow是最后一页时会错误。>
		int last = (pageNow + pageSize - 1);
		int size = last > pageCount ? pageCount : last;

		for (int j = pageNow; j <= size; j++)
		{
			out.println("<a href=welcom?pageNow=" + j + " > <" + j + "> </a>");
		}

		// 下一页
		if (pageNow != pageCount)
			out.println("<a href=welcom?pageNow=" + (pageNow + 1) + " >下一页</a><br><br>");

		// 指定跳转到某页,实际上是一个表单
		out.println("<form action=welcom>");
		out.println("<input type=text name=pageNow size=10>");
		out.println("<input type=submit value=go>");
		out.println("</form><hr>");

		out.println("<br>该网页被访问了" + this.getServletContext().getAttribute("visitTimes").toString() + "次<br>");
		// out.println("您的IP="+request.getRemoteAddr()+"<br>");
		// out.println("您的机器名="+request.getRemoteHost()+"<br>");
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
