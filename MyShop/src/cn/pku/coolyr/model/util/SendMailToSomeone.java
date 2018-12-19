package cn.pku.coolyr.model.util;

public class SendMailToSomeone
{

	/**
	 * ���͵����ʼ���ָ��������
	 * 
	 * @param title
	 *            �ʼ�����
	 * @param mailbody
	 *            �ʼ�����
	 * @param sendTo
	 *            ���͸�˭
	 * @param from
	 *            ����Щ����
	 * @param passwd
	 *            ����
	 * @param sendStmp
	 *            �����ʼ���smtp
	 */
	public void send(String title, String mailbody, String sendTo, String from, String passwd, String sendStmp)
	{

		// ָ�����Ǹ�smtpת��(�Ѻ�)
		MySendMail themail = new MySendMail(sendStmp);

		// У�����
		themail.setNeedAuth(true);

		// �ʼ�����
		if (themail.setSubject(title) == false)
			return;
		// ��Ҫ���͵� ���ݷ����ʼ���
		if (themail.setBody(mailbody) == false)
			return;

		// ���͵�����
		if (themail.setTo(sendTo) == false)
			return;

		// ˭���͵�
		if (themail.setFrom(from) == false)
			return;

		// if(themail.addFileAffix("e:\\butterfly.gif") == false) return;

		// if()
		// ����sohu��վ�ϵ��ʼ��û����� ���� �����ʼ���
		themail.setNamePass("1531271510", passwd);

		// ����
		if (themail.sendout() == false)
			return;
	}
}
