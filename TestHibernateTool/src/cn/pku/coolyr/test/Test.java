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
		// 这我们举例说明hql使用
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 1.检索所有的学生
			List<Student> list = session.createQuery("from Student").list();
			// 取出1. for 增强
			for (Student s : list)
			{
				System.out.println(s.getSname() + " " + s.getSaddress());
			}
			System.out.println("*****************");

			// 2. 使用iterator
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
			// 2.检索的学生名字和所在系
			// 原则: 在讲解jdbc我们曾说过， 要查询什么字段就查询什么字段，不要select * from 表
			// 但是在hibernate ,我们其实可以不遵循这个规则,建议我们把整个对象的属性都查询
			// 这里我们还是要讲解如何取出部分属性
			List<Object[]> list = session.createQuery("select sname,sdept from Student").list();
			for (int i = 0; i < list.size(); i++)
			{
				Object[] objs = (Object[]) list.get(i);
				System.out.println(objs[0].toString() + " " + objs[1].toString());
			}
			System.out.println("****************");
			// 如果使用iterator
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
		// 这我们举例说明hql使用
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();

			// 如果我们检索一个对象，明确知道最多只有一个对象，则建议使用该方法:
			// 具体用法如下:
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
		// 这我们举例说明hql使用
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 过滤重复的记录
			// 比如，显示所有学生的性别和年龄.
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
		// 这我们举例说明hql使用
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 计算年龄在20~22之间的学生
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
		// 这我们举例说明hql使用
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 查询计算机系和外语系的学生信息
			List<Student> list = session.createQuery("from Student where sdept not in ('计算机系','外语系')").list();
			// for 增强
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
		// 这我们举例说明hql使用
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// having的使用

			// 1.对分组查询后的结果，进行筛选:比如请显示人数大于3的系名称
			// a. 查询各个系分别有多少学生.
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
		// 这我们举例说明hql使用
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 显示各个系的学生的平均年龄
			List<Object[]> list = session.createQuery("select avg(sage),sdept from  Student group by sdept").list();
			// 取出 for 增强
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
		// 这我们举例说明hql使用
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();

			// *** 1.查询计算机系共多少人?->如果我们返回的是一列数据
			// 这时我们的取法是直接取出list->object 而不是 list->Object[]
			List<Object[]> list0 = session.createQuery("select count(*) from  Student where sdept='计算机系'").list();
			System.out.println("********1.查询计算机系共多少人?********");
			for (Object obj : list0)
			{
				System.out.println(obj.toString());
			}

			// ***2.查询总学分是多少?
			List<Object[]> list1 = session.createQuery("select sum(grade) from Studcourse").list();
			System.out.println("********2.查询总学分是多少?********");
			for (Object obj : list1)
			{
				System.out.println(obj.toString());
			}

			// *** 3.查询选修11号课程的最高分和最低分.
			List<Object[]> list2 = session.createQuery("select 11,max(grade),min(grade) from Studcourse where course.cid=11").list();
			System.out.println("********3.查询选修11号课程的最高分和最低分?********");
			for (Object[] obj : list2)
			{
				System.out.println(obj[0].toString() + " max=" + obj[1].toString() + " min=" + obj[2].toString());
			}

			// ***
			// 4.显示各科考试不及格学生的名字(Student-student)，科目(Course-course)和分数(Studcourse-studcourse)
			List<Object[]> list4 = session.createQuery("select student.sname,course.cname,grade from Studcourse where grade>=60").list();
			System.out.println("********4.显示各科考试不及格学生的名字(Student-student)，科目(Course-course)和分数(Studcourse-studcourse)?********");

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

	// 分页函数
	public static void showResultByPage(int pageSize)
	{
		// 设置分页的变量
		int pageNow = 1;
		int pageCount = 1;// 计算
		int rowCount = 1; // 这个需要查询

		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();

			// 查询出总行数：rowcount
			rowCount = Integer.parseInt(session.createQuery("select count(*) from Student").uniqueResult().toString());
			// 计算总页数：pageCount
			pageCount = (rowCount - 1) / pageSize + 1;

			// 现在我们可以循环的显示每页的信息
			for (int i = 1; i <= pageCount; i++)
			{
				System.out.println("************第" + i + "页************");
				// setFirstResult : 设置从第几条记录取
				// setMaxResults : 设置取几条
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

	// 参数绑定
	public static void selectParameterBingding()
	{
		// 这我们举例说明hql使用
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();

			// 参数绑定有两种形式

			// （1）如果我们的参数是 :冒号形式给出的，则我们的参数绑定应当这样:
			List<Student> list1 = session.createQuery("from Student where sdept=:a1 and sage>:sage").setString("a1", "计算机系").setString("sage", "2").list();
			for (int i = 0; i < list1.size(); i++)
			{
				Student s = list1.get(i);
				System.out.println(s.getSname() + " " + s.getSage());
			}

			// （2）还有一种形式:参数是以？形式给出的
			List<Student> list2 = session.createQuery("from Student where sdept=? and sage>?").setString(0, "计算机系").setString(1, "2").list();
			for (int i = 0; i < list2.size(); i++)
			{
				Student s = list2.get(i);
				System.out.println(s.getSname() + " " + s.getSage());
			}

			// （3）参数的绑定，可以分开写,用于使用For循环绑定参数
			Query query = session.createQuery("from Student where sdept=? and sage>?");
			query.setString(0, "计算机系");
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

	// 参数绑定
	public static void selectCriteria()
	{
		// 这我们举例说明hql使用
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();

			// 查询年龄大于22岁的学生 criteria

			session = HibernateUtil.getCurrentSession();
			Criteria criteria = session.createCriteria(Student.class);
			// 添加检索条件
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
