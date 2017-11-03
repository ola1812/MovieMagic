package controller;

import java.io.File;
import java.util.Collection;

import com.google.common.base.Optional;

import models.Movies;
import models.Users;
import utils.Serializer;
import utils.XMLSerializer;

public class Main {
	public MovieMagicAPI moviemagicAPI;
	
	public Main() throws Exception {
	File  datastore = new File("./lib/users1.xml");
    Serializer serializer = new XMLSerializer(datastore);

    moviemagicAPI = new MovieMagicAPI(serializer);
    if (datastore.isFile())
    {
      moviemagicAPI.load();
    }
	}

	
	public static void main(String[] args) throws Exception {
		
	    
	     int choice ; 
	     String fname, lname, age, gender, job, title, year, url;
	     Long movieId;
	     Main mainProgramme = new Main();
	     
	     mainProgramme.moviemagicAPI.initalLoad();
	    do {
			System.out.println("Menu");
			System.out.println(" ");
			System.out.println("1.Add User");
			System.out.println("2.Delete User");
			System.out.println("3.Search User");
			System.out.println("4.Add Movie");
			System.out.println("5.Search Movie");
			System.out.println("6.Add Rating");
			
			System.out.println("Enter chouice 1-6");
			
			choice = EasyScanner.nextInt();
			
			switch(choice)
			{
	    case 1:
			     System.out.println("Please enter your first name");
			     fname = EasyScanner.nextString();

			System.out.println("Please enter your surname");
			lname = EasyScanner.nextString();

			System.out.println("Please enter your age");
			age = EasyScanner.nextString();

			System.out.println("Please enter your gender");
			gender = EasyScanner.nextString();

			System.out.println("Please enter your job");
			job = EasyScanner.nextString();
			mainProgramme.AddUser(fname, lname, age, gender, job);
			mainProgramme.moviemagicAPI.store();

			break;
		case 2:
			System.out.println("Please enter userID");
			movieId = (long) EasyScanner.nextInt();
			System.out.println("Please enter the movie title");
			title = EasyScanner.nextString();

			System.out.println("Please enter the release date of the movie");
			year = EasyScanner.nextString();

			System.out.println("Please enter the website url of the movie");
			url = EasyScanner.nextString();
			mainProgramme.addMovie(movieId, title, year, url);

			break;
		case 3:
			mainProgramme.getUsers();
			break;
		case 4:
			System.out.println("Please enter the first name of the user");
			fname = EasyScanner.nextString();
			mainProgramme.getUserByLname(fname);
			break;
		case 0:
			System.out.println("Thanks for using the programme. Goodbye!");
			}
	    }while(choice != 0);
		
	}		
	    
	
	public void AddUser(String fname, String lname, String age, String gender, String job)
	{
	   moviemagicAPI.createUser(fname, lname, age, gender, job);
			
	}
	
	public void getUserByLname(String name) {
		Users users = moviemagicAPI.getUserByLname(name);
		System.out.println(users);
	}

	public void getUsers() {
		Collection<Users> users = moviemagicAPI.getUsers();
		System.out.println(users);
	}
	
	public void addMovie(Long movieId, String title, String year, String url) {
		Optional<Users> user = Optional.fromNullable(moviemagicAPI.getUserById(movieId));
		if (user.isPresent()) {
			moviemagicAPI.createMovie(movieId, title, year, url);
		}
	}

	

	public void addRating(Long userId, Long movieId, int rating) {
		Optional<Movies> ratings = Optional.fromNullable(moviemagicAPI.getMovie(movieId));
		if (ratings.isPresent()) {
			moviemagicAPI.addRatings(ratings.get().id,userId, movieId, rating);
		}
	}
		
		  

}
