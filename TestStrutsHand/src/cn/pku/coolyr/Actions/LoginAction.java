package cn.pku.coolyr.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.pku.coolyr.Forms.LoginForm;

//这是一个Action(表示小队长，需要继承Action对象)
public class LoginAction extends Action
{
	// 我们需要重新编写一个方法:execute()会被自动调用
	// 有点类似Servlet中的service方法
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 从Form表单转换成自定义的LoginForm对象
		LoginForm loginForm = (LoginForm) form;

		// 简单验证
		if ("123".equals(loginForm.getPassword()))
		{
			String username = (String) request.getParameter("username");
			System.out.println("username = " + username);
			
			request.setAttribute("attribute", "Hades");
			//该方法是转发，不是重定向。所以可以直接使用Request对象
			return mapping.findForward("ok");
		}
		else
		{
			return mapping.findForward("err");
		}

	}
}
