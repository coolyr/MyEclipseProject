package cn.pku.coolyr;

public class SendMailToSomeone
{

	public static void main(String[] args)
	{

		SendMailToSomeone sts = new SendMailToSomeone();
		sts.send("title", "<meta http-equiv=content-Type content=text/html;charset=gb2312><div align=center><h1 style=\"color: red\">coolyr�ڸ�ɶ�� ��-.-��</h1></div>", "1531271510@qq.com",
				"wuyanshen2013@sina.com", "wuyanshen2013", "880711", "smtp.sina.com");

		//ʹ��QQ���䷢�ͱ�����
		// sts.send("title---smtp.qq.com",
		// "<meta http-equiv=content-Type content=text/html;charset=gb2312><div align=center><h1 style=\"color: red\">coolyr�ڸ�ɶ�� ��-.-��</h1></div>",
		// "1531271510@qq.com",
		// "382363222@qq.com", "382363222", "wyr+127122", "smtp.qq.com");
	}

	/**
	 * ���͵����ʼ���ָ��������
	 * 
	 * @param title
	 *            �ʼ�����
	 * @param mailbody
	 *            �ʼ����ݡ�һ����ҳ�����ok��
	 * @param sendTo
	 *            ���͸�˭ hanshunping@tsinghua.org.cn
	 * @param from
	 *            ���Ǹ����䷢�� admin@sohu.com
	 * @param username
	 *            ��������ĵ�¼�û���
	 * @param passwd
	 *            �������������
	 * @param sendSmtp
	 *            �����ʼ���smtp��������ַ�� ��1���Ѻ��� smtp.sohu.com ��2��163�� [smtp.163.com]
	 *            ��3�����ˣ�[smtp.sina.com] ��4����Ѷ��[smtp.qq.com]
	 */
	public void send(String title, String mailbody, String sendTo, String from, String username, String passwd, String sendSmtp)
	{

		// ָ�����Ǹ�smtpת��(�Ѻ�)
		MailUtil mail = new MailUtil(sendSmtp);

		// У�����
		mail.setNeedAuth(true);

		// �ʼ�����
		if (mail.setSubject(title) == false)
			return;
		// ��Ҫ���͵� ���ݷ����ʼ���
		if (mail.setBody(mailbody) == false)
			return;

		// ���͵�����
		if (mail.setTo(sendTo) == false)
			return;

		// ˭���͵�
		if (mail.setFrom(from) == false)
			return;

		// ��Ӹ���
		// if(mail.addFileAffix("c:\\1.rar") == false) return;

		// ����sohu��վ�ϵ��ʼ��û����� ���� �����ʼ���
		mail.setNamePass(username, passwd);

		// ����
		if (mail.sendout() == false)
			return;
	}
}
