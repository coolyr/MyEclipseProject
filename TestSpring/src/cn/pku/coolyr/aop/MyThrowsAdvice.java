package cn.pku.coolyr.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class MyThrowsAdvice implements ThrowsAdvice
{
	// 该接口为标识性接口，没有任何方法，但实现该接口的类必须要有如下形
	// 式的方法： 二选一
	
	// void afterThrowing(Throwable throwable);
	
	
	//注意导入的Method方法是那个包的：java反射包
	public void afterThrowing(Method m, Object[] os, Object target, Exception e)
	{
		System.out.println("出错啦！" + e.getMessage());
	}

}
