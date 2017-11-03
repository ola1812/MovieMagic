package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.HashMap;
import java.util.Map;


public class Ratings {
	
	public static Long counter= (long) 01;
	public Long id;
	public Long userId, movieId;
	public int ratings;
	public Map<Long, Movies> rating = new HashMap<>();

	public Ratings(Long userId, Long movieId, int ratings) 
	{
		
		this.id = counter++;
		this.userId = userId;
		this.movieId = movieId;
		this.ratings  = ratings;
	}
	
	@Override
	public String toString()
	{
		return toStringHelper(this)
				.addValue(id)
				.addValue(userId)
				.addValue(movieId)
				.addValue(ratings)
				.toString();
	}
	
}