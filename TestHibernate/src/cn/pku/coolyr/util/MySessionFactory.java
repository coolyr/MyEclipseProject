package cn.pku.coolyr.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//使用hibernate做项目的时候一定要保证只有一个sessionFactory
//一个数据库只有一个sessionFactory
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
