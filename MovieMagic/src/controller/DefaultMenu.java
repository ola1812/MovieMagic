package controller;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
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
  	  @Command(description = "Get a User by ID")
  	  public void getUser(@Param(name = "id") Long id) {
  	    Users user = moviemagicAPI.getUserById(id);
  	    System.out.println(user);
  	  }
  	  @Command(description = "Add a Movie")
  	  public void addMovie(@Param(name = "title") String title, @Param(name = "year") String year,
  	      @Param(name = "url") String url) {
  	    moviemagicAPI.createMovie(title, year, url);
  	  }
  	  @Command(description = "Add a Rating")
  	  public void addRating(@Param(name = "activity-id") Long id, @Param(name = "userId") Long userId,
  	      @Param(name = "movieId") Long movieId, @Param(name="rating")int rating) {
  	      moviemagicAPI.addRatings(userId, movieId,rating);
  	    
  	  }
  	  public String getName() {
  	    return name;
  	  }
  	  public void setName(String name) {
  	    this.name = name;
  	  }
 
		  

}
