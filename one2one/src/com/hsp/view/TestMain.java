package com.hsp.view;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hsp.domain.IdCard;
import com.hsp.domain.Person;

import com.hsp.util.HibernateUtil;

public class TestMain
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		// 添加一组Person/idcard

		Session s = null;
		Transaction tx = null;

		try
		{
			// 我们使用基础模板来讲解.
			s = HibernateUtil.getCurrentSession();
			tx = s.beginTransaction();

			Person p1 = new Person();
			p1.setId(1234);
			p1.setName("顺平");
			IdCard idCard = new IdCard();
			idCard.setId(1900);
			idCard.setValidateDte(new Date());

			idCard.setPerson(p1);// 表示idCard对象是属于p1这个对象.
			s.save(p1);// 先保存人
			s.save(idCard);

			tx.commit();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
		}
		finally
		{

			if (s != null && s.isOpen())
			{
				s.close();
			}
		}

	}

}
