package cn.pku.coolyr.view;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;

import cn.pku.coolyr.domain.Employee;
import cn.pku.coolyr.util.HibernateUtil;
import cn.pku.coolyr.util.MySessionFactory;

public class TestMain
{

	public static void main(String[] args)
	{
		// addEmpoyee();
		// updateEmp();
		// delEmp();
		// queryEmp();
		//queryTest();
		criteriaTest();
	}

	// 添加一个雇员
	public static void addEmpoyee()
	{
		// 1.得到Configuration
		Configuration configuration = new Configuration().configure();
		Configuration configuration2 = new Configuration().configure();

		// 2.得到SessionFactory(会话工厂，这是一个重量级的类，因此要保证在一个应用程序中只能有一个)
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		// 3. 从SessionFactory中取出一个Session对象(它表示和数据库的出一次会话，相当于JDBC Connection)
		Session session = sessionFactory.openSession();
		// 4. 开始一个事务(Hibernate 要求对于增、删、改，必须使用事务。查询不需要。)
		Transaction transaction = session.beginTransaction();
		// 保存一个对象到数据库(持久化一个对象)
		Employee emp = new Employee();
		emp.setName("coolyr");
		emp.setEmail("coolyr@sohu.com");
		emp.setHiredate(new java.util.Date());

		// 不要给id,因为它是自增的
		session.save(emp);// insert into employee (name,id,...) value(?,?,?)
		transaction.commit();// 提交事务
		session.close();// 关闭会话
	}

	// ■ query接口测试
	public static void queryTest()
	{
		// 使用工具类获得绑定到当前对象的session对象。
		Session session = HibernateUtil.getThreadLocalSession();
		Transaction ts = null;

		try
		{

			ts = session.beginTransaction();

			// 获取query引用[这里 Employee不是表.而是domain类名]
			// [经测试：where 后面的条件可以是类的属性名，也可以是表的字段。
			// 按照Hibernate规定，我们还是应该使用类的属性名.]
			Query query = session.createQuery("from Employee where name='coolyr'");
			// 通过list方法获取结果,这个list会自动的将封装成对应的domain对象
			// 所以我们jdbc进行二次封装的工作没有了.
			List<Employee> list = query.list();
			for (Employee e : list)
			{
				System.out.println(e.getId() + " " + e.getName() + " " + e.getHiredate());
			}

			ts.commit();
		}
		catch (Exception e)
		{

			if (ts != null)
			{
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			// 关闭session
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	//■ Criteria接口
	public static void criteriaTest()
	{
		// 使用工具类获得绑定到当前对象的session对象。
		Session session = HibernateUtil.getThreadLocalSession();
		Transaction ts = null;

		try
		{

			ts = session.beginTransaction();

			Criteria cri = session.createCriteria(Employee.class).setMaxResults(2).addOrder(Order.desc("id"));
			List<Employee> list = cri.list();
			for (Employee e : list)
			{
				System.out.println("Id="+e.getId() + "  Name=" + e.getName());
			}

			ts.commit();

		}
		catch (Exception e)
		{

			if (ts != null)
			{
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			// 关闭session
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}
	}

	// 更新
	public static void updateEmp()
	{
		// 修改一个雇员
		SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction ts = null;
		try
		{
			ts = session.beginTransaction();
			// 1.先得到，再修改
			// load方法是用于获取 指定 主键的对象（记录.）
			Employee emp = (Employee) session.load(Employee.class, 1);
			emp.setName("hades01");
			ts.commit();
		}
		catch (Exception e)
		{
			// 抛异常回滚事务
			if (ts != null)
			{
				ts.rollback();
			}
			// 抛运行时异常
			throw new RuntimeException(e);
		}
		finally
		{
			// 关闭session
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}

	}

	// 删除
	public static void delEmp()
	{

		SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction ts = null;
		try
		{
			ts = session.beginTransaction();
			// 1.先得到，再删除
			// load方法是用于获取 指定 主键的对象（记录.）
			Employee emp = (Employee) session.load(Employee.class, 2);
			session.delete(emp);
			ts.commit();
		}
		catch (Exception e)
		{
			// 抛异常回滚事务
			if (ts != null)
			{
				ts.rollback();
			}
			// 抛运行时异常
			throw new RuntimeException(e);
		}
		finally
		{
			// 关闭session
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}
	}

	// 查询
	public static void queryEmp()
	{
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		// 查询可以不使用事务
		Employee emp = (Employee) session.load(Employee.class, 1);
		System.out.println(emp.getName() + " " + emp.getEmail());
		session.close();
	}

}
