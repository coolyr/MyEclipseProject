package cn.pku.coolyr.aop;

public class Test1Service implements TestServiceInter, TestServiceInter2
{

	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void sayHello()
	{
		// TODO Auto-generated method stub
		System.out.println("hi " + name);

	}

	public void sayBye()
	{
		// TODO Auto-generated method stub
		System.out.println("bye " + name);
		int n = 9 / 0; // �����쳣֪ͨ
	}

}
