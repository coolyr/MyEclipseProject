package cn.pku.coolyr.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pku.coolyr.model.domain.UsersBean;

public class ShoppingCL extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html");
		// 这个servlet用于检验用户是否登录过，如果没有就进入登录页面
		// 如果登录过就直接进入显示用户信息和购物车情况的页面

		// 看看session中是否有用户登录的信息
		UsersBean ub = (UsersBean) request.getSession().getAttribute("userInfo");
		if (ub == null)
		{
			// 说明用户没有登录过
			// 就跳转到shopping2.jsp去登录
			request.getRequestDispatcher("shopping2.jsp").forward(request, response);
		}
		else
		{
			// 登录过
			// 就跳转到shopping3.jsp
			request.getRequestDispatcher("shopping3.jsp").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
