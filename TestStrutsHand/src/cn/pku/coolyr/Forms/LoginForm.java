package cn.pku.coolyr.Forms;

import org.apache.struts.action.ActionForm;
//这是一个登陆页面用的表单对象，用于封装登陆时候填充的数据
public class LoginForm extends ActionForm
{
	//定义属性【这里有一个规范：属性名和jsp页面相应控件的的name属性名一样】
	//其实：只要该表单的get和Set方法和对应的jsp页面的控件的name属性相对应就行
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
	
	//********所以上面的书写格式也是正确的，但是一般按下面的格式写*****//
	
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
