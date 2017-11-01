package controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.Users;


public class MovieMagicAPI {

	private Map <Long, Users> userIndex = new HashMap<>();
	private Map <String, Users> lnameIndex = new HashMap<>();
	
	public Collection <Users> getUser()
	{
		return userIndex.values();
	}
	
	public void deleteUsers()
	{
		userIndex.clear();
		lnameIndex.clear();
	}
	
	public Users createUser(String fname, String lname, int age, String gender, String job)
	{
		Users user= new Users (fname, lname, age, gender, job);
		userIndex.put(user.id, user);
		lnameIndex.put(lname,  user);
		return user;
	}
	
	public Users getUsersByLname(String lname)
	{
		return lnameIndex.get(lname);
	}
	
	public Users getUser(Long id)
	{
		return userIndex.get(id);
	}
	
	public void deleteUser(Long id)
	{
		Users user= userIndex.remove(id);
		lnameIndex.remove(user.lname);
	}
	
	
	}

