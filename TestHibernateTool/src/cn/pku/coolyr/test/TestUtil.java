package cn.pku.coolyr.test;

import java.util.List;

import cn.pku.coolyr.domain.Course;
import cn.pku.coolyr.domain.Studcourse;
import cn.pku.coolyr.util.HibernateUtil;

public class TestUtil
{

	public static void main(String[] args)
	{

		// 这里我们使用增强的HibernateUtil来完成curd.

		String hql1 = "select sname,saddress from Student where sdept=? and sage>?";
		String parameters1[] =
		{ "计算机系", "3" };
		List<Object[]> list1 = HibernateUtil.executeQuery(hql1, parameters1);
		for (Object[] s : list1)
		{
			System.out.println(s[0].toString() + " " + s[1].toString());
		}

		// 使用工具分页

		String hql2 = "select sname,saddress from Student order by sage";
		String parameters2[] = null;
		List<Object[]> list2 = HibernateUtil.executeQueryByPage(hql2, parameters2, 2, 3);
		for (Object[] s : list2)
		{
			System.out.println(s[0].toString() + " " + s[1].toString());
		}

		/* 添加 */

//		Course c = new Course();
//		c.setCname("servlet");
//		c.setCid(5);
//		HibernateUtil.save(c);

		// 调用修改/删除

		String hql3 = "update Student set sage=sage+1 where sdept=?";
		String parameters3[] =
		{ "计算机系" };
		try
		{
			HibernateUtil.executeUpdate(hql3, parameters3);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// 请显示所有选择了21号课程的学生信息

		String hql4 = "select student.sname,student.sdept from Studcourse where course.cid=?";
		String parameters4[] =
		{ "21" };
		List<Object[]> list4 = HibernateUtil.executeQuery(hql4, parameters4);
		for (Object[] s : list4)
		{
			System.out.println(s[0].toString() + " " + s[1].toString());
		}

//		String hql5 = "from Studcourse where course.cid=21";
//		List<Studcourse> list5 = HibernateUtil.executeQuery(hql5, null); // 懒加载我们有一个章节详解.
//		for (Studcourse sc : list5)
//		{
//			System.out.println(sc.getGrade() + sc.getStudent().getSname());
//		}

	}
}
