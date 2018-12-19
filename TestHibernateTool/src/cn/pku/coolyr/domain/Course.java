package cn.pku.coolyr.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable
{

	// Fields

	private Integer cid;
	private String cname;
	private Integer ccredit;
	private Set studcourses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Course()
	{
	}

	/** minimal constructor */
	public Course(Integer cid, String cname)
	{
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Course(Integer cid, String cname, Integer ccredit, Set studcourses)
	{
		this.cid = cid;
		this.cname = cname;
		this.ccredit = ccredit;
		this.studcourses = studcourses;
	}

	// Property accessors

	public Integer getCid()
	{
		return this.cid;
	}

	public void setCid(Integer cid)
	{
		this.cid = cid;
	}

	public String getCname()
	{
		return this.cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public Integer getCcredit()
	{
		return this.ccredit;
	}

	public void setCcredit(Integer ccredit)
	{
		this.ccredit = ccredit;
	}

	public Set getStudcourses()
	{
		return this.studcourses;
	}

	public void setStudcourses(Set studcourses)
	{
		this.studcourses = studcourses;
	}

}