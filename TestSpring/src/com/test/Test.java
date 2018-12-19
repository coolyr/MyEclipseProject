package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.pku.coolyr.util.ApplicaionContextUtil;

import com.service.ByeService;
import com.service.UserService;

public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		// 我们先使用传统的方法，来调用UserService的 sayHello方法
		// UserService userService=new UserService();
		// userService.setName("顺平");
		// userService.sayHello();

		// 我们现在使用spring来完成上面的任务
		// 1.得到spring 的applicationContext对象(容器对象)
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2.从ac[代表applicatonContext容器]中取出需要的bean对象。
		UserService us = (UserService) ac.getBean("userService");
		us.sayHello();
		
		// ((UserService)
		// ApplicaionContextUtil.getApplicationContext().getBean("userService")).sayHello();

		// 从ac[代表applicatonContext容器]中
		// ByeService bybService=(ByeService) ac.getBean("bybService");
		// bybService.sayBye();

	}

}
