package cn.pku.coolyr.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.pku.coolyr.domain.User;

//1.创建一个过滤器（继承Httpservlet 实现Filter接口）
//2.配置过滤器，默认不生效必须配置
//3.配置过滤器的顺序可以决定调用顺序。
public class MyFilter extends HttpServlet implements Filter
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try
		{
		}
		finally
		{
			out.close();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		System.out.println("**********MyFilter  doFilter**********");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 看看请求的资源是什么
		String uri = httpServletRequest.getRequestURI();
		System.out.println("uri ** " + uri);
		// 测试验证码的案例
		if (uri.startsWith("/TestWeb/CreateCheckCode") || uri.startsWith("/TestWeb/LoginUICheckCode"))
		{
			// 放行
			chain.doFilter(request, response);
		}
		else
		{
			// HttpSession session = httpServletRequest.getSession();
			// User u = (User) session.getAttribute("u");
			// if (u != null)
			// {
			// chain.doFilter(request, response);
			// }
			String flag = "true";
			if (flag.equals("true"))
			{
				chain.doFilter(request, response);
			}
			else
			{
				// 所有没有登入的用户必须先等录
				request.setAttribute("ERROR", "请登录Filter");
				request.getRequestDispatcher("/LoginUICheckCode").forward(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO 自动生成的方法存根

	}

}
