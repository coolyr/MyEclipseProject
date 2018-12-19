//处理与goods表相关的业务逻辑

package cn.pku.coolyr.model.service;

import java.sql.*;
import java.util.*;

import cn.pku.coolyr.model.domain.GoodsBean;
import cn.pku.coolyr.model.util.ConnDB;

public class GoodsBeanBO
{

	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private Connection ct = null;

	// 返回共有多少页
	public int getPageCount(int pageSize)
	{

		int pageCount = 0;
		int rowCount = 0;
		try
		{

			ct = new ConnDB().getConn();
			ps = ct.prepareStatement("select count(*) from goods_shop");
			rs = ps.executeQuery();
			if (rs.next())
			{

				rowCount = rs.getInt(1);
			}
			if (rowCount % pageSize == 0)
			{
				pageCount = rowCount / pageSize;
			}
			else
			{
				pageCount = rowCount / pageSize + 1;
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

		return pageCount;

	}

	// 分页显示商品信息
	/**
	 * @author Administrator
	 * @parameter pageNow : 显示第几页
	 * @parameter pageSize
	 * @return list
	 */
	public List<GoodsBean> getGoodsList(int pageSize, int pageNow)
	{
		List<GoodsBean> list = new ArrayList<GoodsBean>();
		ct = new ConnDB().getConn();
		try
		{
//			ps = ct.prepareStatement("select top " + pageSize + " * from goods where goodsId not in(select top " + (pageNow - 1) * pageSize + " goodsId from goods)");
			//"select * from users limit " + (pageNow - 1) * pageSize + "," + pageSize;
			ps = ct.prepareStatement("select * from goods_shop limit " + (pageNow - 1) * pageSize + "," + pageSize);
			// ps.setInt(1, pageSize);
			// ps.setInt(2, (pageNow-1)*pageSize);
			rs = ps.executeQuery();
			while (rs.next())
			{
				GoodsBean gb = new GoodsBean();
				gb.setGoodsId(rs.getInt(1));
				gb.setGoodsName(rs.getString(2));
				gb.setGoodsIntro(rs.getString(3));
				gb.setGoodsPrice(rs.getFloat(4));
				gb.setGoodsNum(rs.getInt(5));
				gb.setPublisher(rs.getString(6));
				gb.setPhoto(rs.getString(7));
				gb.setType(rs.getString(8));
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

	// 根据商品id获得商品的具体信息
	public GoodsBean getResultsById(String id)
	{
		GoodsBean gb = new GoodsBean();
		try
		{
			ct = new ConnDB().getConn();
			ps = ct.prepareStatement("select * from goods_shop where goodsId = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next())
			{
				// 放入到gb
				gb.setGoodsId(rs.getInt(1));
				gb.setGoodsName(rs.getString(2));
				gb.setGoodsIntro(rs.getString(3));
				gb.setGoodsPrice(rs.getFloat(4));
				gb.setGoodsNum(rs.getInt(5));
				gb.setPublisher(rs.getString(6));
				gb.setPhoto(rs.getString(7));
				gb.setType(rs.getString(8));
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
		return gb;
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
