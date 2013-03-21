package databaseOps;

import java.sql.*;

import models.IDbModel;

public class Insert<T extends IDbModel> extends DatabaseOp<T>{

	protected Insert(T table) throws IllegalArgumentException,
			IllegalAccessException, InstantiationException {
		super(table);
	}

	public static <T extends IDbModel> Insert<T> from(T table) throws IllegalArgumentException, IllegalAccessException, InstantiationException  {
		return new Insert<T>(table);
	}
	
	@Override
	public QueryResult<T> execute(Statement stmt) throws SQLException {
		sql = "insert into "+tableName+" ";
		
		sql += columns()+" ";
		
		sql += "values "+values()+" "+whereStr;
		
		//save results
		QueryResult<T> result = new QueryResult<T>();
		result.sql = sql;
		stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		result.autoGeneratedKeys = stmt.getGeneratedKeys();
				
		return result;
	}
}
