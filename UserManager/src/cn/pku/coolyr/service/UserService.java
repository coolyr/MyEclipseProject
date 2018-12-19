package cn.pku.coolyr.service;

import java.sql.ResultSet;
import java.util.List;

import cn.pku.coolyr.bean.User;
import cn.pku.coolyr.db.SqlHelper;
import cn.pku.coolyr.mapping.UserMapping;

/**
 * ��User����Ĳ���,�������ӡ�ɾ�����޸ġ����ҡ���ҳ����
 */
public class UserService
{
	// �ڷ�ҳ��ʾ����Ҫ�õ�
	private int pageCount = 0;// ���м�ҳ

	// �������ݿ����
	SqlHelper sqlHelper = new SqlHelper();

	/**
	 * �޸��û���Ϣ
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
			// �޸ĳɹ�
			b = true;
		}

		return b;
	}

	/**
	 * ɾ���û�
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
			// ɾ���ɹ�
			b = true;
		}
		return b;
	}

	/**
	 * �����û���Ϣ
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
			// �޸ĳɹ�
			b = true;
		}

		return b;
	}

	// ����pageCount �ڷ�ҳ����Ҫ�õ���PageCount
	public int getPageCount()
	{
		return this.pageCount;
	}

	/**
	 * ��ҳ��ʾ
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return List
	 */
	public List<?> getResultByPage(int pageNow, int pageSize)
	{
		List<?> userListByPage = null;

		int rowCount = 0;// ���м�����¼

		String sql = "select count(*) from UM_users";

		rowCount = sqlHelper.getRowCount(sql);

		// ����pageCount --BaD Code Ч�ʵͣ����԰�pageCount������װ��һ������
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
	 * ��֤�û� ��Ҫ�õ����������
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
	 * ��ȷ���Һ�ģ������
	 * 
	 * @param type
	 *            �������� 1��ʾģ������,2��ʾ��ȷ����
	 * @param name
	 *            ���Ҷ��������
	 * @param pageNow
	 *            ��ǰ�ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾҳ��
	 * @return
	 */
	public List<?> getResultByPage(String type, String name, int pageNow, int pageSize)
	{
		List<?> userListByPage = null;

		int type_int = 0;// ��ʼ��type_int=0

		if (type != null)
		{
			// ���type��Ϊ��,���û�����˾�ȷ��ģ������,���ø�String����ת��Ϊint����
			// ���typeΪ������ type_int Ϊ0,�������ȥ����(����һ��������)
			type_int = Integer.parseInt(type);// 1����ģ������,2����ȷ����
		}

		int rowCount = 0;// ���м�����¼

		// ***************************************************************

		// 2��ʾ�Ǿ�ȷ����,���ؾ�ȷ���Һ������
		if (type_int == 2)
		{
			String sql = "select count(*) from UM_users where username = ?";
			System.out.println(sql);

			rowCount = sqlHelper.getRowCount(sql, name);
		}
		// 1��ʾ��ģ������,����ģ�����Һ������
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

		// ����pageCount
		if (rowCount % pageSize == 0)
		{
			pageCount = rowCount / pageSize;
		}
		else
		{
			pageCount = rowCount / pageSize + 1;
		}

		String sql2 = "";

		// 2��ʾ�Ǿ�ȷ����,���ؾ�ȷ���Һ������
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
		// 1��ʾ��ģ������,����ģ�����Һ������
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
		// 0��ʾ û�г���type����,���û���һ�ε�½�ò�ѯ����,����һ��������
		else if (type_int == 0)
		{
			return null;
		}
		return null;
	}

}
