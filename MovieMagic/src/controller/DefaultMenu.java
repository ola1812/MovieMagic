package controller;

import java.util.Collection;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Ratings;
import models.Users;

public class DefaultMenu {

  	  private String name;
  	  private Users user;
  	  private MovieMagicAPI moviemagicAPI;

  	  public DefaultMenu(MovieMagicAPI moviemagicAPI, Users user) {
  	    this.moviemagicAPI = moviemagicAPI;
  	    this.setName(user.lname);
  	    this.user = user;
  	  }
  	  
  	  @Command(description = "Get a User by ID")//searching user by id
  	  public void getUser(@Param(name = "id") Long id) {
  	    Users user = moviemagicAPI.getUserById(id);
  	    System.out.println(user);
  	  }
  	  
  	  
  	@Command
	  (description = "Get all users details")
	  public void getUsers() {

	    Collection <Users> users = moviemagicAPI.getUsers();
	    System.out.println(users);

	  }
 
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	 
	    
	  @Command
	  (description = "Get a Users detail")//get user by last name
	  public void getUserByLname(@Param(name = "lname") String lname) {

	    Users user = moviemagicAPI.getUserByLname(lname);
	    System.out.println(user);

	  }
	
	  @Command
	  (description = "Add a Rating") //Adding a movie rating
	  public void addRating(@Param(name = "adding a rating") Long id, @Param(name = "userId") Long userId,
	      @Param(name = "movieId") Long movieId, @Param(name = "ratings") int ratings) {
	    moviemagicAPI.addRatings(userId, movieId, ratings);
	  }
	  
	  @Command 
	  (description = "Get a users rating") //Getting s user's rating
	  public void getUserRating(@Param(name="userId")Long userId)
	  {
		 moviemagicAPI.getUserRating(userId);
	  }
	  
	  @Command 
	  (description = "Get movie rating")//getting a movie rating
	  public void getMovieRating(@Param(name="Movie")Long Movie)
	  {
		  moviemagicAPI.getMovieRating(Movie);
	  }
	  
	  @Command 
	  (description = "Returning one rating")//returning a rating when called
	  public void getRating(@Param(name = "rating")Long rating)
	  {
		  System.out.println(moviemagicAPI.getRating(rating));
	  }
	  
	  @Command
	  (description = "Return all ratings")//returning all ratings
	  public void getAllRatings()
	  {
		  moviemagicAPI.getAllRatings();
	  }
	  
	  @Command
	  (description = "delete one rating")
	  public void deleteRating(@Param(name="Rating id")long id)
	  {
		  moviemagicAPI.deleteRating(id);
	  }		  

	  @Command
	  (description = "Seaching movie by title")
	  public void searchMovieByTitle(@Param(name="title")String title)
	  {
		  moviemagicAPI.getMovieByTitle(title);
	  }
	  
	  @Command
	  (description = "Seaching movie by name")
	  public void searchUsersByName(@Param(name="name")String fname)
	  {
		  moviemagicAPI.getUsersByName(fname);
	  }
	  
	  @Command
		 (description = "sortingMovies")
		 public void SortByMovies()
		 {
			 moviemagicAPI.SortByMovies();
		 }
		 
		 @Command
		 (description = "sortingUsers")
		 public void SortByUsers()
		 {
			 moviemagicAPI.SortByUsers();
		 }
}
