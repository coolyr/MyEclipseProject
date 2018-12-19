package com.sina.service.imp;

import java.io.Serializable;

import com.sina.service.inter.BaseInterface;
import com.sina.util.HibernateUtil;

public abstract class BaseServiceImp implements BaseInterface
{

	public Object findById(Class clazz, Serializable id)
	{
		// TODO Auto-generated method stub
		return HibernateUtil.findById(clazz, id);
	}
	// ¸ù¾ÝidÉ¾³ý...

}
