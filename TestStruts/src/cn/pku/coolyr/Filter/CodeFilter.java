package cn.pku.coolyr.Filter;

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

import org.omg.CORBA.PRIVATE_MEMBER;

public class CodeFilter extends HttpServlet implements Filter
{
	private String encoding;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
	{

		// TODO �Զ����ɵķ������
		System.out.println("this is Code filter !");
		//���ý��ձ���
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		//�������ת��
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		// TODO �Զ����ɵķ������
		encoding = filterConfig.getInitParameter("encoding");
	}

}
