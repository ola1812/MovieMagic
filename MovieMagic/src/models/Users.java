package models;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Users {
	
	public String fname;
	public String lname;
	public int age;
	public String gender;
	public String job;
	
	public Users(String fname, String lname, int age, String gender, String job) {
		
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.gender = gender;
		this.job = job;
	}
	
	public Users() {
		
	}
	
	public String toString()
	{
		return toStringHelper(this).addValue(fname)
				.addValue(lname)
				.addValue(age)
				.addValue(gender)
				.addValue(job)
				.toString();
	}
	
}

