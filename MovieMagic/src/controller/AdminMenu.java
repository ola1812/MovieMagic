package controller;

import java.util.Collection;
import java.util.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
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
		  (description = "Create a new User")
		  public void createUser(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName,
		      @Param(name = "age") String age, @Param(name = "gender") String gender, @Param(name="occupation")String occupation) 
		  {

		    movieapi.createUser(firstName, lastName, age, gender, occupation);

		  }
		  @Command(description = "Get a Users detail")//get user by last name
		  public void getUserByLname(@Param(name = "lname") String lname) {

		    Users user = movieapi.getUserByLname(lname);
		    System.out.println(user);

		  }
		  @Command(description = "Delete a User")
		  public void deleteUser(@Param(name = "id") Long id) {

		    Optional<Users> user = Optional.ofNullable(movieapi.getUserById(id));
		    if (user.isPresent()) {
		      movieapi.deleteUser(user.get().id);
		    }
		  }
		  @Command(description = "Add an movie") //add a movie
		  public void createMovie(@Param(name = "id") long id, @Param(name = "title") String title,
		      @Param(name = "year") String year, @Param(name = "url") String url) {
		    movieapi.createMovie(title, year, url);
		  }
		  
		  
//		  @Command(description = "Add Location to an activity")
//		  public void addLocation(@Param(name = "activity-id") Long id, @Param(name = "latitude") float latitude,
//		      @Param(name = "longitude") float longitude) {
//		    Optional<Activity> activity = Optional.fromNullable(paceApi.getActivity(id));
//		    if (activity.isPresent()) {
//		      movieapi.addLocation(activity.get().id, latitude, longitude);
//		    }
//		  }
		
}
