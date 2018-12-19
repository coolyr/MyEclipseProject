/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package cn.pku.coolyr.struts.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import cn.pku.coolyr.Domain.User;
import cn.pku.coolyr.Service.UserService;
import cn.pku.coolyr.struts.form.UserForm;

public class FileUpAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm userForm = (UserForm) form;
		String username = userForm.getName();// ȡ���û�����
		String password = userForm.getPassword();// ȡ������
		FormFile formFile = userForm.getPhoto();// ȡ��ͼƬ

		// ͨ��FormFile ���ǿ��Ի�ȡ�����ϴ��ļ���ͼƬ���ĸ�����Ϣ
		String fileName = formFile.getFileName();
		String newFileName = "";

		// ��ȡ���ĺ�׺������ƴ����UUID�ţ��γ��µ��ļ�����
		// �÷�����ȡ�ļ��������ĺ�׺������ƴ����UURD���γ��µ��ļ�����
		// �ļ����ƣ�100.png
		// ���ļ�����:450259ea-35a1-44a2-8df5-c864367bc07f.png
		// �ļ���С��40878
		int beginIndex = fileName.lastIndexOf(".");
		String uuid = (java.util.UUID.randomUUID()).toString();
		newFileName = uuid + fileName.substring(beginIndex, fileName.length());
		int fileSize = formFile.getFileSize();

		System.out.println("uuid��" + uuid);
		System.out.println("�ļ����ƣ�" + fileName);
		System.out.println("���ļ�����:" + newFileName);
		System.out.println("�ļ���С��" + fileSize);

		InputStream is = null;
		OutputStream os = null;
		try
		{
			// ȡ��������
			is = formFile.getInputStream();

			// �õ������--�ļ�
			// �õ�file�ļ����ϴ���tomcat��������ľ���·��
			String storeFilePath = this.getServlet().getServletContext().getRealPath("file");
			System.out.println("storeFilePath = " + storeFilePath);// ��ӡ·��
			os = new FileOutputStream(storeFilePath + "\\" + newFileName);// ���浽�������������

			int len = 0;// ÿ�ζ�ȡ���ֽ���
			byte[] bytes = new byte[1024];// ����
			// ѭ������
			while ((len = is.read(bytes)) > 0)
			{
				os.write(bytes, 0, len);
			}

			// ����û��ϴ��ɹ������Ǿ�д�뵽���ݿ�
			UserService userService = new UserService();
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setNewphoto(newFileName);
			user.setOldphoto(fileName);
			if (userService.addUser(user))
			{
				return mapping.findForward("fileok");
				
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				is.close();
				os.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			formFile.destroy();
		}
		return mapping.findForward("fileerr");
	}
}