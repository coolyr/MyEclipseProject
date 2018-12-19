package cn.pku.coolyr.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import cn.pku.coolyr.Forms.LoginForm;

public class RegisterAction extends DispatchAction
{
	// ʹ�÷���Action������Ҫ�Լ�������Ӧ�ĺ���.
	// action="/TestStrutsHand/register.do?flag=register"
	// ͨ������UTL�е��Զ��������flag=���������ķ�ʽ������Ӧ�ĺ���

	// �ú�����Ӧ��¼��֤����
	public ActionForward register(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		System.out.println("*****register*****");
		DynaActionForm registerForm = (DynaActionForm) form;// TODO Auto-generated method
	
		String name = registerForm.getString("name");
		String passwd = registerForm.get("passwd").toString();
		String email = registerForm.getString("email");
		
		System.out.println("name=" + name + " passwd=" + passwd + " email=" + email );
		return mapping.findForward("registerOk");
	}

}
