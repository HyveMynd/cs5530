package dbModels;

import databaseOps.IDbModel;


public class PlaceOrder implements IDbModel{
	public int oid;
	public int cid;
	public String ISBN;
	public String OrderDate;
	public String VideoCount;
	
//	public String toString(){
//		return "Order Id: " + oid + "\n" +
//				"Customer Id: " + cid + "\n" +
//				"ISBN: " + ISBN + "\n" +
//				"Order Date: " + OrderDate + "\n" +
//				"Video Count: " + VideoCount + "\n\n";
//	}
	
	public String toString(){
		return "<h2>Order:</h2>" +
				"<h3>Order Id: " + oid + "</h3>" +
				"<h3>Customer Id: " + cid + "</h3>" +
				"<h3>ISBN: " + ISBN + "</h3>" +
				"<h3>Order Date: " + OrderDate + "</h3>" +
				"<h3>Video Count: " + VideoCount + "</h3><br>";
	}
}
