package cs5530;

import java.sql.Statement;
import java.util.Collection;

import databaseOps.OP;
import databaseOps.Select;
import dbModels.Feedback;
import dbModels.Rates;
import dbModels.Trusted;

public class FeedbackOperations {
	public static Collection<Feedback> getFeedbackForCustomer(Statement stmt, String id) throws Exception{
		try{
			return Select.from(new Feedback())
					.where("cid", OP.Equal, id)
					.execute(stmt).queryResults;
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
	}
	
	public static Collection<Rates> getRatesForCustomer(Statement stmt, String id) throws Exception{
		try{
			return Select.from(new Rates())
					.where("cid", OP.Equal, id)
					.execute(stmt).queryResults;
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
	}
	
	public static Collection<Trusted> getTrustedForCustomer(Statement stmt, String id) throws Exception{
		try{
			return Select.from(new Trusted())
					.where("cidFrom", OP.Equal, id)
					.execute(stmt).queryResults;
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
	}
}
