package models;

public class Rates implements IDbModel{
	public int cid;
	public int fid;
	public int rating;
	
	public String toString(){
		return "Customer Id: " + cid + "\n" +
				"Feedback Id: " + fid + "\n" +
				"Rating: " + rating + "\n\n";
	}
}
