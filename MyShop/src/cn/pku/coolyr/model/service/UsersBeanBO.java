//处理与users表相关的业务
package cn.pku.coolyr.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.pku.coolyr.model.domain.UsersBean;
import cn.pku.coolyr.model.util.ConnDB;

public class UsersBeanBO
{
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private Connection ct = null;

	// 根据用户名返回用户所有的信息
	public UsersBean getUsersBean(String username)
	{
		UsersBean ub = new UsersBean();
		try
		{
			ct = new ConnDB().getConn();
			String sql = "select * from users_shop where username=?";
			ps = ct.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next())
			{
				ub.setUserid(rs.getInt(1));
				ub.setUsername(rs.getString(2));
				ub.setTruename(rs.getString(3));
				ub.setPassword(rs.getString(4));
				ub.setEmail(rs.getString(5));
				ub.setPhone(rs.getString(6));
				ub.setAddress(rs.getString(7));
				ub.setPostcode(rs.getString(8));
				ub.setGrade(rs.getInt(9));
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.closeResource();
		}
		return ub;
	}

	// 验证用户
	public boolean checkUser(String username, String password)
	{
		boolean flag = false;
		try
		{
			ct = new ConnDB().getConn();
			// top 1表示查到记录立即返回，不继续往下查，返回一条记录，能提高查询速度
			String sql = "select password from users_shop where username='" + username + "'";
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next())
			{
				// 取出数据库的密码
				String dbPassword = rs.getString(1);
				if (dbPassword.equals(password))
				{
					flag = true;
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.closeResource();
		}
		return flag;
	}

	// 关闭资源方法
	public void closeResource()
	{

		try
		{

			if (rs != null)
			{
				rs.close();
				rs = null;
			}
			if (ps != null)
			{
				ps.close();
				ps = null;
			}
			if (ct != null)
			{
				ct.close();
				ct = null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
