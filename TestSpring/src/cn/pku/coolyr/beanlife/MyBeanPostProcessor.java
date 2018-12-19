package cn.pku.coolyr.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor
{

	//<!-- �������ǵ��Լ����ô�����(�е��������ǵ�filter) -->
	//<!-- AOP �� ����������-->
	public Object postProcessAfterInitialization(Object object, String arg1) throws BeansException
	{
		System.out.println("postProcessAfterInitialization ����������");
		System.out.println(object + " ��������ʱ����" + new java.util.Date());
		return object;
	}

	public Object postProcessBeforeInitialization(Object object, String arg1) throws BeansException
	{
		System.out.println("postProcessBeforeInitialization ����������");
		return object;
	}

}
