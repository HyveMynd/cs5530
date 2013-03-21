package databaseOps;

import java.sql.ResultSet;
import java.util.Collection;

import models.IDbModel;


public class QueryResult<T extends IDbModel> {
	public String sql = "";
	public Collection<T> queryResults;
	public ResultSet autoGeneratedKeys;
}