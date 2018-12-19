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
	// 定义链接需要的变量
	// 如果访问数据库很频繁，则我们的Connection 、PreparedStatement,ResultSet.. 就不要搞成static
	private static Connection connection = null;
	// private static Statement statement = null;
	private static PreparedStatement preparedStatment = null;// 使用PreparedStatement代替Statement防止sql注入攻击。
	private static CallableStatement callableStatement = null;// 执行存储过程的statement对象
	private static ResultSet resultSet = null;

	// 链接数据库的参数
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

	// 加载驱动，只需一次
	static
	{
		try
		{
			// 从db.properties文件中读取配置信息
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

	// 得到连接
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

	// 一个update / delete / insert
	// sql格式 ：update 表名 set 字段名=？where 字段名 = ？
	// parameters格式： ["abc",30]
	/**
	 * 
	 * @param sql
	 * @param parameters
	 *            都是String類型的？？--(在sql中使用oracle自带的函数转换格式)
	 */
	public static void executeUpdate(String sql, String[] parameters)
	{
		// 创建一个connection
		try
		{
			connection = initConnection();
			preparedStatment = connection.prepareStatement(sql);
			// 给？赋值
			if (parameters != null)
			{
				for (int i = 0; i < parameters.length; i++)
				{
					preparedStatment.setString(i + 1, parameters[i]);// 都是setString(),如果出现int类型也可以吗？？
																		// 有的数据库会自动转换，比如int
																		// ->
																		// string
																		// 但是最好在SQL语句中就是用数据库自带的函数时先转换
				}
			}
			preparedStatment.executeUpdate();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();// 开发阶段
			// 抛出异常
			// 抛出运行时异常可以给调用该函数的用户一个选择，可以处理，也可以放弃处理。
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			// 关闭资源
			close(resultSet, preparedStatment, connection);
		}

	}

	// 多个update / delete / insert【需要考虑事务】
	public static void executeUpdateOfTransaction(String[] sql, String[][] parameters)
	{
		try
		{
			// 核心
			// 获得链接
			connection = initConnection();

			// 因为用户可能传入 多个sql语句
			connection.setAutoCommit(false);// 设置执行非自动提交事务
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

			connection.commit();// 手动提交

		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();// 开发阶段
			try
			{
				// 抛出异常回滚事务
				connection.rollback();
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// 抛出异常
			// 抛出运行时异常可以给调用该函数的用户一个选择，可以处理，也可以放弃处理。
			throw new RuntimeException(e.getMessage());

		}
		finally
		{
			// 关闭资源
			close(resultSet, preparedStatment, connection);
		}
	}

	// 统一的查询函数
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
			e.printStackTrace();// 开发阶段
			// 抛出异常
			// 抛出运行时异常可以给调用该函数的用户一个选择，可以处理，也可以放弃处理。
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			// 需要延迟关闭；客户端要使用ResultSet
		}
		return resultSet;
	}

	public ArrayList<Object> executeQueryForALOJ(String sql, String[] parameters)
	{
		connection = initConnection();// 连接数据库
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
			// 获取ResultSet的元数据信息
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			// 获取记录的属性的个数
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
			// 好的方法：哪里使用了资源就在那里关闭
			close(resultSet, callableStatement, connection);
			; // 关闭资源
		}
		return list;
	}

	public ArrayList<HashMap<String, Object>> executeQueryForALHM(String sql, String[] parameters)
	{
		connection = initConnection();// 连接数据库
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
				//让属性有序存放使用LinkedHashMap
				HashMap<String, Object> obj = new LinkedHashMap<String, Object>();
				for (int i = 0; i < column; i++)
				{
					//列名
					String key = resultSet.getMetaData().getColumnName(i + 1);
					//列值
					Object val = resultSet.getObject(i + 1);
					// 属性名：属性值
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
			// 好的方法：哪里使用了资源就在那里关闭
			close(resultSet, callableStatement, connection); // 关闭资源
		}
		return list;
	}

	// 带分页的查询函数
	public static ResultSet executeQueryByPageForRS(String sql, String[] parameters, int pageNow, int pageSize)
	{
		// 最好判断下传入的pageNow和pageSize是否合理！
		if (pageNow < 1 || pageSize < 0)
		{
			return null;
		}

		try
		{
			connection = initConnection();
			// 拼接limit语法的时候可能在没拼接前有个;号
			if (sql.contains(";"))
			{
				int location = sql.indexOf(";");
				sql = sql.substring(0, location);
			}
			// select * from 表名 where 条件 [group by .. having .. order by ..]
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
			e.printStackTrace();// 开发阶段
			// 抛出异常
			// 抛出运行时异常可以给调用该函数的用户一个选择，可以处理，也可以放弃处理。
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			// 需要延迟关闭；客户端要使用ResultSet
		}
		return resultSet;
	}

	// 调用存储过程--无结果
	// sql: {call 过程名（参数列表？，？..）}
	public static void callProcedure(String sql, String[] parameters)
	{
		try
		{
			connection = initConnection();
			callableStatement = connection.prepareCall(sql);
			// ?赋值
			if (parameters != null)
			{
				for (int i = 0; i < parameters.length; i++)
				{
					callableStatement.setObject(i + 1, parameters[i]);
				}
			}
			// 执行存储过程
			callableStatement.execute();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			// 抛出运行时异常可以给调用该函数的用户一个选择，可以处理，也可以放弃处理。
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			close(resultSet, callableStatement, connection);
		}
	}

	// 调用存储过程,返回结果集
	// sql: {call 过程名（参数列表？，？..）}
	public static CallableStatement callProcedureForResult(String sql, String[] inparameters, Integer[] outparameters)
	{
		try
		{
			connection = initConnection();
			callableStatement = connection.prepareCall(sql);
			// ? in赋值
			if (inparameters != null)
			{
				for (int i = 0; i < inparameters.length; i++)
				{
					callableStatement.setObject(i + 1, inparameters[i]);
				}
			}

			// ? out 赋值
			if (outparameters != null)
			{
				for (int i = 0; i < outparameters.length; i++)
				{
					callableStatement.registerOutParameter(inparameters.length + 1 + i, outparameters[i]);
				}
			}

			// 执行存储过程
			callableStatement.execute();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			// 抛出运行时异常可以给调用该函数的用户一个选择，可以处理，也可以放弃处理。
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			// 无需关闭
		}
		return callableStatement;
	}

	// 关闭资源
	public static void close(ResultSet resultSet, Statement statement, Connection connection)
	{
		// TODO Auto-generated method stub
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
			statement = null;// 使用垃圾回收.
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
