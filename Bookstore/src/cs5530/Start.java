package cs5530;


import java.sql.*;
import java.io.*;

import models.Customer;
import models.Video;

public class Start {

	/**
	 * @param args
	 */
	public static void displayMenu()
	{
		 System.out.println("        Video Store Management System     ");
    	 System.out.println("1. Register User:");
    	 System.out.println("2. enter your own query:");
    	 System.out.println("3. exit:");
    	 System.out.println("4. Add Video:");
    	 System.out.println("5. Order Videos:");
    	 System.out.println("6. All Videos:");
    	 System.out.println("pleasse enter your choice:");
	}
	
	@SuppressWarnings("null")
	public static void main(String[] args) {
		System.out.println("Example for cs5530");
		Connector con=null;
		String choice;
        String sql=null;
        User user = new User();
        
        int c=0;
         try
		 {
			//remember to replace the password
			 	 con= new Connector();
	             System.out.println ("Database connection established");
	         
	             BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	             
	             while(true)
	             {
	            	 displayMenu();
	            	 while ((choice = in.readLine()) == null && choice.length() == 0);
	            	 try{
	            		 c = Integer.parseInt(choice);
	            	 }catch (Exception e)
	            	 {
	            		 
	            		 continue;
	            	 }
	            	 if (c<1 | c>20)
	            		 continue;
	            	 if (c==1)
	            	 {
	            		 Customer customer = new Customer();

	            		 customer.firstName = enterData("please enter a first name:", in);
	            		 customer.lastName = enterData("please enter a last name:", in);
	            		 customer.phone = enterData("please enter a phone:", in);
	            		 customer.address = enterData("please enter an address:", in);
	            		 customer.login = enterData("please enter a login:", in);
	            		 customer.password = enterData("please enter a password:", in);

	            		 CustomerOperations custOp = new CustomerOperations();
	            		 System.out.println(custOp.registerUser(con.stmt, customer));
	            	 }
	            	 else if (c==2)
	            	 {	 
	            		 System.out.println("please enter your query below:");
	            		 while ((sql = in.readLine()) == null && sql.length() == 0)
	            			 System.out.println(sql);
	            		 ResultSet rs=con.stmt.executeQuery(sql);
	            		 ResultSetMetaData rsmd = rs.getMetaData();
	            		 int numCols = rsmd.getColumnCount();
	            		 while (rs.next())
	            		 {
	            			 //System.out.print("cname:");
	            			 for (int i=1; i<=numCols;i++)
	            				 System.out.print(rs.getString(i)+"  ");
	            			 System.out.println("");
	            		 }
	            		 System.out.println(" ");
	            		 rs.close();
	            	 }
	            	 else if (c==4)
	            	 {
	            		 Video video = new Video();

	            		 video.ISBN = enterData("please enter an isbn:", in);
	            		 video.Title = enterData("please enter a title:", in);
	            		 video.Subject= enterData("please enter a subject:", in);
	            		 video.Rating = enterData("please enter a rating:", in);
	            		 video.Price = enterData("please enter a price:", in);
	            		 video.Format = enterData("please enter a format:", in);
	            		 video.YearOfProduction = enterData("please enter a year of production:", in);
	
	            		 VideoOperations vidOp = new VideoOperations();
	            		 System.out.println(vidOp.addVideo(con.stmt, video));
	            	 }
	            	 else if (c==5)
	            	 {
	            		 VideoOperations vidOp = new VideoOperations();
	            		 System.out.println(vidOp.allVideos(con.stmt, new Video()));
	            		 String selectedVids = enterData("please enter the videos you wish to order in the " +
	            		 		"following format: isbn,quantity ; isbn,quantity", in);
	            		 String ccNum = enterData("please enter a major credit card number", in);
	            		 String[] videos = selectedVids.split(";");
	            		 OrderOperations orderOp = new OrderOperations();
	            		 orderOp.orderVideos(con.stmt, user.id, videos, ccNum);
	            	 }
	            	 else if (c==6)
	            	 {
	            		 VideoOperations addVid = new VideoOperations();
	            		 System.out.println(addVid.allVideos(con.stmt, new Video()));
	            	 }
	            	 else
	            	 {   
	            		 System.out.println("Remeber to pay us!");
	            		 con.stmt.close(); 
	        
	            		 break;
	            	 }
	             }
		 }
         catch (Exception e)
         {
        	 e.printStackTrace();
        	 System.err.println ("Cannot connect to database server");
         }
         finally
         {
        	 if (con != null)
        	 {
        		 try
        		 {
        			 con.closeConnection();
        			 System.out.println ("Database connection terminated");
        		 }
        	 
        		 catch (Exception e) { /* ignore close errors */ }
        	 }	 
         }
	}
	
	@SuppressWarnings("null")
	private static String enterData(String line, BufferedReader in) throws IOException{
		String returnStr;
		System.out.println(line);
		while ((returnStr = in.readLine()) == null && returnStr.length() == 0);
		
		return returnStr;
	}
	
	
	
	
}



