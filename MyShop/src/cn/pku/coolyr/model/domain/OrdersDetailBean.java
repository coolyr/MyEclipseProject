//这是一个订单明细bean和ordersDetail表对应
package cn.pku.coolyr.model.domain;

public class OrdersDetailBean
{
	private long orderIid;
	private long goodsId;
	private int nums;

	public long getOrderIid()
	{
		return orderIid;
	}

	public void setOrderIid(long orderIid)
	{
		this.orderIid = orderIid;
	}

	public long getGoodsId()
	{
		return goodsId;
	}

	public void setGoodsId(long goodsId)
	{
		this.goodsId = goodsId;
	}

	public int getNums()
	{
		return nums;
	}

	public void setNums(int nums)
	{
		this.nums = nums;
	}

}
