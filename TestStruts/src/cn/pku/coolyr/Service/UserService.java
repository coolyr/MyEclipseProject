package cn.pku.coolyr.Service;

import java.util.ArrayList;

import cn.pku.coolyr.Domain.User;
import cn.pku.coolyr.Util.MysqlHelper;

public class UserService
{
	public boolean addUser(User user)
	{
		boolean isSuccess = true;
		String sql = "insert into users_photo values(?,?,?,?)";
		String[] parameters =
		{ user.getUsername(), user.getPassword(), user.getNewphoto(), user.getOldphoto() };

		try
		{
			MysqlHelper.executeUpdate(sql, parameters);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			isSuccess = false;

		}
		return isSuccess;
	}

	public User getUser(String username)
	{
		String sql = "select * from users_photo where username = ?";
		String[] parameters =
		{ username };
		User user = new User();

		try
		{
			ArrayList<Object[]> userList = MysqlHelper.executeQueryForALOJ(sql, parameters);
			Object[] objects = (Object[]) userList.get(0);

			user.setUsername(objects[0].toString());
			user.setPassword(objects[1].toString());
			user.setNewphoto(objects[2].toString());
			user.setOldphoto(objects[3].toString());
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

		return user;
	}

	public ArrayList<User> getUserList()
	{

		String sql = "select * from users_photo";
		ArrayList<Object[]> al = MysqlHelper.executeQueryForALOJ(sql, null);
		ArrayList<User> userList = new ArrayList<User>();
		// 进行二次封装
		for (int i = 0; i < al.size(); i++)
		{
			User user = new User();
			Object[] objects = (Object[]) al.get(i);
			user.setUsername(objects[0].toString());
			user.setPassword(objects[1].toString());
			user.setNewphoto(objects[2].toString());
			user.setOldphoto(objects[3].toString());
			userList.add(user);
		}
		return userList;
	}
}
