package cs5530;

import java.sql.Date;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;

import models.Customer;
import models.Feedback;
import models.Inventory;
import models.Orders;
import models.PlaceOrder;
import models.Rates;
import models.Trusted;
import models.Video;
import databaseOps.Insert;
import databaseOps.OP;
import databaseOps.QueryResult;
import databaseOps.Select;
import databaseOps.Update;

public class Options {
	public static QueryResult<Customer> registerUser(Statement stmt, Customer customer) throws Exception{
		String query = "";
		QueryResult<Customer> result;
				
		try{
			result = Insert.into(customer).execute(stmt);
			query = result.sql;
			result.queryResults = Select.from(customer).execute(stmt).queryResults;
        } catch(Exception e) {
			System.err.println("Unable to execute query: "+ query);
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("Register:registerUser query="+query+"\n");
		result.resultStr = "Successful Register.";
		return result;
	}
	
	public static String userRecord(Statement stmt, String login) throws Exception{
		String resultstr = "";
		Customer custInfo;
		Collection<PlaceOrder> placeOrderInfo;
		Collection<Feedback> feedbackInfo;
		Collection<Rates> ratesInfo;
		Collection<Trusted> trustedInfo;
		try{
			custInfo = CustomerOperations.getCustomerWithLogin(stmt, login);
			String id = Integer.toString(custInfo.ID);
			placeOrderInfo = OrderOperations.getPlaceOrdersForCustomer(stmt, id);
			feedbackInfo = FeedbackOperations.getFeedbackForCustomer(stmt, id);
			ratesInfo = FeedbackOperations.getRatesForCustomer(stmt, id);
			trustedInfo = FeedbackOperations.getTrustedForCustomer(stmt, id);
			
			//Get the video names
			String vidNames = "";
			for (PlaceOrder po : placeOrderInfo)
				vidNames += Select.from(new Video()).where("ISBN", OP.Equal, po.ISBN).execute(stmt)
						.firstOrDefault().Title + ",";
			vidNames += "\n\n";
			
			resultstr = custInfo.toString();
			resultstr += Helper.tablesToString(placeOrderInfo);
			resultstr += vidNames;
			resultstr += Helper.tablesToString(feedbackInfo);
			resultstr += Helper.tablesToString(ratesInfo);
			resultstr += Helper.tablesToString(trustedInfo);
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
		return resultstr;
	}
	
	public static String orderVideos(Statement stmt, int id, String[] videos,
			String ccNum) throws Exception {
		
		// Map representing video, quantity
		HashMap<String, String> selections = new HashMap<String, String>();
		
		for (String video : videos){
			String[] selection = video.split(",");
			selections.put(selection[0], selection[1]);
		}
		Video video = new Video();
		double total = 0;
		
		//Add up the prices
		for (String isbn : selections.keySet()){
			Collection<Video> vid = Select.from(video)
					.where("ISBN", OP.Equal, isbn)
					.execute(stmt).queryResults;
			for (Video v : vid){
				total += Double.parseDouble(v.Price);
			}
		}
		
		// Insert a new Order
		Orders order = new Orders();
		order.CreditCardNumber = ccNum;
		order.PriceTotal = Double.toString(total);
		int oid = Insert.into(order).execute(stmt).getAutoGeneratedKey();
		PlaceOrder newOrder = new PlaceOrder();
		// Insert to PlaceOrder
		for (String isbn : selections.keySet()){
			newOrder.cid = id;
			newOrder.ISBN = isbn;
			newOrder.OrderDate = getDate().toString();
			newOrder.VideoCount = selections.get(isbn);
			newOrder.oid = oid;
			
			Insert.into(newOrder).execute(stmt);
		}
		
		return "Order Successful!\n\nHere are some suggested movies: \n" + getSuggested(stmt, newOrder.ISBN);
	}
	
	public static String addVideo(Statement stmt, Video video) throws Exception{
		String query = "";
		String resultstr="";
		QueryResult<Video> result;
				
		try{
			result = Insert.into(video).execute(stmt);
			query = result.sql;
			Inventory inv = new Inventory();
			Video vid = Select.from(video).where("ISBN", OP.Equal, video.ISBN).execute(stmt).firstOrDefault();
			inv.ISBN = vid.ISBN;
			inv.copies = "1";
			Insert.into(inv).execute(stmt);
        } catch(Exception e) {
			System.err.println("Unable to execute query: "+ query);
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("Videos: addVideo query="+query+"\n");
		resultstr = "Successfully added video.";
		return resultstr;
	}
	
	private static Date getDate(){
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		java.sql.Date sqlDate = new Date(utilDate.getTime());
		return sqlDate;
	}

	public static String addToInventory(Statement stmt, String isbn,
			int count) throws Exception {
		Inventory inv = Select.from(new Inventory()).where("ISBN", OP.Equal, isbn).execute(stmt).firstOrDefault();
		int vidCount = Integer.parseInt(inv.copies);
		vidCount += count;
		inv.copies = Integer.toString(vidCount);
		Update.set(inv).where("ISBN", OP.Equal, isbn).execute(stmt);
		return "Add to Inventory Successful!";
	}

	public static String addFeedback(Statement stmt, int id, String isbn, String score,
			String comment) throws Exception{
		Feedback feedback = Select.from(new Feedback()).where("cid", OP.Equal, id).execute(stmt).firstOrDefault();
		if (feedback != null && feedback.isbn == isbn)
			return "Already gave feedback for this movie";
		feedback  = new Feedback();
		
		feedback.cid = id;
		feedback.isbn = isbn;
		feedback.Date = getDate();
		feedback.Score = score;
		feedback.Comment = comment;
		Insert.into(feedback).execute(stmt);
		return "Successfully added a feedback!";
	}

	public static String addRating(Statement stmt, int id, int fid,
			int rating) throws Exception{
		Rates rates = Select.from(new Rates()).where("cid", OP.Equal, id).execute(stmt).firstOrDefault();
		if (rates != null && rates.cid == id)
			return "Cannot rate your own feedback";
		rates = new Rates();
		
		rates.cid = id;
		rates.fid = fid;
		rates.rating = rating;
		
		Insert.into(rates).execute(stmt);
		
		return "Successfully added a rating!";
	}

	public static String addTrustedRating(Statement stmt, int cidFrom, int cidTo,
			int rating) throws Exception{
		Trusted trusted = new Trusted();
		trusted.cidFrom = cidFrom;
		trusted.cidTo = cidTo;
		
		switch(rating){
		case 0:
			trusted.IsTrusted = "Not-trusted";
			break;
		case 1:
			trusted.IsTrusted = "Trusted";
			break;
		}
		Insert.into(trusted).execute(stmt);
		
		return "Successfully added trusted rating!";
	}

	public static String movieSearch(Statement stmt, String search, int order) throws Exception{
		String[] searchCrit = search.split(",");
		String sql = "select f.*, a.*, r.*, " +
				"t.isTrusted from Video a, Rates r, Feedback f, Trusted t " +
				"where f.ISBN=a.ISBN and f.cid = r.cid and t.cidTo = r.cid or ";
				
		if (!searchCrit[0].isEmpty())
			sql += "a.ActorName like '%" + searchCrit[0] + "%' ";
		if(!searchCrit[1].isEmpty())
			sql += "a.Director like '%" + searchCrit[1] + "%' ";
		if(!searchCrit[2].isEmpty())
			sql += "a.Title like '%" + searchCrit[2] + "%' ";
		if(!searchCrit[3].isEmpty())
			sql += "r.Rating like '%" + searchCrit[3] + "%' ";
		
		switch(order){
		case 1:
			sql += "group by f.ISBN order by a.YearOfProduction desc";
			break;
		case 2:
			sql += "group by f.ISBN order by AVG(f.score) desc";
			break;
		case 3:
			sql += "and t.isTrusted = 'Trusted' group by f.ISBN order by AVG(f.score) desc";
			break;
		}
		
		QueryResult<Video> result = Select.from(new Video()).execute(stmt, sql);
		String ret = Helper.tablesToString(result.queryResults);
		return ret;
	}

	public static String searchMostUseful(Statement stmt, String isbn, int count) throws Exception{
		Feedback feedback = Select.from(new Feedback()).where("ISBN", OP.Equal, isbn).execute(stmt).firstOrDefault();
		String sql = "select from Rates where fid = "+feedback.ID+" order by avg(rating) desc";
		QueryResult<Rates> results = Select.from(new Rates()).execute(stmt, sql);
		String ret = Helper.tablesToString(results.queryResults, count);
		return ret;
	}

	public static String getStatistics(Statement stmt, int videoCount, int directorCount, int actorCount) 
	throws Exception{
		String sql = "SELECT v.* FROM Video v, PlaceOrder p where v.isbn = p.isbn group by p.isbn order by p.videocount desc";
		QueryResult<Video> videoList = Select.from(new Video()).execute(stmt, sql);
		sql = "SELECT v.* FROM Video v, PlaceOrder p where v.isbn = p.isbn group by p.isbn order by p.videocount desc";
		QueryResult<Video> directorList = Select.from(new Video()).execute(stmt, sql);
		sql = "SELECT v.* FROM Video v, PlaceOrder p where v.isbn = p.isbn group by p.isbn order by p.videocount desc";
		QueryResult<Video> actorList = Select.from(new Video()).execute(stmt, sql);
		
		String ret = "The most Popular movies are: " +  Helper.tablesToString(videoList.queryResults, videoCount);
		ret += "The most popular movie directors are :" + Helper.tablesToString(directorList.queryResults, directorCount);
		ret += "The most popular actors are: " + Helper.tablesToString(actorList.queryResults, actorCount);
		
		return ret;
	}
	
	public static String getSuggested(Statement stmt, String isbnOrdered) throws Exception{
		String sql = "select v.* from Video v, PlaceOrder p where v.isbn = p.isbn and p.cid in(" +
				"select cid from PlaceOrder p where isbn=" + isbnOrdered + ")";
		QueryResult<Video> videoList = Select.from(new Video()).execute(stmt, sql);
		
		return Helper.tablesToString(videoList.queryResults);
	}
}