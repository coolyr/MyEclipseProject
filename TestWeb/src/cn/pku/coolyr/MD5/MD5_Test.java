package cn.pku.coolyr.MD5;

import java.security.MessageDigest;

public class MD5_Test
{

	/**
	 * �������String����MD5����
	 * 
	 * @param Ҫ���ܵ��ַ���
	 * @return ���ܺ���ַ���
	 */
	public final static String MD5(String s)
	{
		char hexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try
		{
			byte[] strTemp = s.getBytes();
			//MessageDigest����ΪӦ�ó����ṩ��ϢժҪ�㷨�Ĺ��ܣ��� MD5 �� SHA �㷨��
			//�򵥵�˵������������ɢ���롣��ϢժҪ�ǰ�ȫ�ĵ����ϣ�����������������С�����ݣ�����̶����ȵĹ�ϣֵ��
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");//����ʵ��ָ��ժҪ�㷨�� MessageDigest ����
			md5Digest.update(strTemp);//ʹ��ָ���� byte �������ժҪ��
			byte[] md5 = md5Digest.digest();//ͨ��ִ���������֮������ղ�����ɹ�ϣ���㡣�ڵ��ô˷���֮��ժҪ�����á�
			int length = md5.length;
			char outChars[] = new char[length * 2];
			int index = 0;
			//forѭ����Ϊ�˰ѵõ���MD5ֵÿ4λת����һ��16�������������
			for (int i = 0; i < length; i++)
			{
				byte byteMd5 = md5[i];
				// '>>>' : �޷�������,��λ��0
				outChars[index++] = hexDigits[byteMd5 >>> 4 & 0xf];//ȡ����λ --> ת������Ӧ��16������
				outChars[index++] = hexDigits[byteMd5 & 0xf];//ȡ��4λ--> ת������Ӧ��16������
			}
			return new String(outChars);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public static void main(String[] args)
	{
		// TODO �Զ����ɵķ������
		String md5 = MD5_Test.MD5("coolyr");
		System.out.println(md5);
		//����--������
		System.out.println(MD5_Test.MD5(md5));

	}

}
