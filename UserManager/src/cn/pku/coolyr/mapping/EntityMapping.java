package cn.pku.coolyr.mapping;

import java.sql.ResultSet;

public interface EntityMapping
{
	/**
	 * 映射，返回一个对象 
	 * 
	 * 该方法通过传入的结果集对象ResultSet,让数据库中的每一行 都映射为一个对象
	 */
	public Object mapping(ResultSet rs);

}
