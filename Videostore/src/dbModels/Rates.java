package dbModels;

import databaseOps.IDbModel;

public class Rates implements IDbModel{
	public int cid;
	public int fid;
	public String rating;
	
//	public String toString(){
//		return "Customer Id: " + cid + "\n" +
//				"Feedback Id: " + fid + "\n" +
//				"Rating: " + rating + "\n\n";
//	}
	
	public String toString(){
		return "<h2>Rating:</h2>" +
				"<h3>Customer Id: " + cid + "</h3>" +
				"<h3>Feedback Id: " + fid + "</h3>" +
				"<h3>Rating: " + rating + "</h3><br>";
	}
}
