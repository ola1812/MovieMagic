package controller;

import java.io.File;
import java.io.IOException;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import models.Users;
import utils.Serializer;
import utils.XMLSerializer;

public class Main implements ShellDependent {
	private static final String ADMIN = "admin";
	public MovieMagicAPI moviemagicAPI;
	private Shell theShell;
	
	public Main() throws Exception {
	File  datastore = new File("./lib/users2.xml");
    Serializer serializer = new XMLSerializer(datastore);

    moviemagicAPI = new MovieMagicAPI(serializer);
    if (datastore.isFile())
    {
      moviemagicAPI.load();
    }
	}

	public void cliSetShell(Shell theShell) {
		this.theShell = theShell;
	}
	
	@Command(description = "Log in")
	public void logIn(@Param(name= "user name") Long userId, @Param(name="Lname")String lname)
    throws IOException{
    	
    	  if (moviemagicAPI.login(userId, lname) && moviemagicAPI.currentUser.isPresent()) {
    	      Users user = moviemagicAPI.currentUser.get();
    	      System.out.println("You are logged in as " + user.lname);
    	      if (user.role!=null && user.role.equals(ADMIN)) {
    	        AdminMenu adminMenu = new AdminMenu(moviemagicAPI, user.lname);
    	        ShellFactory.createSubshell(user.lname, theShell, "Admin", adminMenu).commandLoop();
    	      } else {
    	        DefaultMenu defaultMenu = new DefaultMenu(moviemagicAPI, user);
    	        ShellFactory.createSubshell(user.lname, theShell, "Default", defaultMenu).commandLoop();
    	      }
    	    } else
    	      System.out.println("Unknown username/password.");
    }
	
    
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.moviemagicAPI.initalLoad();
        Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to pacemaker-console - ?help for instructions",
            main);
        shell.commandLoop();
        main.moviemagicAPI.store();
      }
	
    

}
