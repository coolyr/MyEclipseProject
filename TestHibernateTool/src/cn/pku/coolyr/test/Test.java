package cn.pku.coolyr.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.pku.coolyr.domain.Student;
import cn.pku.coolyr.util.HibernateUtil;

public class Test
{
	public static void main(String[] args)
	{
		// selectAll();
		// selectPart();
		// selectUniqueResult();
		// selectDistinct();
		// selectBetweenAnd();
		// selectInNotin();
		// selectGroupBy();
		// selectGroupBy();
		// selectExamples();
		// showResultByPage(3);
		// selectParameterBingding();
		// selectCriteria();

	}

	public static void selectAll()
	{
		// �����Ǿ���˵��hqlʹ��
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 1.�������е�ѧ��
			List<Student> list = session.createQuery("from Student").list();
			// ȡ��1. for ��ǿ
			for (Student s : list)
			{
				System.out.println(s.getSname() + " " + s.getSaddress());
			}
			System.out.println("*****************");

			// 2. ʹ��iterator
			Iterator<Student> iterator = list.iterator();
			while (iterator.hasNext())
			{
				Student s = iterator.next();
				System.out.println(s.getSname() + " " + s.getSage());
			}
			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	public static void selectPart()
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 2.������ѧ�����ֺ�����ϵ
			// ԭ��: �ڽ���jdbc������˵���� Ҫ��ѯʲô�ֶξͲ�ѯʲô�ֶΣ���Ҫselect * from ��
			// ������hibernate ,������ʵ���Բ���ѭ�������,�������ǰ�������������Զ���ѯ
			// �������ǻ���Ҫ�������ȡ����������
			List<Object[]> list = session.createQuery("select sname,sdept from Student").list();
			for (int i = 0; i < list.size(); i++)
			{
				Object[] objs = (Object[]) list.get(i);
				System.out.println(objs[0].toString() + " " + objs[1].toString());
			}
			System.out.println("****************");
			// ���ʹ��iterator
			Iterator it = list.iterator();
			while (it.hasNext())
			{
				Object[] objs = (Object[]) it.next();
				System.out.println(objs[0].toString() + " " + objs[1].toString());
			}
			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	public static void selectUniqueResult()
	{
		// �����Ǿ���˵��hqlʹ��
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();

			// ������Ǽ���һ��������ȷ֪�����ֻ��һ����������ʹ�ø÷���:
			// �����÷�����:
			Student s = (Student) session.createQuery("from Student where sid='20050003'").uniqueResult();
			System.out.println(s.getSname());

			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	public static void selectDistinct()
	{
		// �����Ǿ���˵��hqlʹ��
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// �����ظ��ļ�¼
			// ���磬��ʾ����ѧ�����Ա������.
			List list = session.createQuery("select distinct sage,ssex from Student").list();
			for (int i = 0; i < list.size(); i++)
			{
				Object[] objs = (Object[]) list.get(i);
				System.out.println(objs[0].toString() + " " + objs[1].toString());
			}
			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	public static void selectBetweenAnd()
	{
		// �����Ǿ���˵��hqlʹ��
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// ����������20~22֮���ѧ��
			List list = session.createQuery("select distinct sage,ssex,sname from Student where sage between 20 and 22").list();
			for (int i = 0; i < list.size(); i++)
			{
				Object[] objs = (Object[]) list.get(i);
				System.out.println(objs[0].toString() + " " + objs[1].toString() + objs[2].toString());
			}

			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	public static void selectInNotin()
	{
		// �����Ǿ���˵��hqlʹ��
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// ��ѯ�����ϵ������ϵ��ѧ����Ϣ
			List<Student> list = session.createQuery("from Student where sdept not in ('�����ϵ','����ϵ')").list();
			// for ��ǿ
			for (Student s : list)
			{
				System.out.println(s.getSname() + " " + s.getSaddress() + " " + s.getSdept());
			}

			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	public static void selectGroupBy()
	{
		// �����Ǿ���˵��hqlʹ��
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// having��ʹ��

			// 1.�Է����ѯ��Ľ��������ɸѡ:��������ʾ��������3��ϵ����
			// a. ��ѯ����ϵ�ֱ��ж���ѧ��.
			List<Object[]> list = session.createQuery("select count(*) as c1,sdept from  Student group by sdept having count(*)>1").list();
			for (Object[] obj : list)
			{
				System.out.println(obj[0].toString() + " " + obj[1].toString());
			}

			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	public static void selectHaving()
	{
		// �����Ǿ���˵��hqlʹ��
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// ��ʾ����ϵ��ѧ����ƽ������
			List<Object[]> list = session.createQuery("select avg(sage),sdept from  Student group by sdept").list();
			// ȡ�� for ��ǿ
			for (Object[] obj : list)
			{
				System.out.println(obj[0].toString() + " " + obj[1].toString());
			}

			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	public static void selectExamples()
	{
		// �����Ǿ���˵��hqlʹ��
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();

			// *** 1.��ѯ�����ϵ��������?->������Ƿ��ص���һ������
			// ��ʱ���ǵ�ȡ����ֱ��ȡ��list->object ������ list->Object[]
			List<Object[]> list0 = session.createQuery("select count(*) from  Student where sdept='�����ϵ'").list();
			System.out.println("********1.��ѯ�����ϵ��������?********");
			for (Object obj : list0)
			{
				System.out.println(obj.toString());
			}

			// ***2.��ѯ��ѧ���Ƕ���?
			List<Object[]> list1 = session.createQuery("select sum(grade) from Studcourse").list();
			System.out.println("********2.��ѯ��ѧ���Ƕ���?********");
			for (Object obj : list1)
			{
				System.out.println(obj.toString());
			}

			// *** 3.��ѯѡ��11�ſγ̵���߷ֺ���ͷ�.
			List<Object[]> list2 = session.createQuery("select 11,max(grade),min(grade) from Studcourse where course.cid=11").list();
			System.out.println("********3.��ѯѡ��11�ſγ̵���߷ֺ���ͷ�?********");
			for (Object[] obj : list2)
			{
				System.out.println(obj[0].toString() + " max=" + obj[1].toString() + " min=" + obj[2].toString());
			}

			// ***
			// 4.��ʾ���ƿ��Բ�����ѧ��������(Student-student)����Ŀ(Course-course)�ͷ���(Studcourse-studcourse)
			List<Object[]> list4 = session.createQuery("select student.sname,course.cname,grade from Studcourse where grade>=60").list();
			System.out.println("********4.��ʾ���ƿ��Բ�����ѧ��������(Student-student)����Ŀ(Course-course)�ͷ���(Studcourse-studcourse)?********");

			for (Object[] obj : list4)
			{
				System.out.println(obj[0].toString() + " " + obj[1].toString() + " " + obj[2].toString());
			}

			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	// ��ҳ����
	public static void showResultByPage(int pageSize)
	{
		// ���÷�ҳ�ı���
		int pageNow = 1;
		int pageCount = 1;// ����
		int rowCount = 1; // �����Ҫ��ѯ

		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();

			// ��ѯ����������rowcount
			rowCount = Integer.parseInt(session.createQuery("select count(*) from Student").uniqueResult().toString());
			// ������ҳ����pageCount
			pageCount = (rowCount - 1) / pageSize + 1;

			// �������ǿ���ѭ������ʾÿҳ����Ϣ
			for (int i = 1; i <= pageCount; i++)
			{
				System.out.println("************��" + i + "ҳ************");
				// setFirstResult : ���ôӵڼ�����¼ȡ
				// setMaxResults : ����ȡ����
				List<Student> list = session.createQuery("from Student").setFirstResult((i - 1) * pageSize).setMaxResults(pageSize).list();
				for (Student s : list)
				{
					System.out.println(s.getSname() + " " + s.getSdept());
				}
			}

			tx.commit();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}

	}

	// ������
	public static void selectParameterBingding()
	{
		// �����Ǿ���˵��hqlʹ��
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();

			// ��������������ʽ

			// ��1��������ǵĲ����� :ð����ʽ�����ģ������ǵĲ�����Ӧ������:
			List<Student> list1 = session.createQuery("from Student where sdept=:a1 and sage>:sage").setString("a1", "�����ϵ").setString("sage", "2").list();
			for (int i = 0; i < list1.size(); i++)
			{
				Student s = list1.get(i);
				System.out.println(s.getSname() + " " + s.getSage());
			}

			// ��2������һ����ʽ:�������ԣ���ʽ������
			List<Student> list2 = session.createQuery("from Student where sdept=? and sage>?").setString(0, "�����ϵ").setString(1, "2").list();
			for (int i = 0; i < list2.size(); i++)
			{
				Student s = list2.get(i);
				System.out.println(s.getSname() + " " + s.getSage());
			}

			// ��3�������İ󶨣����Էֿ�д,����ʹ��Forѭ���󶨲���
			Query query = session.createQuery("from Student where sdept=? and sage>?");
			query.setString(0, "�����ϵ");
			query.setString(1, "2");
			List<Student> list3 = query.list();
			for (int i = 0; i < list3.size(); i++)
			{
				Student s = list3.get(i);
				System.out.println(s.getSname() + " " + s.getSage());
			}

			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	// ������
	public static void selectCriteria()
	{
		// �����Ǿ���˵��hqlʹ��
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();

			// ��ѯ�������22���ѧ�� criteria

			session = HibernateUtil.getCurrentSession();
			Criteria criteria = session.createCriteria(Student.class);
			// ��Ӽ�������
			criteria.add(Restrictions.gt("sage", new Integer(22)));
			List<Student> list = criteria.list();
			for (Student student : list)
			{
				System.out.println(student.getSname() + " " + student.getSage());
			}

			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}
}
