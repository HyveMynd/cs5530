package dbModels;

import databaseOps.IDbModel;

public class Customer implements IDbModel{
	public int ID;
	public String firstName;
	public String lastName;
	public String phone;
	public String address;
	public String login;
	public String password;
	public String Role;
	
	// Used for phase 2
//	public String toString(){
//		return "First Name: " + firstName + "\n"+
//				"Last Name: " + lastName + "\n"+
//				"Phone: " + phone + "\n"+
//				"Address: " + address + "\n"+
//				"Login: " + login + "\n" + 
//				"Password: " + password + "\n\n";
//	}
	
	// Used for phase 3
	public String toString(){
		return "<h2>Customer:</h2>" +
				"<h3>First Name: " + firstName + "</h3>"+
				"<h3>Last Name: " + lastName + "</h3>"+
				"<h3>Phone: " + phone + "</h3>"+
				"<h3>Address: " + address + "</h3>"+
				"<h3>Login: " + login + "</h3>" + 
				"<h3>Password: " + password + "</h3><br>";
	}
}
