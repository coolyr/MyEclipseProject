package cn.pku.coolyr.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//这是一个工具类
//可以返回全新的session,也可以返回和线程绑定session
final public class HibernateUtil
{
	private static SessionFactory sessionFactory = null;
	// 有些程序员得到和线程绑定的session,不希望和hibernate.cfg.xml关联(不希望使用getCurrentSession的时候要配置),
	// 于是可以使用 ThreadLocal (线程局部模式)解决该问题
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	private HibernateUtil()
	{
	}

	static
	{
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public static SessionFactory getSessionFactory()
	{
		// TODO 自动生成的方法存根
		return sessionFactory;
	}

	// 获取全新的全新的sesession
	public static Session getNewSession()
	{
		return sessionFactory.openSession();
	}

	// 获取和线程关联的session
	public static Session getCurrentSession()
	{

		// return sessionFactory.getCurrentSession();
		// 先从 threadLocat取出对象
		Session session = threadLocal.get();
		// 如果取不出来,就创建一个新的
		// 如果取的出来,则直接返回
		if (session == null)
		{
			session = sessionFactory.openSession();
			// 把session对象设置到 threadLocal,相当于该session已经和线程绑定
			threadLocal.set(session);
		}
		return session;

	}

	// 关闭和线程绑定的session
	public static void closeCurrentSession()
	{

		Session s = (Session) threadLocal.get();
		if (s != null && s.isOpen())
		{
			s.close();
			threadLocal.set(null);
		}
	}

	// 统一的一个修改和删除(批量 hql)
	// hql : "delete upate ...?"
	public static void executeUpdate(String hql, String[] parameters)
	{

		Session s = null;
		Transaction tx = null;

		try
		{
			s = getNewSession();
			tx = s.beginTransaction();
			Query query = s.createQuery(hql);
			// 先判断是否有参数要绑定
			if (parameters != null && parameters.length > 0)
			{
				for (int i = 0; i < parameters.length; i++)
				{
					query.setString(i, parameters[i]);
				}
			}
			query.executeUpdate();
			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		finally
		{

			if (s != null && s.isOpen())
			{
				s.close();
			}
		}
	}

	// 统一的添加的方法
	public static void save(Object obj)
	{
		Session s = null;
		Transaction tx = null;

		try
		{
			s = getNewSession();
			tx = s.beginTransaction();
			s.save(obj);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			if (s != null && s.isOpen())
			{
				s.close();
			}
		}
	}

	// 提供一个统一的查询方法(带分页)
	// hql形式: from 类 where 条件=? ..
	public static List executeQueryByPage(String hql, String[] parameters, int pageSize, int pageNow)
	{
		Session s = null;
		List list = null;

		try
		{
			s = getNewSession();
			Query query = s.createQuery(hql);
			// 先判断是否有参数要绑定
			if (parameters != null && parameters.length > 0)
			{
				for (int i = 0; i < parameters.length; i++)
				{
					query.setString(i, parameters[i]);
				}
			}
			// 设置起始位置和返回的记录数
			query.setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize);
			list = query.list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		finally
		{

			if (s != null && s.isOpen())
			{
				s.close();
			}
		}
		return list;
	}

	// 提供一个统一的查询方法
	// hql形式 :from 类 where 条件=? ..
	public static List executeQuery(String hql, String[] parameters)
	{

		Session s = null;
		List list = null;

		try
		{
			// 防止死锁，使用局部变量
			s = getNewSession();
			Query query = s.createQuery(hql);
			// 先判断是否有参数要绑定
			if (parameters != null && parameters.length > 0)
			{
				for (int i = 0; i < parameters.length; i++)
				{
					query.setString(i, parameters[i]);
				}
			}
			list = query.list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		finally
		{

			if (s != null && s.isOpen())
			{
				s.close();
			}

		}
		return list;
	}

}
