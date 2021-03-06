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
	// 使用分派Action往往需要自己定义相应的函数.
	// action="/TestStrutsHand/loginAndout.do?flag=login"
	// 通过请求UTL中的自定义参数（flag=函数名）的方式设置相应的函数

	// 该函数相应登录验证请求
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

	// 该函数相应安全退出请求
	// <a href="/TestStrutsTag/loginAndout.do?flag=logout">安全退出</a>
	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("*****logout*****");
		// 安全退出
		request.getSession().invalidate();
		return mapping.findForward("gotologin");
	}
}
