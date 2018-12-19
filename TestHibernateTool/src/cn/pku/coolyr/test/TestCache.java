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
		// ͨ����ȡһ��sesion,��hibernate�������(config->����hibernate.cfg.xml)
		Session s = null;
		Transaction tx = null;
		try
		{
			// ����ʹ�û���ģ��������.
			s = HibernateUtil.getNewSession();
			tx = s.beginTransaction();

			// ��ѯ1��ѧ��

			Employee employee = (Employee) s.get(Employee.class, 1);// 1->һ������
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
			// ����ʹ�û���ģ��������.
			s = HibernateUtil.getNewSession();
			tx = s.beginTransaction();

			// ��ѯ45��ѧ��

			Employee employee1 = (Employee) s.get(Employee.class, 1);// 1->һ������
			System.out.println(employee1.getName());
			Employee employee01 = (Employee) s.get(Employee.class, 1);// 1->һ������
			System.out.println(employee01.getName());

			Employee employee2 = (Employee) s.get(Employee.class, 3);// 1->һ������
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
		// ���һ��ͳ�ƣ�ͳ�Ƶ���Ϣ��Sessfactory
		// SessionFactory����.
		Statistics statistics = HibernateUtil.getSessionFactory().getStatistics();
		System.out.println(statistics);
		System.out.println("����" + statistics.getSecondLevelCachePutCount());
		System.out.println("����" + statistics.getSecondLevelCacheHitCount());
		System.out.println("���" + statistics.getSecondLevelCacheMissCount());

	}

}
