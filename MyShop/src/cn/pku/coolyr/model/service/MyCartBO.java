//���ڴ����빺����ص�ҵ���߼�

package cn.pku.coolyr.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import cn.pku.coolyr.model.domain.GoodsBean;
import cn.pku.coolyr.model.util.ConnDB;

public class MyCartBO
{

	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private Connection ct = null;

	// ����һ��hashmap���ϣ����ڴ洢���id������
	private HashMap<String, String> hm = new HashMap<String, String>();
	// ���幺�ﳵ���ܼ�
	private float allPrice = 0.0f;

	// 1.��ӻ���
	public void addGoods(String goodsId, String goodsNum)
	{

		hm.put(goodsId, goodsNum);
	}

	// 2.ɾ������
	public void deleteGoods(String goodsId)
	{
		hm.remove(goodsId);
	}

	// 3.��ջ���
	public void clearGoods()
	{
		hm.clear();
	}

	// 4.�޸Ļ��������
	public void updateGoods(String goodsId, String newNum)
	{
		hm.put(goodsId, newNum);
	}

	// 5.��ʾ����(�е���)
	public List<GoodsBean> showMyCar()
	{
		List<GoodsBean> list = new ArrayList<GoodsBean>();
		try
		{

			// ʹ�õ�������ɽ�����id��hashmap��ȡ��
			Iterator<String> it = hm.keySet().iterator();
			String sub = "(";
			while (it.hasNext())
			{
				// ȡ��goodsId
				String goodsId = (String) it.next();
				if (it.hasNext())
				{
					sub += goodsId + ",";
				}
				else
				{
					sub += goodsId + ")";
				}
			}
			//�����ݿ��������Լ����ﳵ�����ϸ��Ʒ��Ϣ
			//Ϊʲô���洢��Session���
			ct = new ConnDB().getConn();
			String sql = "select * from goods_shop where goodsId in";
			sql += sub;
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			// ���ܼ����
			this.allPrice = 0.0f;
			while (rs.next())
			{
				GoodsBean gb = new GoodsBean();
				int goodsId = rs.getInt(1);
				gb.setGoodsId(goodsId);
				gb.setGoodsName(rs.getString(2));
				gb.setGoodsIntro(rs.getString(3));
				float unitPrice = rs.getFloat(4);
				gb.setGoodsPrice(unitPrice);
				gb.setGoodsNum(rs.getInt(5));
				gb.setPublisher(rs.getString(6));
				gb.setPhoto(rs.getString(7));
				gb.setType(rs.getString(8));
				// �õ�������Ʒ���ܼ�
				this.allPrice += unitPrice * Integer.parseInt(this.getGoodsNum(goodsId + ""));
				list.add(gb);
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
		return list;
	}

	/**
	 * ����id���ػ��������
	 * 
	 * @param goodsId
	 *            ����id
	 * @return
	 */
	public String getGoodsNum(String goodsId)
	{
		return hm.get(goodsId);
	}

	/**
	 * ���ﳵ���ܼ�
	 */
	public float getAllPrice()
	{
		return this.allPrice;
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
