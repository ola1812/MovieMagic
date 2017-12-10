package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.HashMap;
import java.util.Map;

public class Movies implements Comparable<Movies>{
	
	static Long counter=0L;
	
	public Long id;
	
	public String title;
	public String year;
	public String url;
	public Map<Long, Ratings> movieThing = new HashMap<>();
	
	
	
	public Movies(String title, String year, String url) {
		
		this.id = counter++;
		
		this.title = title;
		this.year = year;
		this.url = url;
	}
	
	
	@Override
	public String toString()
	{
		return toStringHelper(this)
				.addValue(id)
				.addValue(title)
				.addValue(year)
				.addValue(url)
				.toString();
	}


	@Override
	public int compareTo(Movies movie) {
		// TODO Auto-generated method stub
		return this.title.compareTo(movie.title);
	}
	
}