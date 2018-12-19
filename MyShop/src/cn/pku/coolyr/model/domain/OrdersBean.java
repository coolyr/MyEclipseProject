//这是个订单bean和orders表对应
package cn.pku.coolyr.model.domain;

import java.util.Date;

public class OrdersBean
{
	private long ordersId;
	private long userId;
	private Date orderDate;
	private String payMode;
	private byte isPayed;
	private float totalPrice;

	public long getOrdersId()
	{
		return ordersId;
	}

	public void setOrdersId(long ordersId)
	{
		this.ordersId = ordersId;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public Date getOrderDate()
	{
		return orderDate;
	}

	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
	}

	public String getPayMode()
	{
		return payMode;
	}

	public void setPayMode(String payMode)
	{
		this.payMode = payMode;
	}

	public byte getIsPayed()
	{
		return isPayed;
	}

	public void setIsPayed(byte isPayed)
	{
		this.isPayed = isPayed;
	}

	public float getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice)
	{
		this.totalPrice = totalPrice;
	}

}
