package cn.pku.coolyr.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;


public class CodeFilter extends HttpServlet implements Filter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String encoding;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
	{

		// TODO �Զ����ɵķ������
		System.out.println("this is Code filter !");
		//���ý��ձ���
		request.setCharacterEncoding(encoding);
		//�������ת��
		response.setCharacterEncoding(encoding);
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		// TODO �Զ����ɵķ������
		encoding = filterConfig.getInitParameter("encoding");
	}

}
