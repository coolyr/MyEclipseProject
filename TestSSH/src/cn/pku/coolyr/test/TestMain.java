package cn.pku.coolyr.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.pku.coolyr.domain.Employee;

public class TestMain
{

	public static void main(String[] args)
	{

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// 1*测试下Spring
//		TestService testService = (TestService)applicationContext.getBean("testService");
//		System.out.println("测试Spring ： " + testService.getName());
		
		//2*测试Spring 和 Hibernate 的结合
		SessionFactory sessionFactory = (SessionFactory)applicationContext.getBean("sessionFactory");
		
		Session session = sessionFactory.openSession();
		Employee employee = new Employee("coolyr", "123@qq.com", new java.util.Date(), 5000.0f);
		
		Transaction transaction = session.beginTransaction();
		session.save(employee);
		transaction.commit();
		
	}

}
