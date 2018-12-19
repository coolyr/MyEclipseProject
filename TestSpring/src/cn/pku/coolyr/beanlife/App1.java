package cn.pku.coolyr.beanlife;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class App1
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/pku/coolyr/beanlife/beans.xml");
		PersonService ps = (PersonService) ac.getBean("personService");
		ps.sayHi();

		System.out.println("*********************两种方法不一样************************");
		
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("cn/pku/coolyr/beanlife/beans.xml"));
		PersonService personService = (PersonService) factory.getBean("personService");
		personService.sayHi();
	}
}
