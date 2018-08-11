package oop;

public class Trx {
	
	String user_id;
	String timestamp;
	double close_equity;
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getClose_equity() {
		return close_equity;
	}

	public void setClose_equity(double close_equity) {
		this.close_equity = close_equity;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	 
	public Trx(String t, double c, String u){
		this.timestamp = t; 
		this.close_equity = c; 
		this.user_id = u;	
	}
	
}
