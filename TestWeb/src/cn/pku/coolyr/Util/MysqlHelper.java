package cn.pku.coolyr.Util;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MysqlHelper
{
	// ����������Ҫ�ı���
	// ����������ݿ��Ƶ���������ǵ�Connection ��PreparedStatement,ResultSet.. �Ͳ�Ҫ���static
	private static Connection connection = null;
	// private static Statement statement = null;
	private static PreparedStatement preparedStatment = null;// ʹ��PreparedStatement����Statement��ֹsqlע�빥����
	private static CallableStatement callableStatement = null;// ִ�д洢���̵�statement����
	private static ResultSet resultSet = null;

	// �������ݿ�Ĳ���
	private static String url = "";
	private static String userName = "";
	private static String driver = "";
	private static String password = "";

	private static Properties propertiesFile = null;
	private static FileInputStream fileInputStream = null;

	public static Connection getConnection()
	{
		return connection;
	}

	public static PreparedStatement getPreparedStatment()
	{
		return preparedStatment;
	}

	public static ResultSet getResultSet()
	{
		return resultSet;
	}

	public static CallableStatement getCallableStatement()
	{
		return callableStatement;
	}

	// ����������ֻ��һ��
	static
	{
		try
		{
			// ��db.properties�ļ��ж�ȡ������Ϣ
			propertiesFile = new Properties();
			fileInputStream = new FileInputStream("db.properties");
			propertiesFile.load(fileInputStream);

			url = propertiesFile.getProperty("url");
			System.out.println(url.toString());
			driver = propertiesFile.getProperty("driver");
			System.out.println(driver.toString());
			userName = propertiesFile.getProperty("userName");
			System.out.println(userName.toString());
			password = propertiesFile.getProperty("password");
			System.out.println(password.toString());

			Class.forName(driver);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fileInputStream.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			fileInputStream = null;
		}
	}

	// �õ�����
	public static Connection initConnection()
	{
		try
		{
			connection = DriverManager.getConnection(url, userName, password);
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;
	}

	// һ��update / delete / insert
	// sql��ʽ ��update ���� set �ֶ���=��where �ֶ��� = ��
	// parameters��ʽ�� ["abc",30]
	/**
	 * 
	 * @param sql
	 * @param parameters
	 *            ����String��͵ģ���--(��sql��ʹ��oracle�Դ��ĺ���ת����ʽ)
	 */
	public static void executeUpdate(String sql, String[] parameters)
	{
		// ����һ��connection
		try
		{
			connection = initConnection();
			preparedStatment = connection.prepareStatement(sql);
			// ������ֵ
			if (parameters != null)
			{
				for (int i = 0; i < parameters.length; i++)
				{
					preparedStatment.setString(i + 1, parameters[i]);// ����setString(),�������int����Ҳ�����𣿣�
																		// �е����ݿ���Զ�ת��������int
																		// ->
																		// string
																		// ���������SQL����о��������ݿ��Դ��ĺ���ʱ��ת��
				}
			}
			preparedStatment.executeUpdate();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();// �����׶�
			// �׳��쳣
			// �׳�����ʱ�쳣���Ը����øú������û�һ��ѡ�񣬿��Դ���Ҳ���Է�������
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			// �ر���Դ
			close(resultSet, preparedStatment, connection);
		}

	}

	// ���update / delete / insert����Ҫ��������
	public static void executeUpdateOfTransaction(String[] sql, String[][] parameters)
	{
		try
		{
			// ����
			// �������
			connection = initConnection();

			// ��Ϊ�û����ܴ��� ���sql���
			connection.setAutoCommit(false);// ����ִ�з��Զ��ύ����
			// ...
			for (int i = 0; i < sql.length; i++)
			{
				if (parameters != null)
				{
					preparedStatment = connection.prepareStatement(sql[i]);
					for (int j = 0; j < parameters[i].length; j++)
					{
						preparedStatment.setString(j + 1, parameters[i][j]);
					}
					preparedStatment.executeUpdate();
				}
			}

			connection.commit();// �ֶ��ύ

		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();// �����׶�
			try
			{
				// �׳��쳣�ع�����
				connection.rollback();
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// �׳��쳣
			// �׳�����ʱ�쳣���Ը����øú������û�һ��ѡ�񣬿��Դ���Ҳ���Է�������
			throw new RuntimeException(e.getMessage());

		}
		finally
		{
			// �ر���Դ
			close(resultSet, preparedStatment, connection);
		}
	}

	// ͳһ�Ĳ�ѯ����
	public static ResultSet executeQueryForRS(String sql, String[] parameters)
	{
		try
		{
			connection = initConnection();
			preparedStatment = connection.prepareStatement(sql);
			if (parameters != null && !parameters.equals(""))
			{
				for (int i = 0; i < parameters.length; i++)
				{
					preparedStatment.setString(i + 1, parameters[i]);
				}
			}
			resultSet = preparedStatment.executeQuery();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();// �����׶�
			// �׳��쳣
			// �׳�����ʱ�쳣���Ը����øú������û�һ��ѡ�񣬿��Դ���Ҳ���Է�������
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			// ��Ҫ�ӳٹرգ��ͻ���Ҫʹ��ResultSet
		}
		return resultSet;
	}

	public ArrayList<Object> executeQueryForALOJ(String sql, String[] parameters)
	{
		connection = initConnection();// �������ݿ�
		ArrayList<Object> list = null;
		try
		{
			preparedStatment = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (parameters != null)
			{
				// System.out.println(sql);
				for (int i = 0; i < parameters.length; i++)
				{
					// System.out.println(i+"    "+parameters[i]);
					preparedStatment.setString((i + 1), parameters[i]);
				}
			}
			resultSet = preparedStatment.executeQuery();
			list = new ArrayList<Object>();
			// ��ȡResultSet��Ԫ������Ϣ
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			// ��ȡ��¼�����Եĸ���
			int column = resultSetMetaData.getColumnCount();
			while (resultSet.next())
			{
				Object objs[] = new Object[column];
				for (int i = 0; i < column; i++)
				{
					objs[i] = resultSet.getObject(i + 1);
				}
				list.add(objs);
			}

		}
		catch (Exception ex)
		{
			Logger.getLogger(MysqlHelper.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException(ex.getMessage());
		}
		finally
		{
			// �õķ���������ʹ������Դ��������ر�
			close(resultSet, callableStatement, connection);
			; // �ر���Դ
		}
		return list;
	}

	public ArrayList<HashMap<String, Object>> executeQueryForALHM(String sql, String[] parameters)
	{
		connection = initConnection();// �������ݿ�
		ArrayList<HashMap<String, Object>> list = null;
		try
		{
			preparedStatment = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (parameters != null)
			{
				// System.out.println(sql);
				for (int i = 0; i < parameters.length; i++)
				{
					// System.out.println(i+"    "+parameters[i]);
					preparedStatment.setString((i + 1), parameters[i]);
				}
			}
			resultSet = preparedStatment.executeQuery();
			list = new ArrayList<HashMap<String, Object>>();
			int column = resultSet.getMetaData().getColumnCount();
			while (resultSet.next())
			{
				//������������ʹ��LinkedHashMap
				HashMap<String, Object> obj = new LinkedHashMap<String, Object>();
				for (int i = 0; i < column; i++)
				{
					//����
					String key = resultSet.getMetaData().getColumnName(i + 1);
					//��ֵ
					Object val = resultSet.getObject(i + 1);
					// ������������ֵ
					obj.put(key, val);
				}
				list.add(obj);
			}

		}
		catch (Exception ex)
		{
			Logger.getLogger(MysqlHelper.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException(ex.getMessage());
		}
		finally
		{
			// �õķ���������ʹ������Դ��������ر�
			close(resultSet, callableStatement, connection); // �ر���Դ
		}
		return list;
	}

	// ����ҳ�Ĳ�ѯ����
	public static ResultSet executeQueryByPageForRS(String sql, String[] parameters, int pageNow, int pageSize)
	{
		// ����ж��´����pageNow��pageSize�Ƿ����
		if (pageNow < 1 || pageSize < 0)
		{
			return null;
		}

		try
		{
			connection = initConnection();
			// ƴ��limit�﷨��ʱ�������ûƴ��ǰ�и�;��
			if (sql.contains(";"))
			{
				int location = sql.indexOf(";");
				sql = sql.substring(0, location);
			}
			// select * from ���� where ���� [group by .. having .. order by ..]
			// limit (pageNow-1)*pagesize, pageSize;
			sql = sql + " limit " + (pageNow - 1) * pageSize + "," + pageSize;
			preparedStatment = connection.prepareStatement(sql);
			if (parameters != null && !parameters.equals(""))
			{
				for (int i = 0; i < parameters.length; i++)
				{
					preparedStatment.setString(i + 1, parameters[i]);
				}
			}
			resultSet = preparedStatment.executeQuery();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();// �����׶�
			// �׳��쳣
			// �׳�����ʱ�쳣���Ը����øú������û�һ��ѡ�񣬿��Դ���Ҳ���Է�������
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			// ��Ҫ�ӳٹرգ��ͻ���Ҫʹ��ResultSet
		}
		return resultSet;
	}

	// ���ô洢����--�޽��
	// sql: {call �������������б�����..��}
	public static void callProcedure(String sql, String[] parameters)
	{
		try
		{
			connection = initConnection();
			callableStatement = connection.prepareCall(sql);
			// ?��ֵ
			if (parameters != null)
			{
				for (int i = 0; i < parameters.length; i++)
				{
					callableStatement.setObject(i + 1, parameters[i]);
				}
			}
			// ִ�д洢����
			callableStatement.execute();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			// �׳�����ʱ�쳣���Ը����øú������û�һ��ѡ�񣬿��Դ���Ҳ���Է�������
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			close(resultSet, callableStatement, connection);
		}
	}

	// ���ô洢����,���ؽ����
	// sql: {call �������������б�����..��}
	public static CallableStatement callProcedureForResult(String sql, String[] inparameters, Integer[] outparameters)
	{
		try
		{
			connection = initConnection();
			callableStatement = connection.prepareCall(sql);
			// ? in��ֵ
			if (inparameters != null)
			{
				for (int i = 0; i < inparameters.length; i++)
				{
					callableStatement.setObject(i + 1, inparameters[i]);
				}
			}

			// ? out ��ֵ
			if (outparameters != null)
			{
				for (int i = 0; i < outparameters.length; i++)
				{
					callableStatement.registerOutParameter(inparameters.length + 1 + i, outparameters[i]);
				}
			}

			// ִ�д洢����
			callableStatement.execute();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			// �׳�����ʱ�쳣���Ը����øú������û�һ��ѡ�񣬿��Դ���Ҳ���Է�������
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			// ����ر�
		}
		return callableStatement;
	}

	// �ر���Դ
	public static void close(ResultSet resultSet, Statement statement, Connection connection)
	{
		// TODO Auto-generated method stub
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
		// statement
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
		// connection
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
