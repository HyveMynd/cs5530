package dbModels;

import java.sql.Date;

import databaseOps.IDbModel;

public class Feedback implements IDbModel{
	public int ID;
	public int cid;
	public String isbn;
	public String Score;
	public Date Date;
	public String Comment;
	
//	public String toString(){
//		return "Customer Id: " + cid + "\n" +
//				"ISBN: " + isbn + "\n" +
//				"Score: " + Score + "\n" +
//				"Date: " + Date.toString() + "\n\n";
//	}
	
	public String toString(){
		return "<h2>Feedback:</h2>" +
				"<h3>Feedback ID:" + ID + "</h3>" +
				"<h3>Customer Id: " + cid + "</h3>" +
				"<h3>ISBN: " + isbn + "</h3>" +
				"<h3>Score: " + Score + "</h3>" +
				"<h3>Date: " + Date.toString() + "</h3><br>";
	}
}
