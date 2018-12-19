package cn.pku.coolyr.model.util;

//数据库连接类

import java.sql.*;

public class ConnDB
{

	private Connection ct = null;

	public Connection getConn()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTest?useUnicode=true&amp;characterEncoding=UTF-8", "root", "root");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return ct;

	}

}
