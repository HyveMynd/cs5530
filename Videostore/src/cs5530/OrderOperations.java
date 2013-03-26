package cs5530;

import java.sql.Statement;
import java.util.Collection;

import databaseOps.OP;
import databaseOps.Select;

import models.Orders;
import models.PlaceOrder;

public class OrderOperations {
	
	public static Collection<Orders> getOrdersForCustomer(Statement stmt, String id) throws Exception{
		try{
			return Select.from(new Orders())
					.where("cid", OP.Equal, id)
					.execute(stmt).queryResults;
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
	}
	
	public static Orders getOrder(Statement stmt, String oid)throws Exception{
		try{
			return Select.from(new Orders())
					.where("oid", OP.Equal, oid)
					.execute(stmt).queryResults
					.iterator().next();
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
	}
	
	public static Collection<Orders> getOrders(Statement stmt)throws Exception{
		try{
			return Select.from(new Orders())
					.execute(stmt).queryResults;
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
	}
	
	public static Collection<PlaceOrder> getPlaceOrdersForCustomer(Statement stmt, String id) throws Exception{
		try{
			return Select.from(new PlaceOrder())
					.where("cid", OP.Equal, id)
					.execute(stmt).queryResults;
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
	}
	
	public static Collection<PlaceOrder> getPlaceOrders(Statement stmt)throws Exception{
		try{
			return Select.from(new PlaceOrder())
					.execute(stmt).queryResults;
		}
		catch(Exception e){
            System.err.println(e.getMessage());
            throw(e);
		}
	}

}
