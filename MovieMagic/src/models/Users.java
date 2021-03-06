package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.HashMap;
import java.util.Map;

public class Users implements Comparable <Users>{
	
	static Long counter=0L;
	
	public Long id;
	
	public String fname;
	public String lname;
	public String age;
	public String gender;
	public String job;
    public String role;
	
	public Map<Long, Ratings> UserRating = new HashMap<>();
	
	public Users(String fname, String lname, String age, String gender, String job, String role) {
		
		this.id = counter++;
		
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.gender = gender;
		this.job = job;
		this.role = role;
	}
	
	public Users() {
		
	}
	
	@Override
	public String toString()
	{
		return toStringHelper(this).addValue(id)
				.addValue(fname)
				.addValue(lname)
				.addValue(age)
				.addValue(gender)
				.addValue(job)
				.toString();
	}
	
	 public Users(String fName, String lName, String age, String gender, String job)
	  {
	    this(fName,lName, age, gender, job ,"default");
	  }

	@Override
	public int compareTo(Users user) {
		// TODO Auto-generated method stub
		return this.fname.compareTo(user.fname);
	}

	  
	
	
}

