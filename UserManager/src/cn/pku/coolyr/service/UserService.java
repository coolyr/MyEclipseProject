package cn.pku.coolyr.service;

import java.sql.ResultSet;
import java.util.List;

import cn.pku.coolyr.bean.User;
import cn.pku.coolyr.db.SqlHelper;
import cn.pku.coolyr.mapping.UserMapping;

/**
 * 对User对象的操作,包括增加、删除、修改、查找、分页查找
 */
public class UserService
{
	// 在分页显示界面要用到
	private int pageCount = 0;// 共有几页

	// 处理数据库对象
	SqlHelper sqlHelper = new SqlHelper();

	/**
	 * 修改用户信息
	 * 
	 * @param id
	 * @param name
	 * @param pwd
	 * @param email
	 * @param grade
	 * @return boolean
	 */
	public boolean updateUser(String id, String name, String pwd, String email, String grade)
	{
		boolean b = false;

		String sql = "update UM_users set username =?, passwd =?,email=?, grade =? where userId= ?";

		int num = sqlHelper.update(sql, name, pwd, email, grade, id);

		if (num == 1)
		{
			// 修改成功
			b = true;
		}

		return b;
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteUser(String id)
	{
		boolean b = false;

		String sql = "delete from UM_users where userId= ?";

		int num = sqlHelper.update(sql, id);

		if (num == 1)
		{
			// 删除成功
			b = true;
		}
		return b;
	}

	/**
	 * 增加用户信息
	 * 
	 * @param name
	 * @param pwd
	 * @param email
	 * @param grade
	 * @return boolean
	 */
	public boolean addUser(String name, String pwd, String email, String grade)
	{
		boolean b = false;

		String sql = "insert into UM_users values( null,?, ?, ?, ? )";

		System.out.println(sql);

		int num = sqlHelper.update(sql, name, pwd, email, grade);

		if (num == 1)
		{
			// 修改成功
			b = true;
		}

		return b;
	}

	// 返回pageCount 在分页界面要用到该PageCount
	public int getPageCount()
	{
		return this.pageCount;
	}

	/**
	 * 分页显示
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return List
	 */
	public List<?> getResultByPage(int pageNow, int pageSize)
	{
		List<?> userListByPage = null;

		int rowCount = 0;// 共有几条记录

		String sql = "select count(*) from UM_users";

		rowCount = sqlHelper.getRowCount(sql);

		// 计算pageCount --BaD Code 效率低：可以把pageCount单独封装成一个函数
		if (rowCount % pageSize == 0)
		{
			pageCount = rowCount / pageSize;
		}
		else
		{
			pageCount = rowCount / pageSize + 1;
		}

		// String sql2 = "select top " + pageSize +
		// "  * from UM_users where userId not in " + "(select top " + pageSize *
		// (pageNow - 1) + " userId from UM_users)";

		String sql2 = "select * from UM_users limit " + (pageNow - 1) * pageSize + "," + pageSize;
		userListByPage = sqlHelper.query(sql2, new UserMapping());

		return userListByPage;
	}

	/**
	 * 验证用户 需要用到结果集对象
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean checkUser(User user)
	{
		ResultSet rs = null;

		boolean b = false;

		try
		{
			String sql = "select passwd from UM_users where username= ?";

			rs = sqlHelper.getRS(sql, user.getUsername());

			if (rs.next())
			{
				String dbPasswd = rs.getString(1);

				if (dbPasswd.equals(user.getPasswd()))
				{
					b = true;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
					rs = null;
				}

			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return b;

	}

	/**
	 * 精确查找和模糊查找
	 * 
	 * @param type
	 *            查找类型 1表示模糊查找,2表示精确查找
	 * @param name
	 *            查找对象的名称
	 * @param pageNow
	 *            当前第几页
	 * @param pageSize
	 *            每页显示页数
	 * @return
	 */
	public List<?> getResultByPage(String type, String name, int pageNow, int pageSize)
	{
		List<?> userListByPage = null;

		int type_int = 0;// 初始化type_int=0

		if (type != null)
		{
			// 如果type不为空,即用户点击了精确或模糊查找,则让该String类型转换为int类型
			// 如果type为空则让 type_int 为0,下面代码去处理(返回一个空数组)
			type_int = Integer.parseInt(type);// 1代表模糊查找,2代表精确查找
		}

		int rowCount = 0;// 共有几条记录

		// ***************************************************************

		// 2表示是精确查找,返回精确查找后的数组
		if (type_int == 2)
		{
			String sql = "select count(*) from UM_users where username = ?";
			System.out.println(sql);

			rowCount = sqlHelper.getRowCount(sql, name);
		}
		// 1表示是模糊查找,返回模糊查找后的数组
		else if (type_int == 1)
		{
			//

			String sql = "select count(*) from UM_users where username like '%" + name + "%'";

			System.out.println(sql);

			rowCount = sqlHelper.getRowCount(sql);
		}
		// ***************************************************************

		System.out.println("rowCount=" + rowCount);
		System.out.println("pageCount=" + pageCount);

		// 计算pageCount
		if (rowCount % pageSize == 0)
		{
			pageCount = rowCount / pageSize;
		}
		else
		{
			pageCount = rowCount / pageSize + 1;
		}

		String sql2 = "";

		// 2表示是精确查找,返回精确查找后的数组
		if (type_int == 2)
		{
			// sql2 = "select top " + pageSize +
			// "  * from UM_users where userId not in " + "(select top " + pageSize
			// * (pageNow - 1) +
			// " userId from UM_users where username=?) and username=?";
			sql2 = "select * from UM_users where username = ? limit " + (pageNow - 1) * pageSize + "," + pageSize;
			System.out.println(sql2);

			return userListByPage = sqlHelper.query(sql2, new UserMapping(), name);
		}
		// 1表示是模糊查找,返回模糊查找后的数组
		else if (type_int == 1)
		{
			// sql2 = "select top " + pageSize +
			// "  * from UM_users where userId not in " + "(select top " + pageSize
			// * (pageNow - 1) + " userId from UM_users where username like '%" +
			// name
			// + "%') and username like '%" + name + "%'";

			sql2 = "select * from UM_users where username like '%" + name + "%' limit " + (pageNow - 1) * pageSize + "," + pageSize;

			System.out.println(sql2);

			userListByPage = sqlHelper.query(sql2, new UserMapping());
			return userListByPage;
		}
		// 0表示 没有出入type类型,即用户第一次登陆该查询界面,返回一个空数组
		else if (type_int == 0)
		{
			return null;
		}
		return null;
	}

}
