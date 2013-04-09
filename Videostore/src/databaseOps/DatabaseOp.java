package databaseOps;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.TreeMap;


public abstract class DatabaseOp<T extends IDbModel>{
	protected T table;
	protected String sql;
	protected Field[] fields;
	protected Class<? extends IDbModel> entity;
	protected TreeMap<String, String> values;
	protected String tableName;
	protected String whereStr = "";
	
	protected DatabaseOp(T table) throws IllegalArgumentException, IllegalAccessException, InstantiationException{
		this.table = table;
		sql = "";
		entity = table.getClass();
		fields = entity.getFields();
		tableName = entity.getSimpleName();
		values = new TreeMap<String, String>();
		for (Field field : fields){
			String name = field.getName();
			Object value = field.get(table);
			if(value != null)
				values.put(name, "'"+value.toString()+"'");
		}
	}
	
	protected String columns() {
		String columns = "(";
		for (String column : values.keySet()){
			columns += column+",";
		}
		return removeLastComma(columns)+")";
	}

	protected String values() {
		String vals = "(";
		for (String value : values.values()){
			vals += value+",";
		}
		return removeLastComma(vals)+")";
	}
	
	protected String removeLastComma(String s){
		// Remove last comma
		int i = s.lastIndexOf(',');
		return s.substring(0, i);
	}
	
	public DatabaseOp<T> where(String column, OP operation, String value){
		switch (operation){
		case Equal:
			whereStr = "where "+column+"='"+value+"' ";
			break;
		case LessThan:
			whereStr = "where "+column+"<'"+value+"' ";
			break;
		case GreaterThan:
			break;
		case NotEqual:
			whereStr = "where "+column+">'"+value+"' ";
			break;
		}
		return this;
	}
	
	public DatabaseOp<T> where(String column, OP operation, int value){
		return where(column, operation, Integer.toString(value));
	}
	
	public DatabaseOp<T> and(String column, OP operation, String value){
		switch (operation){
		case Equal:
			whereStr = "and "+column+"="+value+" ";
			break;
		case LessThan:
			whereStr = "and "+column+"<"+value+" ";
			break;
		case GreaterThan:
			break;
		case NotEqual:
			whereStr = "and "+column+">"+value+" ";
			break;
		}
		return this;
	}
	
	public DatabaseOp<T> or(String column, OP operation, String value){
		switch (operation){
		case Equal:
			whereStr = "or "+column+"="+value+" ";
			break;
		case LessThan:
			whereStr = "or "+column+"<"+value+" ";
			break;
		case GreaterThan:
			break;
		case NotEqual:
			whereStr = "or "+column+">"+value+" ";
			break;
		}
		return this;
	}
	
	public abstract QueryResult<T> execute(Statement stmt) throws Exception;
}
