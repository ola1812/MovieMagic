package models;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static models.Fixtures.ratings;
import org.junit.Test;

public class RatingTest {

	//public Map<Long, Movies> rating = new HashMap<>();
	
     @Test
     public void testCreate()
     {
    	 assertEquals(1, 1L, ratings[0].userId);
    	 assertEquals(2, 2L, ratings[0].movieId);
    	 assertEquals(3,  ratings[0].ratings);
     }

     @Test
     public void testIds()
     {
    	 assertNotEquals(ratings[0].id, ratings[1].id);
     }
     
     @Test
     public void testToSting()
     {
    	 //assertEquals("Ratings{"+ratings[0].id+",1, 2, 3}",ratings[0].toString());
     }
}
