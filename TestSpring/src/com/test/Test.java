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

		// ������ʹ�ô�ͳ�ķ�����������UserService�� sayHello����
		// UserService userService=new UserService();
		// userService.setName("˳ƽ");
		// userService.sayHello();

		// ��������ʹ��spring��������������
		// 1.�õ�spring ��applicationContext����(��������)
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2.��ac[����applicatonContext����]��ȡ����Ҫ��bean����
		UserService us = (UserService) ac.getBean("userService");
		us.sayHello();
		
		// ((UserService)
		// ApplicaionContextUtil.getApplicationContext().getBean("userService")).sayHello();

		// ��ac[����applicatonContext����]��
		// ByeService bybService=(ByeService) ac.getBean("bybService");
		// bybService.sayBye();

	}

}
