package cn.pku.coolyr.db;

/**
 * 使用jdbc连接方式时的数据库配置信息
 * url = jdbc:mysql://localhost:3306/myTest?useUnicode=true&amp;characterEncoding=UTF-8

 */
public class DBConfig
{
	// 驱动名称
	//public static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String DRIVER = "com.mysql.jdbc.Driver";

	// 数据库信息
	//public static final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=";
	public static final String URL = "jdbc:mysql://localhost:3306/";

	// 要连接的数据库名称
	public static final String DBName = "myTest";

	// 连接数据库用户名称
	public static final String UName = "root";

	// 连接数据库用户密码
	public static final String PWD = "root";
}
