package cn.pku.coolyr.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/pku/coolyr/aop/beans.xml");
		TestServiceInter ts = (TestServiceInter) ac.getBean("proxyFactoryBean");
		//System.out.println("动态代理技术：proxyFactoryBean -> " + ts);
		ts.sayHello();
		((TestServiceInter2) ts).sayBye();

	}

}
