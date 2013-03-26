package cs5530;

import java.util.Collection;
import java.util.Iterator;

import models.IDbModel;


public class Helper {
	public static <T extends IDbModel> String tablesToString(Collection<T> tables){
		String results = "";
		for (T table : tables)
			results += table.toString();
		return results;
	}
	
	public static <T extends IDbModel> String tablesToString(Collection<T> tables, int count){
		String results = "";
		Iterator<T> it = tables.iterator();
		for (int i = 0; it.hasNext() && i < count; i++){
			T table = it.next();
			results += table.toString();
		}
		return results;
	}
}
