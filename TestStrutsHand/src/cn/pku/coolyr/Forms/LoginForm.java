package cn.pku.coolyr.Forms;

import org.apache.struts.action.ActionForm;
//����һ����½ҳ���õı��������ڷ�װ��½ʱ����������
public class LoginForm extends ActionForm
{
	//�������ԡ�������һ���淶����������jspҳ����Ӧ�ؼ��ĵ�name������һ����
	//��ʵ��ֻҪ�ñ���get��Set�����Ͷ�Ӧ��jspҳ��Ŀؼ���name�������Ӧ����
//	private String name;
//	private String pwd;
//	public String getUsername()
//	{
//		return name;
//	}
//	public void setUsername(String name)
//	{
//		this.name = name;
//	}
//	public String getPassword()
//	{
//		return pwd;
//	}
//	public void setPassword(String pwd)
//	{
//		this.pwd = pwd;
//	}
	
	//********�����������д��ʽҲ����ȷ�ģ�����һ�㰴����ĸ�ʽд*****//
	
	private String username;
	private String password;
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
	
	
	
	
	
	
}
