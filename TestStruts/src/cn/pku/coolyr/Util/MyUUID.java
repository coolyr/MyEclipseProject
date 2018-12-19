package cn.pku.coolyr.Util;

import java.util.UUID;

public class MyUUID
{

	// 该方法截取文件名的最后的后缀名，在拼接上UURD号形成新的文件名。
	// 文件名称：100.png
	// 新文件名称:450259ea-35a1-44a2-8df5-c864367bc07f.png
	// 文件大小：40878
	public static String getNewFileName(String filename)
	{
		int beginIndex = filename.lastIndexOf(".");
		String newFileName = UUID.randomUUID().toString() + filename.substring(beginIndex, filename.length());
		return newFileName;
	}

}
