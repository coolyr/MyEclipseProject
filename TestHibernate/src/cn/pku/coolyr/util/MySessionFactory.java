package cn.pku.coolyr.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//ʹ��hibernate����Ŀ��ʱ��һ��Ҫ��ֻ֤��һ��sessionFactory
//һ�����ݿ�ֻ��һ��sessionFactory
final public class MySessionFactory
{

	private static SessionFactory sessionFactory = null;

	private static Configuration configuration = null;
	static
	{
		configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
	}

	private MySessionFactory()
	{
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

}
