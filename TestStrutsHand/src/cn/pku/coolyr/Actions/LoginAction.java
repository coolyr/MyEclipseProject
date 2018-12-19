package cn.pku.coolyr.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.pku.coolyr.Forms.LoginForm;

//����һ��Action(��ʾС�ӳ�����Ҫ�̳�Action����)
public class LoginAction extends Action
{
	// ������Ҫ���±�дһ������:execute()�ᱻ�Զ�����
	// �е�����Servlet�е�service����
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// ��Form��ת�����Զ����LoginForm����
		LoginForm loginForm = (LoginForm) form;

		// ����֤
		if ("123".equals(loginForm.getPassword()))
		{
			String username = (String) request.getParameter("username");
			System.out.println("username = " + username);
			
			request.setAttribute("attribute", "Hades");
			//�÷�����ת���������ض������Կ���ֱ��ʹ��Request����
			return mapping.findForward("ok");
		}
		else
		{
			return mapping.findForward("err");
		}

	}
}
