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
	// 重写init方法
	/**
	 * 读取：把磁盘文件中的信息读取到内存中 写出：把内存中的信息写出到磁盘文件中
	 */
	public void init()
	{
		try
		{
			String path = this.getServletContext().getRealPath("/visitTimes.txt");

			FileReader fileReader = new FileReader(path);
			// 创建一个能在磁盘文件中读取信息的对象
			BufferedReader br = new BufferedReader(fileReader);

			// 把磁盘文件中的信息读取到内存中
			String numVal = br.readLine();

			br.close();

			// times 必须让所有用户访问到
			// 将times值放到servletcontext
			// servletcontext:在服务器中,所有登录在线用户共享该内存空间
			// 该init()方法,在tomcat不重启的情况下,只被调用一次
			// 在初次调用LoginCL时执行一次,第二次调用LoinCL时不被调用
			this.getServletContext().setAttribute("visitTimes", numVal);

			System.out.println("LoginCL init 被调用 visitTimes=" + numVal);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// 重写destroy方法,关闭tomcat时被执行一次
	public void destroy()
	{
		try
		{
			// 把内存中的信息写回到文件中
			String path = this.getServletContext().getRealPath("/visitTimes.txt");

			FileWriter fw = new FileWriter(path);

			// 创建一个能把内存中的信息写回到文件中的对象
			BufferedWriter bw = new BufferedWriter(fw);

			// 向文件中写入一行数据,那写入的是什么信息呢
			// 写入的是servletContext内存中的由visitTiems标识的信息
			String visitNum = this.getServletContext().getAttribute("visitTimes").toString();
			bw.write(visitNum);

			bw.close();

			System.out.println("LoginCL destroy 被调用 visitTimes=" + visitNum);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 登录成功：
	 * 
	 * 1.把用户名和密码,通过Cookie技术保存到客户端
	 * 
	 * 2.在服务器,开辟一块内存空间Session,保存了3个属性(pass,name,pwd)
	 * 
	 * 3.取出第一次加载在serveltContext中的值,自增,再放回到该内存空间中
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		// 解决中文乱码问题
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		// 接受通过登录表单点击发送按钮自动发送过来的用户名和密码信息
		String u = request.getParameter("username");
		String p = request.getParameter("passwd");
		User user = new User();
		user.setUsername(u);
		user.setPasswd(p);

		UserService udao = new UserService();

		// 调用UserDAO中的验证方法
		if (udao.checkUser(user))
		{
			// 进入表示用户合法

			// 再判断用户是否选择由keep标识的复选框,选择验证成功,则会通过表单自动发送该信息
			String keep = request.getParameter("keep");// 这里 keep = 2

			System.out.println("keep 的值为:" + keep);

			if (keep != null)
			{
				// 什么是cookie
				/**
				 * 服务器在客户端保存的用户信息,如登录名 密码等....就是cookie 例如现在,用户点击登录,把登录名和密码,复选框信息
				 * 传入到服务器,验证成功,则把用户名和密码保存到该 客户端,在服务器端需要的时候可以从客户端读取
				 * 
				 * 用途：
				 * 
				 * A.保存用户名、密码,在一定时间内不用重新登录
				 * 
				 * B.记录用户访问网站的喜好(有无背景音乐)
				 * 
				 * C.网站的个性化,不如定制网站的服务,内容
				 * 
				 * 步骤：
				 * 
				 * 1.在服务器创建 Cookie c = new Coolie(String name,String val);
				 * 
				 * 2.将该Cookie添加到客户端 response.addCookie(c);
				 * 
				 * 3.从客户端读取该Cookie到服务器端 request.getCookies();
				 * 
				 * 注意：Cookie周期:从Cookie创建开始到该时间结束,不管该Cookie是否被访问
				 * 
				 * Cookie是保存在客户端的,例如A的cookie保存在A客户端,B的cookie保存在B客户端,彼此不影响
				 */

				// 将合法用户名和密码保存在客户端(cookie技术)
				// 1.创建
				Cookie name = new Cookie("myname", u);
				Cookie pwd = new Cookie("mypwd", p);

				// 2.设置时间 2周 单位秒
				name.setMaxAge(14 * 24 * 3600);
				pwd.setMaxAge(14 * 24 * 3600);

				// 3.回写到客户端
				response.addCookie(name);
				response.addCookie(pwd);
			}

			/**
			 * 什么是session?
			 * 
			 * 当用户打开浏览器,访问某个网站时,服务器就会在服务器内存 为该浏览器分配一个空间,该空间被这个浏览器独占,用于不同页面共享数据
			 * 
			 * 用途：
			 * 
			 * A.购物车 (访问同一个服务器),例如访问淘宝网,淘宝网的服务器会创建一个session内存空间,而当当网就是另一个Session
			 * 
			 * B.保存登录用户的信息、将某些数据放入到session中供同一用户的各个页面使用
			 * 
			 * C. 防止用户非法登录到某个页面
			 * 
			 * 如何使用session
			 * 
			 * 1.得到session HttpSession hs = request.getSession(true);
			 * 
			 * 2.向session添加属性 hs.setAttribute(String name,Object val);
			 * 
			 * 3.从session中得到某个属性 String name = hs.getAttribute(String name);
			 * 
			 * 4.从session中删除某个属性 hs.removeAttribute(String name);
			 * 
			 * 注意： session信息是保存在服务器中的,例如客户端A的session保存在服务器中的分配的一块A内存空间,
			 * 客户端B的session保存在该服务器中的分配的一块B内存空间,它们都在服务器内存空间,但它们彼此是独立的 相互是没有关联的。
			 * 
			 * session的存在时间：session的发呆时间,而不是累计时间
			 * 
			 * 同一个客户端,双击打开一个网站,在双击打开同一个网站,服务器会为他们开辟两个不同的 session内存空间
			 * 
			 * session的各个属性要占用服务器内存的,因此万不得已才会使用
			 */

			// 将验证成功的信息,写入session
			// 1.得到session
			HttpSession hs = request.getSession(true);
			// 修改session的存在时间-1小时
			hs.setMaxInactiveInterval(60 * 60);// 秒

			// session中保存了3个属性 pass/uname/pwd
			hs.setAttribute("pass", "ok");
			hs.setAttribute("uname", u);
			hs.setAttribute("pwd", p);

			// 登录验证成功将servletContext内存中的visiTime所对应的值取出
			String times = this.getServletContext().getAttribute("visitTimes").toString();

			// 对times的值++再重新放回到seveltContext内存中
			this.getServletContext().setAttribute("visitTimes", (Integer.parseInt(times) + 1) + "");

			// 跳转
			response.sendRedirect("main");// 写你要到的servlet的那个url
		}
		else
		{
			// 说明用户名不存在
			// 不合法
			// 跳转到登入页面
			response.sendRedirect("login?info=nameError");// 写你要到的servlet的那个url
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
