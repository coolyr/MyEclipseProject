package cn.pku.coolyr.checkCode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCheckCode extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		// 7.��ֹ���������ͼƬ
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// 6.֪ͨ�ͻ�����ͼƬ�ķ�ʽ�򿪷��͹�ȥ������
		response.setHeader("Content-Type", "image/jpeg");

		// 1.���ڴ��д���һ��ͼƬ
		BufferedImage image = new BufferedImage(50, 25, BufferedImage.TYPE_INT_BGR);

		// 2.��ͼƬ��д����
		Graphics g = image.getGraphics();

		// ���ñ���
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 50, 25);

		// 3.����д������ݵ���ɫ������
		g.setColor(Color.RED);
		g.setFont(new Font(null, Font.BOLD, 20));

		// 4.��ͼƬ��д����
		String number = makeNumber();
		//��仰���ǰ�������ɵ����֣����浽Session������
		request.getSession().setAttribute("checkCode", number);
		g.drawString(number, 2, 20);
		
		//5.��д�����ֵ�ͼƬ����������
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	// �ú����������4λ����
	private String makeNumber()
	{
		Random random = new Random();
		String num = random.nextInt(9999) + "";
		StringBuffer stringBuffer = new StringBuffer();
		//�������4λ���油��0
		for (int i = 0; i < 4 - num.length(); i++)
		{
			stringBuffer.append("0");
		}
		num = stringBuffer.toString() + num;
		return num;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.doGet(request, response);
	}

}
