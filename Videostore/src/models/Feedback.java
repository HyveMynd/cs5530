package models;

import java.sql.Date;

public class Feedback implements IDbModel{
	public int ID;
	public int cid;
	public String isbn;
	public String Score;
	public Date Date;
	public String Comment;
	
	public String toString(){
		return "Customer Id: " + cid + "\n" +
				"ISBN: " + isbn + "\n" +
				"Score: " + Score + "\n" +
				"Date: " + Date.toString() + "\n\n";
	}
}
