package databaseOps;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;


public class Select<T extends IDbModel> extends DatabaseOp<T>{

	ArrayList<String> tableNames;
	protected Select(T table) throws IllegalArgumentException,
			IllegalAccessException, InstantiationException {
		super(table);
	}

	public static <T extends IDbModel> Select<T> from(T table) throws IllegalArgumentException, IllegalAccessException, InstantiationException{
		return new Select<T>(table);
	}

	@Override
	public QueryResult<T> execute(Statement stmt) throws Exception {
		sql = "select * from "+tableName+" "+whereStr;
		
		//save results
		QueryResult<T> result = new QueryResult<T>();
		result.sql = sql;
		stmt.execute(sql);
		result.queryResults = saveResults(stmt);
		return result;
	}
	
	public QueryResult<T> execute(Statement stmt, String sql) throws Exception{
		QueryResult<T> result = new QueryResult<T>();
		stmt.execute(sql);
		result.queryResults = saveResults(stmt);
		return result;
	}

	@SuppressWarnings("unchecked")
	private Collection<T> saveResults(Statement stmt) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		ResultSet resultSet = stmt.getResultSet();
		Collection<T> list = new ArrayList<T>();
		while(resultSet.next()){
			T  model = (T) table.getClass().newInstance();
			for (Field f : fields){
				String name = f.getName();
				if (isId(name))
					f.set(model, resultSet.getInt(name));
				else if(name.compareToIgnoreCase("date") == 0)
					f.set(model, resultSet.getDate(name));
				else
					f.set(model, resultSet.getString(name));
			}
			list.add(model);
		}
		return list;
	}
	
	private boolean isId(String name){
		return name.compareToIgnoreCase("id") == 0 || name.compareToIgnoreCase("cid") == 0 || 
				name.compareToIgnoreCase("oid") == 0 || name.compareToIgnoreCase("fid") == 0 ||
				name.compareToIgnoreCase("cidFrom") == 0 || name.compareToIgnoreCase("cidTo") == 0;
	}
}
