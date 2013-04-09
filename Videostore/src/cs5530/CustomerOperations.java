package cs5530;

import java.sql.Statement;
import java.util.Collection;

import databaseOps.OP;
import databaseOps.Select;
import dbModels.Customer;

public class CustomerOperations {
	
	public static Customer getCustomer(Statement stmt, String id) throws Exception{
		try{
			return Select.from(new Customer())
					.where("ID", OP.Equal, id)
					.execute(stmt).queryResults
					.iterator().next();
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
	}
	
	public static Customer getCustomerWithLogin(Statement stmt, String login) throws Exception{
		try{
			return Select.from(new Customer())
					.where("Login", OP.Equal, login)
					.execute(stmt).queryResults
					.iterator().next();
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
	}
	
	public static Collection<Customer> getCustomers(Statement stmt) throws Exception{
		try{
			return Select.from(new Customer())
					.execute(stmt).queryResults;
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
	}
}
