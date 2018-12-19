package cn.pku.coolyr.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class SafeFilter extends HttpServlet implements Filter
{
	private String keywords[];

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
	{
		String username = request.getParameter("username");
		System.out.println("this is safe filter !");
		System.out.println(username);
		if (username != null)
		{
			for (String key : keywords)
			{
				if (key.equals(username))
				{
					request.setAttribute("err", username + "被第二个管理安全的过滤器拦截了");
					request.getRequestDispatcher("/WEB-INF/err.jsp").forward(request, response);
					return;
				}
			}
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		keywords = filterConfig.getInitParameter("keywords").split(";");
	}

}
