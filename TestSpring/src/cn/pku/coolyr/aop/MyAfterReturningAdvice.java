package cn.pku.coolyr.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class MyAfterReturningAdvice implements AfterReturningAdvice
{

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable
	{
		System.out.println("¹Ø±Õ×ÊÔ´£¡£¡");

	}

}
