package cn.pku.coolyr.Domain;

public class User
{
	private String username;
	private String password;
	private String newphoto;// ##�����µ��ļ���
	private String oldphoto;// ##����ɵ��ļ���

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

	public String getNewphoto()
	{
		return newphoto;
	}

	public void setNewphoto(String newphoto)
	{
		this.newphoto = newphoto;
	}

	public String getOldphoto()
	{
		return oldphoto;
	}

	public void setOldphoto(String oldphoto)
	{
		this.oldphoto = oldphoto;
	}

}
