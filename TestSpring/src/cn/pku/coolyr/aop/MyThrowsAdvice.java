package cn.pku.coolyr.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class MyThrowsAdvice implements ThrowsAdvice
{
	// �ýӿ�Ϊ��ʶ�Խӿڣ�û���κη�������ʵ�ָýӿڵ������Ҫ��������
	// ʽ�ķ����� ��ѡһ
	
	// void afterThrowing(Throwable throwable);
	
	
	//ע�⵼���Method�������Ǹ����ģ�java�����
	public void afterThrowing(Method m, Object[] os, Object target, Exception e)
	{
		System.out.println("��������" + e.getMessage());
	}

}
