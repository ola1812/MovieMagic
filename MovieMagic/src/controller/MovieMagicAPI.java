package controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.common.base.Optional;

import models.Movies;
import models.Ratings;
import models.Users;
import utils.Serializer;

public class MovieMagicAPI {
	
	private Serializer serializer;
	private Map<Long, Users> 	usersIndex = new HashMap<>();
	private Map<String, Users> 	usersName = new HashMap<>();
	private Map<Long, Movies> 	movieIndex = new HashMap<>();
	private Map<Long, Ratings>   ratingIndex = new HashMap<>();
    Optional <Users> currentUser;
	
	public MovieMagicAPI() {

	}

	public MovieMagicAPI(Serializer serializer) {
		this.serializer = serializer;
	}
	
	public void initalLoad() throws IOException {
		String delims = "[|]";
		Scanner scanner = new Scanner(new File("./lib/users5.dat"));
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 7) {
				createUser(userTokens[1], userTokens[2], userTokens[3], userTokens[4], userTokens[5]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}
		scanner.close();
	}

	
	@SuppressWarnings("unchecked")
	public void load() throws Exception {
		serializer.read();
		usersIndex 		= (Map<Long, Users>) serializer.pop();
		movieIndex 		= (Map<Long, Movies>) serializer.pop();
		//usersName 		= (Map<String, Users>) serializer.pop();
	}
	
	public void store() throws Exception {
		serializer.push(movieIndex);
		serializer.push(usersIndex);
		//serializer.push(usersName);
		serializer.write(); 
	}

	public Collection<Users> getUsers() {
		return usersIndex.values();
	}

	public void deleteUsers() {
		usersIndex.clear();
		usersName.clear();
	}

	public Users createUser(String firstName, String lastName, String age, String gender, String occupation) {
		Users user = new Users(firstName, lastName, age, gender, occupation);
		usersIndex.put(user.id, user);
		usersName.put(firstName, user);
		return user;
	}
	
	public Users getUserByLname(String name) {
		return usersName.get(name);
	}

	public Users getUserById(Long id) {
		return usersIndex.get(id);
	}

	public void deleteUser(Long id) {
		Users user = usersIndex.remove(id);
		usersName.remove(user.lname);
	}

	public void createMovie(String title, String year, String url) {
		Movies movie;
			movie = new Movies(title, year, url);
			movieIndex.put(movie.id, movie);
		}
	

	public Movies getMovie(Long id) {
		return movieIndex.get(id);
	}

	public void addRatings(Long userID, Long movieID, int rating) {
		Ratings ratings ;
		Optional<Users> user = Optional.fromNullable(usersIndex.get(userID));
		Optional<Movies> movie = Optional.fromNullable(movieIndex.get(movieID));
		if (movie.isPresent() && user.isPresent()) {
			ratings = new Ratings(userID, movieID, rating); //Adding a new rating 
			user.get().UserRating.put(ratings.id, ratings); //Putting a user and a rating together
		    movie.get().movieThing.put(ratings.id, ratings); //Putting Movie and rating together
		    ratingIndex.put(ratings.id, ratings); //Putting a rating into a collection
		}
	}
	
	
	
	
	
	
	// simplified login method
	  public boolean login(String userId, String lName) {
	    Optional<Users> user = Optional.fromNullable(usersIndex.get(userId));
	    if (user.isPresent() && user.get().lname.equals(lName)) {
	      currentUser = user;
	      return true;
	    }
	    return false;
	  }
	
	  // simplified and generalized version of my logout method
	  public void logout() {
	    Optional<Users> user = currentUser;
	    if (user.isPresent()) {
	   
	      currentUser = Optional.absent();
	    }
	  }
	
}

