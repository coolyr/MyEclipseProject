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
		// �õ�showMyCar.jsp��������type���ж��û�Ҫ����ʲô����
		String type = request.getParameter("type");
		String goodsId = request.getParameter("goodsId");
		// ���������ﳵ����
		// 1.����ͼ��session��ȡ��һ�����ﳵ
		MyCartBO mbo = (MyCartBO) request.getSession().getAttribute("mycarbo");
		if (mbo == null)
		{
			// ˵����һ��ʹ�ù��ﳵ
			// �Ǿ�newһ�����ﳵ
			mbo = new MyCartBO();
			// �����ﳵ����session��
			request.getSession().setAttribute("mycarbo", mbo);
		}

		// �ж�Ҫ���еĲ�������
		if (type.equals("addGoods"))
		{
			// �õ�showDeatilҳ�洫������goodsId

			// ����MyCarBO��ɹ���(���԰�MyCarBO���ɹ��ﳵ),����д���ᵼ��ÿ��newһ�����ﳵ�������������ﳵ
			// MyCartBO mbo = new MyCartBO();
			mbo.addGoods(goodsId, "1");

		}
		else if (type.equals("delete"))
		{

			// �ӹ��ﳵ��ɾ����Ʒ
			mbo.deleteGoods(goodsId);
		}
		else if (type.equals("show"))
		{
			// ʲôҲ������
		}
		else if (type.equals("deleteAll"))
		{
			mbo.clearGoods();
		}
		else if (type.equals("update"))
		{
			// �޸Ļ��������

			// ������servlet�еõ������id���µ�����

			// ���ջ����id
			// ��Ϊ�ύ��������ѭ���õ��ģ����Եõ�����һ���ַ�������
			String goodsIds[] = request.getParameterValues("goodsId");
			// �����µ�������Ҳ��һ���ַ�������
			String newNum[] = request.getParameterValues("newNum");

			for (int i = 0; i < goodsIds.length; i++)
			{
				mbo.updateGoods(goodsIds[i], newNum[i]);
			}

		}

		// �����ﳵ�е�λ����ȡ����ʾ��showMyCar.jspҳ��
		List<GoodsBean> list = mbo.showMyCar();

		// ��list����request��������
		request.setAttribute("list", list);

		// ��ת��showMyCar.jspҳ��
		request.getRequestDispatcher("showMyCar.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);

	}

}
