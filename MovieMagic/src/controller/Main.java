package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.Users;
import utils.FileLogger;

public class Main {

	
	public static void main(String[] args) throws IOException {
		
		MovieMagicAPI moviemagicAPI = new MovieMagicAPI();
		
		FileLogger logger = FileLogger.getLogger();
		logger.log("Adding users");
		
		moviemagicAPI.createUser("Ola","Bartos",18,"female","magician");
		moviemagicAPI.createUser("Marcel","Laptop",6,"laptop","laptop");
		
		
		Collection <Users> users = moviemagicAPI.getUser();
		
		System.out.println(users);
		
		Users ola = moviemagicAPI.getUsersByLname("Bartos");
		System.out.println(ola);
		
		moviemagicAPI.deleteUser(ola.id);
		users = moviemagicAPI.getUser();
		System.out.println(users);
		
		logger.log("Finish adding users");
		
		logger.log("Adding users to XML file");
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("./lib/users.xml"));
		out.writeObject(users);
		out.close();
		
		logger.log("Finish shutting down");
	}

}
