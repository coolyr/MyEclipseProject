package cn.pku.coolyr.domain;

import java.io.Serializable;

//����һ��domain����(ʵ���Ͼ���javabean/pojo����)
//���ͱ�employee��Ӧ,���������Ƕ�Ӧ��������ĸ��д��
//domain�������ʵ�����л��ӿ�,�������ݳ־û�.Ŀ�ģ�����Ψһ��ʾ�ö���ͬʱ��������������ļ����䡣
public class Employee implements Serializable
{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;
	private java.util.Date hiredate;

	//�����ṩһ��Ĭ�ϵĹ��캯��
	//hibernate�ײ���ʹ�÷�����Ƶģ��������õ��޲ι��캯��
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
