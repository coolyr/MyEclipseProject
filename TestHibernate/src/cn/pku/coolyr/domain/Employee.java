package cn.pku.coolyr.domain;

import java.io.Serializable;

//这是一个domain对象(实际上就是javabean/pojo对象)
//它和表employee对应,类名建议是对应表名首字母大写。
//domain对象必须实现序列化接口,用于数据持久化.目的：可以唯一标示该对象，同时可以用于网络和文件传输。
public class Employee implements Serializable
{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;
	private java.util.Date hiredate;

	//必须提供一个默认的构造函数
	//hibernate底层是使用反射机制的，解析会用到无参构造函数
	public Employee()
	{
		super();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public java.util.Date getHiredate()
	{
		return hiredate;
	}

	public void setHiredate(java.util.Date hiredate)
	{
		this.hiredate = hiredate;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
