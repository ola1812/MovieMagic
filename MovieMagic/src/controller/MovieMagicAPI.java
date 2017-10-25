package controller;

import java.util.ArrayList;
import java.util.List;

import models.Users;

public class MovieMagicAPI {

	private List <Users> users = new ArrayList<>();
	
	public List <Users> getUser()
	{
		return users;
	}
	
	public void deleteUsers()
	{
		users.clear();
	}
	
	public Users createUser(String fname, String lname, int age, String gender, String job)
	{
		Users user= new Users (fname, lname, age, gender, job);
		users.add(user);
		return user;
	}
	
	public Users getUser(String lname)
	{
		for(Users user : users)
		{
			if(lname.equals(user.lname))
				return user;
		}
		
		return null;
	}
	
	public void deleteUser(String lname)
	{
		Users foundUser = null;
		for (Users user : users)
		{
			if(lname.equals(user.lname))
				foundUser = user;		
	}
	if (foundUser !=null)
	{
		users.remove(foundUser);
	}
	}}

