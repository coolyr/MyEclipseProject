package cn.pku.coolyr.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyMethodInterceptor implements MethodInterceptor
{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable
	{
		System.out.println("调用方法前：");
		Object object = invocation.proceed();
		System.out.println("调用方法后：");
		return object;
	}
	

}
