package cn.pku.coolyr;

public class SendMailToSomeone
{

	public static void main(String[] args)
	{

		SendMailToSomeone sts = new SendMailToSomeone();
		sts.send("title", "<meta http-equiv=content-Type content=text/html;charset=gb2312><div align=center><h1 style=\"color: red\">coolyr在干啥呢 （-.-）</h1></div>", "1531271510@qq.com",
				"wuyanshen2013@sina.com", "wuyanshen2013", "880711", "smtp.sina.com");

		//使用QQ邮箱发送报错！！
		// sts.send("title---smtp.qq.com",
		// "<meta http-equiv=content-Type content=text/html;charset=gb2312><div align=center><h1 style=\"color: red\">coolyr在干啥呢 （-.-）</h1></div>",
		// "1531271510@qq.com",
		// "382363222@qq.com", "382363222", "wyr+127122", "smtp.qq.com");
	}

	/**
	 * 发送电子邮件到指定的信箱
	 * 
	 * @param title
	 *            邮件标题
	 * @param mailbody
	 *            邮件内容【一个网页，表格ok】
	 * @param sendTo
	 *            发送给谁 hanshunping@tsinghua.org.cn
	 * @param from
	 *            从那个邮箱发送 admin@sohu.com
	 * @param username
	 *            发送邮箱的登录用户名
	 * @param passwd
	 *            发送邮箱的密码
	 * @param sendSmtp
	 *            发送邮件的smtp服务器地址： （1）搜狐： smtp.sohu.com （2）163： [smtp.163.com]
	 *            （3）新浪：[smtp.sina.com] （4）腾讯：[smtp.qq.com]
	 */
	public void send(String title, String mailbody, String sendTo, String from, String username, String passwd, String sendSmtp)
	{

		// 指明让那个smtp转发(搜狐)
		MailUtil mail = new MailUtil(sendSmtp);

		// 校验身份
		mail.setNeedAuth(true);

		// 邮件标题
		if (mail.setSubject(title) == false)
			return;
		// 将要发送的 内容放入邮件体
		if (mail.setBody(mailbody) == false)
			return;

		// 发送到哪里
		if (mail.setTo(sendTo) == false)
			return;

		// 谁发送的
		if (mail.setFrom(from) == false)
			return;

		// 添加附件
		// if(mail.addFileAffix("c:\\1.rar") == false) return;

		// 将在sohu网站上的邮件用户名和 密码 放入邮件体
		mail.setNamePass(username, passwd);

		// 发送
		if (mail.sendout() == false)
			return;
	}
}
