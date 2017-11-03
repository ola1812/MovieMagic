package models;

public class Fixtures {

	public static Users [] users = {
			new Users ("ola","bartos","18","female","magician"),
			new Users ("Marcel","Laptop","6","laptop","laptop")
			
	};
	
	public static Movies [] movies = {
			new Movies("Twilight", "2001","Twilight.ie"),
			new Movies("IT","2017","IT.ie"),
			new Movies("Daisy","2021","Daisy.ie")
			
	};
	
	public static Ratings[] ratings = {
			
		new Ratings(1L, 2L, 3),
		new Ratings(5L, 8L, 6),
		new Ratings(4L, 9L, 7),	
	    new Ratings(6L, 2L, 9)
	};
	
}
