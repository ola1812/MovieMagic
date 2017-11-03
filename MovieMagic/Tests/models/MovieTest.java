package models;

import static models.Fixtures.movies;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class MovieTest {

	Movies movie = new Movies ("Twilight","2001","Twilight.ie");
	
	
	@Test
	public void testCreate()
	{
		assertEquals ("Twilight", movie.title);
		assertEquals ("2001", movie.year);
		assertEquals ("Twilight.ie", movie.url);
		
	}
	
	@Test 
	public void testIds()
	{
		Set<Long> ids = new HashSet<>();
		for (Movies movie : movies)
		{
			ids.add(movie.id);
		}
		
		assertEquals (movies.length, ids.size());
	}

	
	@Test
	public void testToSting()
	{
		assertEquals ("Movies{"+ movie.id + ", Twilight, 2001, Twilight.ie}",movie.toString());
	
}
}
