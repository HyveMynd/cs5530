package models;

import java.sql.Date;

public class Orders {
	private long isbn;
	private long ccNum;
	private Date orderDate;
	private int cid;
	private double total;
	
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public long getCcNum() {
		return ccNum;
	}
	public void setCcNum(long ccNum) {
		this.ccNum = ccNum;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
