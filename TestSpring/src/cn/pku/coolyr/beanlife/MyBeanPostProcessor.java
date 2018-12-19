package cn.pku.coolyr.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor
{

	//<!-- 配置我们的自己后置处理器(有点类似我们的filter) -->
	//<!-- AOP ： 面向切面编程-->
	public Object postProcessAfterInitialization(Object object, String arg1) throws BeansException
	{
		System.out.println("postProcessAfterInitialization 函数被调用");
		System.out.println(object + " 被创建的时间是" + new java.util.Date());
		return object;
	}

	public Object postProcessBeforeInitialization(Object object, String arg1) throws BeansException
	{
		System.out.println("postProcessBeforeInitialization 函数被调用");
		return object;
	}

}
