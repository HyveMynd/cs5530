package models;

import java.sql.Date;

public class Feedback implements IDbModel{
	public int cid;
	public long isbn;
	public float score;
	public Date date;
}
