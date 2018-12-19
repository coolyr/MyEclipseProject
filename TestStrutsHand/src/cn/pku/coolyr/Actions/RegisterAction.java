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
	// 使用分派Action往往需要自己定义相应的函数.
	// action="/TestStrutsHand/register.do?flag=register"
	// 通过请求UTL中的自定义参数（flag=函数名）的方式设置相应的函数

	// 该函数相应登录验证请求
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
