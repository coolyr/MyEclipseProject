package cn.pku.coolyr.inherit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App1
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/pku/coolyr/inherit/beans.xml");

		Gradate gradate = (Gradate) ac.getBean("grdate");
		System.out.println(gradate.getName() + " " + gradate.getAge() + " " + gradate.getDegree());
	}

}
