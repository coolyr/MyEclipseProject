package cn.pku.coolyr.domain;

/**
 * Studcourse entity. @author MyEclipse Persistence Tools
 */

public class Studcourse implements java.io.Serializable
{

	// Fields

	private Integer stuCourseId;
	private Student student;
	private Course course;
	private Float grade;

	// Constructors

	/** default constructor */
	public Studcourse()
	{
	}

	/** minimal constructor */
	public Studcourse(Integer stuCourseId, Float grade)
	{
		this.stuCourseId = stuCourseId;
		this.grade = grade;
	}

	/** full constructor */
	public Studcourse(Integer stuCourseId, Student student, Course course, Float grade)
	{
		this.stuCourseId = stuCourseId;
		this.student = student;
		this.course = course;
		this.grade = grade;
	}

	// Property accessors

	public Integer getStuCourseId()
	{
		return this.stuCourseId;
	}

	public void setStuCourseId(Integer stuCourseId)
	{
		this.stuCourseId = stuCourseId;
	}

	public Student getStudent()
	{
		return this.student;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}

	public Course getCourse()
	{
		return this.course;
	}

	public void setCourse(Course course)
	{
		this.course = course;
	}

	public Float getGrade()
	{
		return this.grade;
	}

	public void setGrade(Float grade)
	{
		this.grade = grade;
	}

}