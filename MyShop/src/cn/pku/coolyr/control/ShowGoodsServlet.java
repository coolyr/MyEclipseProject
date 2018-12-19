package cn.pku.coolyr.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pku.coolyr.model.domain.GoodsBean;
import cn.pku.coolyr.model.service.GoodsBeanBO;

public class ShowGoodsServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html");
		String type = request.getParameter("type");
		if (type.equals("showDetail"))
		{
			// �õ�Ҫ��ʾ�Ļ���id
			String goodsId = request.getParameter("id");
			// ����goodsBean
			GoodsBeanBO ggb = new GoodsBeanBO();
			GoodsBean gb = ggb.getResultsById(goodsId);

			// ��GoodsBean����request
			request.setAttribute("goodsInfo", gb);
			// ��ת��showDetail.jspҳ��
			request.getRequestDispatcher("showDetail.jsp").forward(request, response);
		}
		else if (type.equals("fenye"))
		{

			String pageNow = request.getParameter("pageNow");

			request.setAttribute("abc", pageNow);

			request.getRequestDispatcher("index.jsp").forward(request, response);

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

}
