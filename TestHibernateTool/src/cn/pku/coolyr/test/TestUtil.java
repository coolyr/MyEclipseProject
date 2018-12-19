package cn.pku.coolyr.test;

import java.util.List;

import cn.pku.coolyr.domain.Course;
import cn.pku.coolyr.domain.Studcourse;
import cn.pku.coolyr.util.HibernateUtil;

public class TestUtil
{

	public static void main(String[] args)
	{

		// ��������ʹ����ǿ��HibernateUtil�����curd.

		String hql1 = "select sname,saddress from Student where sdept=? and sage>?";
		String parameters1[] =
		{ "�����ϵ", "3" };
		List<Object[]> list1 = HibernateUtil.executeQuery(hql1, parameters1);
		for (Object[] s : list1)
		{
			System.out.println(s[0].toString() + " " + s[1].toString());
		}

		// ʹ�ù��߷�ҳ

		String hql2 = "select sname,saddress from Student order by sage";
		String parameters2[] = null;
		List<Object[]> list2 = HibernateUtil.executeQueryByPage(hql2, parameters2, 2, 3);
		for (Object[] s : list2)
		{
			System.out.println(s[0].toString() + " " + s[1].toString());
		}

		/* ��� */

//		Course c = new Course();
//		c.setCname("servlet");
//		c.setCid(5);
//		HibernateUtil.save(c);

		// �����޸�/ɾ��

		String hql3 = "update Student set sage=sage+1 where sdept=?";
		String parameters3[] =
		{ "�����ϵ" };
		try
		{
			HibernateUtil.executeUpdate(hql3, parameters3);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// ����ʾ����ѡ����21�ſγ̵�ѧ����Ϣ

		String hql4 = "select student.sname,student.sdept from Studcourse where course.cid=?";
		String parameters4[] =
		{ "21" };
		List<Object[]> list4 = HibernateUtil.executeQuery(hql4, parameters4);
		for (Object[] s : list4)
		{
			System.out.println(s[0].toString() + " " + s[1].toString());
		}

//		String hql5 = "from Studcourse where course.cid=21";
//		List<Studcourse> list5 = HibernateUtil.executeQuery(hql5, null); // ������������һ���½����.
//		for (Studcourse sc : list5)
//		{
//			System.out.println(sc.getGrade() + sc.getStudent().getSname());
//		}

	}
}
