package cn.pku.coolyr.db;

/**
 * ʹ��jdbc���ӷ�ʽʱ�����ݿ�������Ϣ
 * url = jdbc:mysql://localhost:3306/myTest?useUnicode=true&amp;characterEncoding=UTF-8

 */
public class DBConfig
{
	// ��������
	//public static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String DRIVER = "com.mysql.jdbc.Driver";

	// ���ݿ���Ϣ
	//public static final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=";
	public static final String URL = "jdbc:mysql://localhost:3306/";

	// Ҫ���ӵ����ݿ�����
	public static final String DBName = "myTest";

	// �������ݿ��û�����
	public static final String UName = "root";

	// �������ݿ��û�����
	public static final String PWD = "root";
}
