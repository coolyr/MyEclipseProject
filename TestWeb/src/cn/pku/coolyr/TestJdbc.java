package cn.pku.coolyr;

import java.sql.*;

public class TestJdbc
{
	// 使用jdbc去操作 Mysql 数据库
	public static void main(String[] args)
	{

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try
		{
			// 1、加载驱动
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
			// DriverManager.registerDriver(new OracleDriver());
			
			
			// 2、得到连接
//			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orclhsp", "scott", "tiger");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTest?useUnicode=true&amp;characterEncoding=UTF-8", "root", "root");
			
			// 把事务设为不自动提交
//			connection.setAutoCommit(false);
			
			
			// 3.创建sql对象(Statement / PreparedStatement /CallableStatement)
			statement = connection.createStatement();
			
			
			// 4.通过statement向数据库发出sql 指令.
			// 查询操作
			resultSet = statement.executeQuery("select * from student");
			while (resultSet.next())
			{
				System.out.println(resultSet.getInt("id") + " ::: " + resultSet.getString("name"));
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			// 如果 sql 语句中任何语句错误，则可以整体回滚.
			try
			{
				connection.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
		finally
		{
			// 关闭资源[先开后闭];
			// resultSet
			if (resultSet != null)
			{
				try
				{
					resultSet.close();
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}
				resultSet = null;
			}
			//statement
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				statement = null;// 使用垃圾回收.
			}
			//connection
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				connection = null;
			}
		}
	}
}
