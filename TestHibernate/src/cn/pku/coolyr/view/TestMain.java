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

	// ���һ����Ա
	public static void addEmpoyee()
	{
		// 1.�õ�Configuration
		Configuration configuration = new Configuration().configure();
		Configuration configuration2 = new Configuration().configure();

		// 2.�õ�SessionFactory(�Ự����������һ�����������࣬���Ҫ��֤��һ��Ӧ�ó�����ֻ����һ��)
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		// 3. ��SessionFactory��ȡ��һ��Session����(����ʾ�����ݿ�ĳ�һ�λỰ���൱��JDBC Connection)
		Session session = sessionFactory.openSession();
		// 4. ��ʼһ������(Hibernate Ҫ���������ɾ���ģ�����ʹ�����񡣲�ѯ����Ҫ��)
		Transaction transaction = session.beginTransaction();
		// ����һ���������ݿ�(�־û�һ������)
		Employee emp = new Employee();
		emp.setName("coolyr");
		emp.setEmail("coolyr@sohu.com");
		emp.setHiredate(new java.util.Date());

		// ��Ҫ��id,��Ϊ����������
		session.save(emp);// insert into employee (name,id,...) value(?,?,?)
		transaction.commit();// �ύ����
		session.close();// �رջỰ
	}

	// �� query�ӿڲ���
	public static void queryTest()
	{
		// ʹ�ù������ð󶨵���ǰ�����session����
		Session session = HibernateUtil.getThreadLocalSession();
		Transaction ts = null;

		try
		{

			ts = session.beginTransaction();

			// ��ȡquery����[���� Employee���Ǳ�.����domain����]
			// [�����ԣ�where ��������������������������Ҳ�����Ǳ���ֶΡ�
			// ����Hibernate�涨�����ǻ���Ӧ��ʹ�����������.]
			Query query = session.createQuery("from Employee where name='coolyr'");
			// ͨ��list������ȡ���,���list���Զ��Ľ���װ�ɶ�Ӧ��domain����
			// ��������jdbc���ж��η�װ�Ĺ���û����.
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
			// �ر�session
			if (session != null && session.isOpen())
			{
				session.close();
			}

		}
	}

	//�� Criteria�ӿ�
	public static void criteriaTest()
	{
		// ʹ�ù������ð󶨵���ǰ�����session����
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
			// �ر�session
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}
	}

	// ����
	public static void updateEmp()
	{
		// �޸�һ����Ա
		SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction ts = null;
		try
		{
			ts = session.beginTransaction();
			// 1.�ȵõ������޸�
			// load���������ڻ�ȡ ָ�� �����Ķ��󣨼�¼.��
			Employee emp = (Employee) session.load(Employee.class, 1);
			emp.setName("hades01");
			ts.commit();
		}
		catch (Exception e)
		{
			// ���쳣�ع�����
			if (ts != null)
			{
				ts.rollback();
			}
			// ������ʱ�쳣
			throw new RuntimeException(e);
		}
		finally
		{
			// �ر�session
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}

	}

	// ɾ��
	public static void delEmp()
	{

		SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction ts = null;
		try
		{
			ts = session.beginTransaction();
			// 1.�ȵõ�����ɾ��
			// load���������ڻ�ȡ ָ�� �����Ķ��󣨼�¼.��
			Employee emp = (Employee) session.load(Employee.class, 2);
			session.delete(emp);
			ts.commit();
		}
		catch (Exception e)
		{
			// ���쳣�ع�����
			if (ts != null)
			{
				ts.rollback();
			}
			// ������ʱ�쳣
			throw new RuntimeException(e);
		}
		finally
		{
			// �ر�session
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}
	}

	// ��ѯ
	public static void queryEmp()
	{
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		// ��ѯ���Բ�ʹ������
		Employee emp = (Employee) session.load(Employee.class, 1);
		System.out.println(emp.getName() + " " + emp.getEmail());
		session.close();
	}

}
