package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

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
		Scanner scanner = new Scanner(new File("./lib/users5.dat"));//Reading users file
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
		scanner = new Scanner(new File("./lib/items5.dat"));//Reading movie Files
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 23) {
				createMovie(userTokens[1], userTokens[2], userTokens[3]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}
		scanner = new Scanner(new File("./lib/ratings5.dat"));//Reading ratings files
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 4) {
				addRatings(Long.valueOf(userTokens[0]),Long.valueOf(userTokens[1]),Integer.valueOf(userTokens[2]));
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

	public Collection<Movies> getMovies()
	{
		return movieIndex.values();
	}
	
	public void addRatings(Long userID, Long movieID, int rating) 
	{
		Ratings ratings ;
		Optional<Users> user = Optional.fromNullable(usersIndex.get(userID));//gets userID to Link user to a rating
		Optional<Movies> movie = Optional.fromNullable(movieIndex.get(movieID));//Links movie and a rating
		if (movie.isPresent() && user.isPresent()) {
			ratings = new Ratings(userID, movieID, rating); //Adding a new rating 
			user.get().UserRating.put(ratings.id, ratings); //Putting a user and a rating together
		    movie.get().movieThing.put(ratings.id, ratings); //Putting Movie and rating together
		    ratingIndex.put(ratings.id, ratings); //Putting a rating into a collection
		}
	}
	
	public void getUserRating(Long userID) 
	{
		Optional<Users> user = Optional.fromNullable(usersIndex.get(userID));//getting userID to link it to rating 
		System.out.println(user.get().UserRating);//printing out the ratings that that user gave
	}
	
	public void getMovieRating(Long Movie)
	{
		Optional<Movies> movie = Optional.fromNullable(movieIndex.get(Movie));//getting movie and linking it to rating
		System.out.println(movie.get().movieThing);//printing out the rating for that movie
	}

	
	
	public Ratings getRating(Long ratings)
	{
		return ratingIndex.get(ratings);
	}
	
	public Collection<Ratings> getAllRatings()
	{
		return ratingIndex.values();
	}
	
	public void deleteRating(Long rating)
	{
		ratingIndex.remove(rating);
	}
	
	
	public void getMovieByTitle(String title)
	{
		ArrayList<Movies> movieTitle=new ArrayList<Movies>();
		movieTitle.addAll(getMovies());
		for(int i=0; i<movieTitle.size();i++)
		{
			if(movieTitle.get(i).title.toUpperCase().contains(title.toUpperCase()))
			{
				System.out.println(movieTitle.get(i));
			}
		}
	}
	
	public void getUsersByName(String fname)
	{
		ArrayList<Users> firstName=new ArrayList<Users>();
		firstName.addAll(getUsers());
		for(int i=0; i<firstName.size();i++)
		{
			if(firstName.get(i).fname.toUpperCase().contains(fname.toUpperCase()))
			{
				System.out.println(firstName.get(i));
			}
		}
	}
	
	public void SortByUsers()//searching by users
	{
		TreeSet<Users> sortedUsers = new TreeSet<Users>();
		sortedUsers.addAll(getUsers());
		Iterator<Users> iter = sortedUsers.iterator();//Goes through users to search through them
		while(iter.hasNext())
		{
			Users users = iter.next();
			System.out.println(users.fname +" "+ users.lname);
		}
	}
	
	public void SortByMovies()//searching by movies
	{
		TreeSet<Movies> sortedMovies = new TreeSet<Movies>();
		sortedMovies.addAll(getMovies());
		Iterator<Movies> iter = sortedMovies.iterator();//Goes through movies to search through them
		while(iter.hasNext())
		{
			Movies movies = iter.next();
			System.out.println(movies.title);
		}
	}
	
	// simplified login method
	  public boolean login(Long userId, String lName) {
	    Optional<Users> user = Optional.fromNullable(usersIndex.get(userId));//Links a userId to lName
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

