package cn.pku.coolyr.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import cn.pku.coolyr.domain.Employee;
import cn.pku.coolyr.util.HibernateUtil;

public class TestCache
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		// 通过获取一个sesion,让hibernate框架运行(config->加载hibernate.cfg.xml)
		Session s = null;
		Transaction tx = null;
		try
		{
			// 我们使用基础模板来讲解.
			s = HibernateUtil.getNewSession();
			tx = s.beginTransaction();

			// 查询1号学生

			Employee employee = (Employee) s.get(Employee.class, 1);// 1->一级缓存
			System.out.println(employee.getName());

			tx.commit();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
		}
		finally
		{

			if (s != null && s.isOpen())
			{
				s.close();
			}
		}

		System.out.println("*********************************");
		try
		{
			// 我们使用基础模板来讲解.
			s = HibernateUtil.getNewSession();
			tx = s.beginTransaction();

			// 查询45号学生

			Employee employee1 = (Employee) s.get(Employee.class, 1);// 1->一级缓存
			System.out.println(employee1.getName());
			Employee employee01 = (Employee) s.get(Employee.class, 1);// 1->一级缓存
			System.out.println(employee01.getName());

			Employee employee2 = (Employee) s.get(Employee.class, 3);// 1->一级缓存
			System.out.println(employee2.getName());
			tx.commit();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
		}
		finally
		{

			if (s != null && s.isOpen())
			{
				s.close();
			}
		}
		// 完成一个统计，统计的信息在Sessfactory
		// SessionFactory对象.
		Statistics statistics = HibernateUtil.getSessionFactory().getStatistics();
		System.out.println(statistics);
		System.out.println("放入" + statistics.getSecondLevelCachePutCount());
		System.out.println("命中" + statistics.getSecondLevelCacheHitCount());
		System.out.println("错过" + statistics.getSecondLevelCacheMissCount());

	}

}
