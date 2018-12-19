//���Ǹ�model�����ڴ����orders��ordersDetail��ص�ҵ��
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
	 * ���ɶ���
	 * 
	 * @param myCartBO�û����ﳵ
	 * @param userIdS�û�id
	 * @return ������ϸ��ϢBean
	 */
	public OrderInfoBean addOrder(MyCartBO myCartBO, String usersId)
	{

		// ����鵽��¼���ͽ���Ϣ�洢��OrderInfoBean��
		OrderInfoBean oib = new OrderInfoBean();
		boolean flag = true;

		int ordersId = 0;
		try
		{

			ct = new ConnDB().getConn();
			// Ϊ�˱�֤���������ȶ��ģ����Խ���������뼶�����ߣ�serializable��
			ct.setAutoCommit(false);
			ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

			ps = ct.prepareStatement("insert into orders_shop(userId,isPayed,totalPrice) values(?,?,?)");
			ps.setString(1, usersId);
			ps.setByte(2, (byte) 0);
			ps.setFloat(3, myCartBO.getAllPrice());
			int a = ps.executeUpdate();
			if (a == 1)
			{

				// ����ȡ���ո���ӵ�orders����Ǹ�������
				// ȡ�����������ordersId,��Ҫ���Ǹ�������
				ps = ct.prepareStatement("select max(ordersId) from orders_shop");
				rs = ps.executeQuery();
				if (rs.next())
				{
					ordersId = rs.getInt(1);
				}

				// orders����ӳɹ�
				// ׼�����ordersDetail��

				// �ӹ��ﳵȡ������ѡ���Ļ���
				List<GoodsBean> list = myCartBO.showMyCar();
				// ѭ����ӵ�ordersDetail����(Ч�ʵף�ÿ����Ӷ�Ҫִ�����ݿ�)
				// ���ǿ���ʹ�������������ݿ�ķ�������߲������ݿ��Ч��
				// satatment�е�addBatch(sql)�������������������
				Statement sm = ct.createStatement();

				for (int i = 0; i < list.size(); i++)
				{
					GoodsBean gb = (GoodsBean) list.get(i);

					sm.addBatch("insert into orderDetail_shop values('" + ordersId + "','" + gb.getGoodsId() + "','" + myCartBO.getGoodsNum(gb.getGoodsId() + "") + "') ");
				}
				// ����ִ�����
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
			// �����ύ--��������Ҫ�ֶ��ύ
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

	// �ر���Դ����
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
