package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class test
{ 

	// 链接数据库的参数
	private static String url = "";
	private static String userName = "";
	private static String driver = "";
	private static String password = "";

	private static Properties propertiesFile = null;
	private static FileInputStream fileInputStream = null;

	public static void main(String[] args) 
	{
		// 从db.properties文件中读取配置信息
		propertiesFile = new Properties();
		// 放到工程目录下
		// 放到Src目录下要使用类加载器去读取
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
			// TODO 自动生成的 catch 块
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
