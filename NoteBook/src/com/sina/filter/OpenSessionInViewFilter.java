package com.sina.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sina.util.HibernateUtil;

public class OpenSessionInViewFilter extends HttpServlet implements Filter
{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
	{
		// TODO Auto-generated method stub
		Session s = null;
		Transaction tx = null;
		try
		{
			//获取和当前线程绑定到Session对象
			s = HibernateUtil.getCurrentSession();
			tx = s.beginTransaction();
			//继续转发请求
			filterChain.doFilter(request, response);
			//处理完业务后提交事务。
			//注意：提交事务前：是服务器已经处理完数据，返回客户端的前一刻。
			tx.commit();

		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{

			HibernateUtil.closeCurrentSession();
		}

	}

	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub

	}

}
