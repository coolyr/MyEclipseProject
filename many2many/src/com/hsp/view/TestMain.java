package com.hsp.view;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;




import com.hsp.domain.Course;
import com.hsp.domain.StuCourse;
import com.hsp.domain.Student;
import com.hsp.util.HibernateUtil;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//添加一组Person/idcard
		
		Session s=null;
		Transaction tx=null;
		
		try {
			//我们使用基础模板来讲解.
			s=HibernateUtil.getCurrentSession();
			tx=s.beginTransaction();
		
			//添加一个学生，一门课程，选课
			Student stu1=new Student();
			stu1.setName("小明");
			
			Course course=new Course();
			course.setName("java");
			
			StuCourse sc=new StuCourse();
			
			sc.setCourse(course);
			sc.setStudent(stu1);
			
			//顺序保存.
			s.save(stu1);
			s.save(course);
			s.save(sc);
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
		}finally{
			
			if(s!=null && s.isOpen()){
				s.close();
			}
		}
		
		
	
		
		
	}

	

}
