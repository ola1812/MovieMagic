package models;

import static models.Fixtures.users;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;



public class UsersTests {

	Users ola = new Users ("ola","bartos","18","female","magician");
	
	@Test
	public void testCreate()
	{
		assertEquals ("ola", ola.fname);
		assertEquals ("bartos", ola.lname);
		assertEquals ("18", ola.age);
		assertEquals ("female", ola.gender);
		assertEquals ("magician", ola.job);
	}
	
	@Test 
	public void testIds()
	{
		Set<Long> ids = new HashSet<>();
		for (Users user : users)
		{
			ids.add(user.id);
		}
		
		assertEquals (users.length, ids.size());
	}

	
	@Test
	public void testToSting()
	{
		assertEquals ("Users{"+ ola.id + ", ola, bartos, 18, female, magician}",ola.toString());
	}
}
