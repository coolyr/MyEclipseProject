package cn.pku.coolyr.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cn.pku.coolyr.Forms.LoginForm;


public class LoginAndoutAction extends DispatchAction
{
	// ʹ�÷���Action������Ҫ�Լ�������Ӧ�ĺ���.
	// action="/TestStrutsHand/loginAndout.do?flag=login"
	// ͨ������UTL�е��Զ��������flag=���������ķ�ʽ������Ӧ�ĺ���

	// �ú�����Ӧ��¼��֤����
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		System.out.println("*****login*****");
		LoginForm loginForm = (LoginForm) form;// TODO Auto-generated method
		if (loginForm.getPassword().equals("123"))
		{
			return mapping.findForward("loginok");
		}
		else
		{
			return mapping.findForward("gotologin");
		}

	}

	// �ú�����Ӧ��ȫ�˳�����
	// <a href="/TestStrutsTag/loginAndout.do?flag=logout">��ȫ�˳�</a>
	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("*****logout*****");
		// ��ȫ�˳�
		request.getSession().invalidate();
		return mapping.findForward("gotologin");
	}
}