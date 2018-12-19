package cn.pku.coolyr.WebCount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitServlet extends HttpServlet
{

	@Override
	public void destroy()
	{
		super.destroy();
		String path = this.getServletContext().getRealPath("/record.txt");
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try
		{
			fileWriter = new FileWriter(path);
			bufferedWriter = new BufferedWriter(fileWriter);
			String nums = (String) this.getServletContext().getAttribute("nums");
			System.out.println("destroy : nums=" + nums);
			bufferedWriter.write(nums);
		}
		catch (Exception ex)
		{
			Logger.getLogger(InitServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally
		{
			try
			{
				if (bufferedWriter != null)
				{
					bufferedWriter.close();
				}
				if (fileWriter != null)
				{
					fileWriter.close();
				}
			}
			catch (IOException ex)
			{
				Logger.getLogger(InitServlet.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	public void init() throws ServletException
	{

		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			String path = this.getServletContext().getRealPath("/record.txt");
			// 打开文件
			fr = new FileReader(path);
			// 转为BufferedReader 再读一行
			br = new BufferedReader(fr);
			String nums = br.readLine();

			System.out.println("init : nums=" + nums);
			// 把nums放入ServletContext
			this.getServletContext().setAttribute("nums", nums);
		}
		catch (Exception ex)
		{
			Logger.getLogger(InitServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally
		{
			try
			{
				if (br != null)
				{
					br.close();
				}
				if (fr != null)
				{
					fr.close();
				}
			}
			catch (IOException ex)
			{
				Logger.getLogger(InitServlet.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try
		{
		}
		finally
		{
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest(request, response);
	}

}
