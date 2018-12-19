package cn.pku.coolyr;

import java.sql.*;

public class TestJdbc
{
	// ʹ��jdbcȥ���� Mysql ���ݿ�
	public static void main(String[] args)
	{

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try
		{
			// 1����������
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
			// DriverManager.registerDriver(new OracleDriver());
			
			
			// 2���õ�����
//			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orclhsp", "scott", "tiger");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTest?useUnicode=true&amp;characterEncoding=UTF-8", "root", "root");
			
			// ��������Ϊ���Զ��ύ
//			connection.setAutoCommit(false);
			
			
			// 3.����sql����(Statement / PreparedStatement /CallableStatement)
			statement = connection.createStatement();
			
			
			// 4.ͨ��statement�����ݿⷢ��sql ָ��.
			// ��ѯ����
			resultSet = statement.executeQuery("select * from student");
			while (resultSet.next())
			{
				System.out.println(resultSet.getInt("id") + " ::: " + resultSet.getString("name"));
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			// ��� sql ������κ����������������ع�.
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
			// �ر���Դ[�ȿ����];
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
				statement = null;// ʹ����������.
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
