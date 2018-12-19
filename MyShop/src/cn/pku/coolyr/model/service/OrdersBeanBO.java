//这是个model，用于处理和orders和ordersDetail相关的业务
package cn.pku.coolyr.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cn.pku.coolyr.model.domain.GoodsBean;
import cn.pku.coolyr.model.domain.OrderInfoBean;
import cn.pku.coolyr.model.util.ConnDB;

public class OrdersBeanBO
{
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private Connection ct = null;

	/**
	 * 生成订单
	 * 
	 * @param myCartBO用户购物车
	 * @param userIdS用户id
	 * @return 订单详细信息Bean
	 */
	public OrderInfoBean addOrder(MyCartBO myCartBO, String usersId)
	{

		// 如果查到记录，就将信息存储到OrderInfoBean中
		OrderInfoBean oib = new OrderInfoBean();
		boolean flag = true;

		int ordersId = 0;
		try
		{

			ct = new ConnDB().getConn();
			// 为了保证订单号是稳定的，所以将其事务隔离级别升高（serializable）
			ct.setAutoCommit(false);
			ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

			ps = ct.prepareStatement("insert into orders_shop(userId,isPayed,totalPrice) values(?,?,?)");
			ps.setString(1, usersId);
			ps.setByte(2, (byte) 0);
			ps.setFloat(3, myCartBO.getAllPrice());
			int a = ps.executeUpdate();
			if (a == 1)
			{

				// 必须取出刚刚添加到orders表的那个订单号
				// 取出最后那条的ordersId,就要的那个订单号
				ps = ct.prepareStatement("select max(ordersId) from orders_shop");
				rs = ps.executeQuery();
				if (rs.next())
				{
					ordersId = rs.getInt(1);
				}

				// orders表添加成功
				// 准备添加ordersDetail表

				// 从购物车取出所有选购的货物
				List<GoodsBean> list = myCartBO.showMyCar();
				// 循环添加到ordersDetail表中(效率底，每次添加都要执行数据库)
				// 我们可以使用批量操作数据库的方法，提高操作数据库的效率
				// satatment中的addBatch(sql)方法可以批量添加数据
				Statement sm = ct.createStatement();

				for (int i = 0; i < list.size(); i++)
				{
					GoodsBean gb = (GoodsBean) list.get(i);

					sm.addBatch("insert into orderDetail_shop values('" + ordersId + "','" + gb.getGoodsId() + "','" + myCartBO.getGoodsNum(gb.getGoodsId() + "") + "') ");
				}
				// 批量执行添加
				sm.executeBatch();
				String sql = "select ordersId ,truename,address,postcode,phone,totalPrice,username,email from users_shop,orders_shop where ordersId=? and users_shop.userid = (select orders_shop.userId from orders_shop where orders_shop.ordersId=? )";
				ps = ct.prepareStatement(sql);
				ps.setInt(1, ordersId);
				ps.setInt(2, ordersId);
				rs = ps.executeQuery();
				if (rs.next())
				{

					oib.setOrdersId(rs.getInt(1));
					oib.setTruename(rs.getString(2));
					oib.setAddress(rs.getString(3));
					oib.setPostcode(rs.getString(4));
					oib.setPhone(rs.getString(5));
					oib.setTotalPrice(rs.getFloat(6));
					oib.setUsername(rs.getString(7));
					oib.setEmail(rs.getString(8));
				}
			}
			// 整体提交--事务隔离后要手动提交
			 ct.commit();

		}
		catch (Exception e)
		{
			try
			{
				// ct.rollback();
			}
			catch (Exception e1)
			{
				System.out.println(e.getMessage());
			}
			flag = false;
			e.printStackTrace();
		}
		finally
		{
			this.closeResource();
		}

		if (flag)
		{
			return oib;
		}
		else
		{
			return null;
		}
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
