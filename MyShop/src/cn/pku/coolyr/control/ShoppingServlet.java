package cn.pku.coolyr.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pku.coolyr.model.domain.GoodsBean;
import cn.pku.coolyr.model.service.MyCartBO;

public class ShoppingServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=utf-8");
		// 得到showMyCar.jsp传过来的type来判断用户要进行什么操作
		String type = request.getParameter("type");
		String goodsId = request.getParameter("goodsId");
		// 解决多个购物车问题
		// 1.先试图从session中取出一个购物车
		MyCartBO mbo = (MyCartBO) request.getSession().getAttribute("mycarbo");
		if (mbo == null)
		{
			// 说明第一次使用购物车
			// 那就new一个购物车
			mbo = new MyCartBO();
			// 将购物车放入session中
			request.getSession().setAttribute("mycarbo", mbo);
		}

		// 判断要进行的操作类型
		if (type.equals("addGoods"))
		{
			// 得到showDeatil页面传过来的goodsId

			// 调用MyCarBO完成购买(可以把MyCarBO看成购物车),这种写法会导致每次new一个购物车，会产生多个购物车
			// MyCartBO mbo = new MyCartBO();
			mbo.addGoods(goodsId, "1");

		}
		else if (type.equals("delete"))
		{

			// 从购物车中删除商品
			mbo.deleteGoods(goodsId);
		}
		else if (type.equals("show"))
		{
			// 什么也不用做
		}
		else if (type.equals("deleteAll"))
		{
			mbo.clearGoods();
		}
		else if (type.equals("update"))
		{
			// 修改货物的数量

			// 怎样在servlet中得到货物的id和新的数量

			// 接收货物的id
			// 因为提交过来的是循环得到的，所以得到的是一个字符串数组
			String goodsIds[] = request.getParameterValues("goodsId");
			// 接收新的数量，也是一个字符串数组
			String newNum[] = request.getParameterValues("newNum");

			for (int i = 0; i < goodsIds.length; i++)
			{
				mbo.updateGoods(goodsIds[i], newNum[i]);
			}

		}

		// 将购物车中单位货物取出显示到showMyCar.jsp页面
		List<GoodsBean> list = mbo.showMyCar();

		// 将list存入request作用域中
		request.setAttribute("list", list);

		// 跳转到showMyCar.jsp页面
		request.getRequestDispatcher("showMyCar.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);

	}

}
