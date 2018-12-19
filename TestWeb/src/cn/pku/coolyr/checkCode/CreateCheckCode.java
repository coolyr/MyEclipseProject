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

		// 7.禁止浏览器缓存图片
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// 6.通知客户端以图片的方式打开发送过去的数据
		response.setHeader("Content-Type", "image/jpeg");

		// 1.在内存中创建一张图片
		BufferedImage image = new BufferedImage(50, 25, BufferedImage.TYPE_INT_BGR);

		// 2.向图片上写数据
		Graphics g = image.getGraphics();

		// 设置背景
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 50, 25);

		// 3.设置写入的数据的颜色和字体
		g.setColor(Color.RED);
		g.setFont(new Font(null, Font.BOLD, 20));

		// 4.向图片上写数据
		String number = makeNumber();
		//这句话就是把随机生成的数字，保存到Session对象中
		request.getSession().setAttribute("checkCode", number);
		g.drawString(number, 2, 20);
		
		//5.把写好数字的图片输出到浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	// 该函数随机生成4位数字
	private String makeNumber()
	{
		Random random = new Random();
		String num = random.nextInt(9999) + "";
		StringBuffer stringBuffer = new StringBuffer();
		//如果不够4位后面补充0
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
