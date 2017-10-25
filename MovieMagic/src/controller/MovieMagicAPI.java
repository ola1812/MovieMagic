package controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.Users;

public class MovieMagicAPI {

	private Map <String, Users> users = new HashMap<String, Users>();
	
	public Collection <Users> getUser()
	{
		return users.values();
	}
	
	public void deleteUsers()
	{
		users.clear();
	}
	
	public Users createUser(String fname, String lname, int age, String gender, String job)
	{
		Users user= new Users (fname, lname, age, gender, job);
		users.put(lname, user);
		return user;
	}
	
	public Users getUser(String lname)
	{
		return users.get(lname);
	}
	
	public void deleteUser(String lname)
	{
		users.remove(lname);
	}
	}

