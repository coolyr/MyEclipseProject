package cn.pku.coolyr.mapping;

import java.sql.ResultSet;

public interface EntityMapping
{
	/**
	 * ӳ�䣬����һ������ 
	 * 
	 * �÷���ͨ������Ľ��������ResultSet,�����ݿ��е�ÿһ�� ��ӳ��Ϊһ������
	 */
	public Object mapping(ResultSet rs);

}
