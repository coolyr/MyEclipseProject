package cn.pku.coolyr.inter;

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

		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/pku/coolyr/inter/beans.xml");
		
		// 1.��ȡ,���ýӿ�
		LowwerLetter changeLetter=(LowwerLetter) ac.getBean("changeLette");
		System.out.println(changeLetter.change());
		
		// 2.ʹ�ýӿ�������bean
		ChangeLetter changeLetter1 = (ChangeLetter) ac.getBean("changeLette");
		System.out.println(changeLetter.change());
	}

}
