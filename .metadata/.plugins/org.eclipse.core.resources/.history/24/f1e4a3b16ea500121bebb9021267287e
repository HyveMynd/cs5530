package cs5530;

import java.sql.Statement;
import java.util.Collection;

import databaseOps.QueryResult;
import databaseOps.Select;
import dbModels.Video;

public class VideoOperations {
	
	public String allVideos(Statement stmt, Video video) throws Exception{
		String query = "";
		String resultstr="";
		QueryResult<Video> result;
		Collection<Video> list;
		
		try{
			result = Select.from(video).execute(stmt);
			list = result.queryResults;
			//resultSet = result.stmt.getResultSet();
			query = result.sql;
        } catch(Exception e) {
			System.err.println("Unable to execute query: "+ query);
	                System.err.println(e.getMessage());
			throw(e);
		}
		System.out.println("Videos: allVideo query="+query+"\n");
		
		resultstr = Helper.tablesToString(list);
		
		return resultstr;
	}
}
