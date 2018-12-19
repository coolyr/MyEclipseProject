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
			//��ȡ�͵�ǰ�̰߳󶨵�Session����
			s = HibernateUtil.getCurrentSession();
			tx = s.beginTransaction();
			//����ת������
			filterChain.doFilter(request, response);
			//������ҵ����ύ����
			//ע�⣺�ύ����ǰ���Ƿ������Ѿ����������ݣ����ؿͻ��˵�ǰһ�̡�
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
