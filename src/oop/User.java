package oop;

public class User {
	String risk_tolerance;
	String investment_experience;
	String liquidity_needs;
	String platform;
	double time_spent;
	String instrument_type_first_traded;
	double first_deposit_amount;
	
	public String getRisk_tolerance() {
		return risk_tolerance;
	}

	public void setRisk_tolerance(String risk_tolerance) {
		this.risk_tolerance = risk_tolerance;
	}

	public String getInvestment_experience() {
		return investment_experience;
	}

	public void setInvestment_experience(String investment_experience) {
		this.investment_experience = investment_experience;
	}

	public String getLiquidity_needs() {
		return liquidity_needs;
	}

	public void setLiquidity_needs(String liquidity_needs) {
		this.liquidity_needs = liquidity_needs;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public double getTime_spent() {
		return time_spent;
	}

	public void setTime_spent(double time_spent) {
		this.time_spent = time_spent;
	}

	public String getInstrument_type_first_traded() {
		return instrument_type_first_traded;
	}

	public void setInstrument_type_first_traded(String instrument_type_first_traded) {
		this.instrument_type_first_traded = instrument_type_first_traded;
	}

	public double getFirst_deposit_amount() {
		return first_deposit_amount;
	}

	public void setFirst_deposit_amount(double first_deposit_amount) {
		this.first_deposit_amount = first_deposit_amount;
	}

	public String getTime_horizon() {
		return time_horizon;
	}

	public void setTime_horizon(String time_horizon) {
		this.time_horizon = time_horizon;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	String time_horizon;
	String user_id;
	
	public User(String r, String i, String l, String p, double t, String ins_tr, double f, String time_h, String id) {
		this.risk_tolerance = r; 
		this.investment_experience = i; 
		this.liquidity_needs = l; 
		this.platform = p; 
		this.time_spent = t; 
		this.instrument_type_first_traded = ins_tr; 
		this.first_deposit_amount = f; 
		this.time_horizon = time_h; 
		this.user_id = id;
	}

	public String toString() {
		return risk_tolerance+","+investment_experience+","+liquidity_needs+","
				+ ""+platform+","+time_spent+","+instrument_type_first_traded+","+first_deposit_amount+","
						+ ""+time_horizon+","+user_id;
	}

}
