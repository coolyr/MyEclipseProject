package cn.pku.coolyr.mapping;

import java.sql.ResultSet;
import cn.pku.coolyr.bean.User;

public class UserMapping implements EntityMapping
{
	/**
	 * ͨ������Ľ��������,ͨ��i++,���������еĵ�ǰ��¼�Ķ���,������
	 * 
	 * �÷���,ִ��һ�η��ؽ�����е�һ������,��rsһ���Ƿ���һ��ѭ���е�
	 * 
	 * ��һ���������������ж���
	 */
	public User mapping(ResultSet rs)
	{
		int i = 1;

		User user = null;

		try
		{
			//�����i++
			user = new User(rs.getInt(i++), rs.getString(i++), rs.getString(i++),
					rs.getString(i++), rs.getInt(i++)

			);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}

}
