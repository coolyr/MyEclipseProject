package cn.pku.coolyr.Util;

import java.util.UUID;

public class MyUUID
{

	// �÷�����ȡ�ļ��������ĺ�׺������ƴ����UURD���γ��µ��ļ�����
	// �ļ����ƣ�100.png
	// ���ļ�����:450259ea-35a1-44a2-8df5-c864367bc07f.png
	// �ļ���С��40878
	public static String getNewFileName(String filename)
	{
		int beginIndex = filename.lastIndexOf(".");
		String newFileName = UUID.randomUUID().toString() + filename.substring(beginIndex, filename.length());
		return newFileName;
	}

}
