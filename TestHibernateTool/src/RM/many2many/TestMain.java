package RM.many2many;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.pku.coolyr.util.HibernateUtil;

public class TestMain
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		// ���һ��Person/idcard

		Session s = null;
		Transaction tx = null;

		try
		{
			// ����ʹ�û���ģ��������.
			s = HibernateUtil.getCurrentSession();
			tx = s.beginTransaction();

			// ���һ��ѧ����һ�ſγ̣�ѡ��
			Student stu1 = new Student();
			stu1.setName("С��");

			Course course = new Course();
			course.setName("java");

			StuCourse sc = new StuCourse();

			sc.setCourse(course);
			sc.setStudent(stu1);

			// ˳�򱣴�.
			s.save(stu1);
			s.save(course);
			s.save(sc);

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

	}

}
