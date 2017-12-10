package controller;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Ratings;
import models.Users;

public class AdminMenu {

		  private String name;
		  private MovieMagicAPI movieapi;

		  public AdminMenu(MovieMagicAPI paceApi, String userName) {

		    this.movieapi = movieapi;
		    this.setName(userName);
		  }

		  @Command
		  (description = "Get all users details")
		  public void getUsers() {

		    Collection <Users> users = movieapi.getUsers();
		    System.out.println(users);

		  }
		  public String getName() {
		    return name;
		  }
		  public void setName(String name) {
		    this.name = name;
		  }
		  @Command
		  (description = "Create a new User")//Creating a user
		  public void createUser(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName,
		      @Param(name = "age") String age, @Param(name = "gender") String gender, @Param(name="occupation")String occupation) 
		  {

		    movieapi.createUser(firstName, lastName, age, gender, occupation);

		  }
		  @Command
		  (description = "Get a Users detail")//get user by last name
		  public void getUserByLname(@Param(name = "lname") String lname) {

		    Users user = movieapi.getUserByLname(lname);
		    System.out.println(user);

		  }
		  @Command
		  (description = "Delete a User")//Deleting a user
		  public void deleteUser(@Param(name = "id") Long id) {

		    Optional<Users> user = Optional.ofNullable(movieapi.getUserById(id));
		    if (user.isPresent()) {  //If a user is present then i can delete a user
		      movieapi.deleteUser(user.get().id);
		    }
		  }
		  @Command
		  (description = "Add an movie") //add a movie
		  public void createMovie(@Param(name = "id") long id, @Param(name = "title") String title,
		      @Param(name = "year") String year, @Param(name = "url") String url) {
		    movieapi.createMovie(title, year, url);
		  }
		  
		  
		  @Command
		  (description = "Add a Rating") //Adding a movie rating
		  public void addRating(@Param(name = "adding a rating") Long id, @Param(name = "userId") Long userId,
		      @Param(name = "movieId") Long movieId, @Param(name = "ratings") int ratings) {
		    movieapi.addRatings(userId, movieId, ratings);
		  }
		  
		  @Command 
		  (description = "Get a users rating") //Getting s user's rating
		  public void getUserRating(@Param(name="userId")Long userId)
		  {
			 movieapi.getUserRating(userId);
		  }
		  
		  @Command 
		  (description = "Get movie rating")//getting a movie rating
		  public void getMovieRating(@Param(name="Movie")Long Movie)
		  {
			  movieapi.getMovieRating(Movie);
		  }
		  
		  @Command 
		  (description = "Returning one rating")//returning a rating when called
		  public void getRating(@Param(name = "rating")Long rating)
		  {
			  System.out.println(movieapi.getRating(rating));
		  }
		  
		  @Command
		  (description = "Return all ratings")//returning all ratings
		  public void getAllRatings()
		  {
			  movieapi.getAllRatings();
		  }
		  
		  @Command
		  (description = "delete one rating")
		  public void deleteRating(@Param(name="Rating id")long id)
		  {
			  movieapi.deleteRating(id);
		  }
		 
		 @Command
		 (description = "sortingMovies")
		 public void SortByMovies()
		 {
			 movieapi.SortByMovies();
		 }
		 
		 @Command
		 (description = "sortingUsers")
		 public void SortByUsers()
		 {
			 movieapi.SortByUsers();
		 }
}
