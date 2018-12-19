//用于处理与购物相关的业务逻辑

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

	// 定义一个hashmap集合，用于存储书的id和数量
	private HashMap<String, String> hm = new HashMap<String, String>();
	// 定义购物车的总价
	private float allPrice = 0.0f;

	// 1.添加货物
	public void addGoods(String goodsId, String goodsNum)
	{

		hm.put(goodsId, goodsNum);
	}

	// 2.删除货物
	public void deleteGoods(String goodsId)
	{
		hm.remove(goodsId);
	}

	// 3.清空货物
	public void clearGoods()
	{
		hm.clear();
	}

	// 4.修改货物的数量
	public void updateGoods(String goodsId, String newNum)
	{
		hm.put(goodsId, newNum);
	}

	// 5.显示货物(有点难)
	public List<GoodsBean> showMyCar()
	{
		List<GoodsBean> list = new ArrayList<GoodsBean>();
		try
		{

			// 使用迭代器完成将货物id从hashmap中取出
			Iterator<String> it = hm.keySet().iterator();
			String sub = "(";
			while (it.hasNext())
			{
				// 取出goodsId
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
			//从数据库搜索出自己购物车里的详细物品信息
			//为什么不存储到Session里？？
			ct = new ConnDB().getConn();
			String sql = "select * from goods_shop where goodsId in";
			sql += sub;
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			// 把总价清空
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
				// 得到购买商品的总价
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
	 * 根据id返回货物的数量
	 * 
	 * @param goodsId
	 *            货物id
	 * @return
	 */
	public String getGoodsNum(String goodsId)
	{
		return hm.get(goodsId);
	}

	/**
	 * 购物车的总价
	 */
	public float getAllPrice()
	{
		return this.allPrice;
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
