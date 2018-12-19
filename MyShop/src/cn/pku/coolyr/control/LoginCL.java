//这个servlet用于验证用户名密码是否正确
package cn.pku.coolyr.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pku.coolyr.model.domain.GoodsBean;
import cn.pku.coolyr.model.domain.UsersBean;
import cn.pku.coolyr.model.service.MyCartBO;
import cn.pku.coolyr.model.service.UsersBeanBO;

public class LoginCL extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html");
		// 得到用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 验证用户
		UsersBeanBO ubo = new UsersBeanBO();
		if (ubo.checkUser(username, password))
		{
			// 用户合法
			// 1.把用户成功登陆的信息放入session
			UsersBean ub = ubo.getUsersBean(username);
			request.getSession().setAttribute("userInfo", ub);
			// 2.把购物车取出，准备在下一页面显示??
			MyCartBO mcb = (MyCartBO) request.getSession().getAttribute("mycarbo");
			List<GoodsBean> list = mcb.showMyCar();
			// 3.将购物车信息放入request中
			request.setAttribute("list", list);

			request.getRequestDispatcher("shopping3.jsp").forward(request, response);
		}
		else
		{
			// 用户不合法
			request.getRequestDispatcher("shopping2.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);
	}

}
