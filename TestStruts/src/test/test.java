package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class test
{ 

	// �������ݿ�Ĳ���
	private static String url = "";
	private static String userName = "";
	private static String driver = "";
	private static String password = "";

	private static Properties propertiesFile = null;
	private static FileInputStream fileInputStream = null;

	public static void main(String[] args) 
	{
		// ��db.properties�ļ��ж�ȡ������Ϣ
		propertiesFile = new Properties();
		// �ŵ�����Ŀ¼��
		// �ŵ�SrcĿ¼��Ҫʹ���������ȥ��ȡ
		//this.getClass().getClassLoader().getResourceAsStream(configFileString);  
		InputStream inputStream =  test.class.getClassLoader().getResourceAsStream("dbinfor.properties");

		//fileInputStream = (FileInputStream)inputStream;
		//System.out.println(inputStream.getClass());
		try
		{
			propertiesFile.load(inputStream);
		}
		catch (IOException e)
		{
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

		url = propertiesFile.getProperty("url");
		System.out.println(url.toString());
		driver = propertiesFile.getProperty("driver");
		System.out.println(driver.toString());
		userName = propertiesFile.getProperty("userName");
		System.out.println(userName.toString());
		password = propertiesFile.getProperty("password");
		System.out.println(password.toString());

	}

}
