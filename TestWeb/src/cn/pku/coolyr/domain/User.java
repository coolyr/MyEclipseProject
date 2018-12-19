package cn.pku.coolyr.domain;

public class User
{
	private int id;
	private String name;
	private float score;

	public User()
	{
	}

	public User(int id, String name, float score)
	{
		super();
		this.id = id;
		this.name = name;
		this.score = score;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public float getScore()
	{
		return score;
	}

	public void setScore(float score)
	{
		this.score = score;
	}

}
