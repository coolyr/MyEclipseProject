package cn.pku.coolyr.MD5;

import java.security.MessageDigest;

public class MD5_Test
{

	/**
	 * 对输入的String进行MD5加密
	 * 
	 * @param 要加密的字符串
	 * @return 加密后的字符串
	 */
	public final static String MD5(String s)
	{
		char hexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try
		{
			byte[] strTemp = s.getBytes();
			//MessageDigest用于为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
			//简单点说就是用于生成散列码。信息摘要是安全的单向哈希函数，它接收任意大小的数据，输出固定长度的哈希值。
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");//返回实现指定摘要算法的 MessageDigest 对象。
			md5Digest.update(strTemp);//使用指定的 byte 数组更新摘要。
			byte[] md5 = md5Digest.digest();//通过执行诸如填充之类的最终操作完成哈希计算。在调用此方法之后，摘要被重置。
			int length = md5.length;
			char outChars[] = new char[length * 2];
			int index = 0;
			//for循环是为了把得到的MD5值每4位转换成一个16进制数进行输出
			for (int i = 0; i < length; i++)
			{
				byte byteMd5 = md5[i];
				// '>>>' : 无符号右移,高位补0
				outChars[index++] = hexDigits[byteMd5 >>> 4 & 0xf];//取高四位 --> 转换成相应的16进制数
				outChars[index++] = hexDigits[byteMd5 & 0xf];//取低4位--> 转换成相应的16进制数
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
		// TODO 自动生成的方法存根
		String md5 = MD5_Test.MD5("coolyr");
		System.out.println(md5);
		//单向--不可逆
		System.out.println(MD5_Test.MD5(md5));

	}

}
