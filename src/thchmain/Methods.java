package thchmain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import oop.User;

public class Methods {
	
	public static void createFlatFile(String flatfile,List<User>u, 
			                          Map<String, TreeMap<String, Double>>m) throws Exception{
		
		List<String>final_users= new ArrayList<String>();
		int churned = 1;
		int not_churned = 0;
		for(int i = 0; i < u.size(); i++) {
			
			String user_id = u.get(i).getUser_id();
			
			if(m.containsKey(user_id)) {
				String user = u.get(i).toString();
				final_users.add(user+","+churned);
			}
			else {
				String user = u.get(i).toString();
				final_users.add(user+","+not_churned);
			}
		}
		PrintWriter out = new PrintWriter(new FileWriter(new File(flatfile)));
		out.println("risk_tolerance,investment_experience,liquidity_needs,platform,time_spent,instrument_type_first_traded,first_deposit_amount,time_horizon,user_id,churned_01");
		for(int i = 0; i < final_users.size(); i++) {
            out.println(final_users.get(i).toString());			
		}
		out.close();
	}
	
	public static List<String> buildMatrixFromFlatFile(String file) throws Exception{
		List<String>ret = new ArrayList<String>();
		// mapping all the possibilities with numbers from 1 to 7
		Map<String, Integer>m = new HashMap<String, Integer>();
		m.put("high_risk_tolerance", 5);
		m.put("med_risk_tolerance", 3);
		m.put("low_risk_tolerance", 1);
		
		m.put("no_investment_exp", 1);
		m.put("limited_investment_exp", 3);
		m.put("good_investment_exp", 5);
		m.put("extensive_investment_exp", 7);
		
		m.put("not_important_liq_need", 1);
		m.put("somewhat_important_liq_need", 3);
		m.put("very_important_liq_need", 5);
		
		m.put("short_time_horizon", 1);
		m.put("med_time_horizon", 3);
		m.put("long_time_horizon", 5);
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		br.readLine();
		String line;
		while((line = br.readLine())!= null) {
			String [] r = line.split(",");
			
			String risk_tolerance = r[0];
			int num_r = m.get(risk_tolerance);
			String investment_experience = r[1];
			//System.out.println(investment_experience);
			int num_i = m.get(investment_experience);
			String liquidity_needs = r[2];
			int num_l = m.get(liquidity_needs);
			String platform= r[3];
			double time_spent = Double.parseDouble(r[4]);
			String instrument_type_first_traded = r[5];
			double first_deposit_amount = Double.parseDouble(r[6]);
			String time_horizon = r[7];
			int time = m.get(time_horizon);
			String user_id = r[8];
			int churned = Integer.parseInt(r[9]);
			String result = num_r+","+num_i+","+num_l+","+time_spent+","+first_deposit_amount+","
					+ ""+time+","+churned;
		    if(churned == 1) {
			  ret.add(result);
		    }
		}
		br.close();
		
		return ret;
	}
	public static void print(String file, List<String>li) throws Exception {
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
		out.println("risk_tolerance,investment_experience,liquidity_needs,time_spent,first_deposit_amount,time_horizon,churned_01");
		for(int i = 0; i < li.size(); i++) {
            out.println(li.get(i).toString());			
		}
		out.close();
	}
	public static List<User> loadUserData( String file) throws Exception{
		List<User>list  = new ArrayList<User>();
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line;
		
		String risk_tolerance;
		String investment_experience;
		String liquidity_needs;
		String platform;
		double time_spent;
		String instrument_type_first_traded;
		double first_deposit_amount;
		String time_horizon;
		String user_id;
		br.readLine();
		while((line = br.readLine())!= null) {
			String [] r = line.split(",");
			
				 risk_tolerance = r[0];
				 investment_experience = r[1];
				 liquidity_needs = r[2];
				 platform = r[3];
				 time_spent = Double.parseDouble(r[4]);
				 instrument_type_first_traded = r[5];
				 first_deposit_amount = Double.parseDouble(r[6]);
				 time_horizon = r[7];
				 user_id = r[8];
				 
				 list.add(new User(risk_tolerance, investment_experience, liquidity_needs,
						 platform,time_spent,instrument_type_first_traded,first_deposit_amount,
						 time_horizon,user_id));
			
		}
		br.close();
		return list;
	}

	public static Map<String, Set<String>> loadTrxDataPerUser(String file) throws Exception {
		Map<String, Set<String>>m = new HashMap<String, Set<String>>();
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line; 
		String timestamp;
		double close_equity;
		String user_id;
		br.readLine();
		
		while((line = br.readLine())!= null) {
			
			String [] r = line.split(",");
			for(int i = 0; i < r.length; i++) {
				 timestamp = r[0].substring(0, r[0].indexOf("T"));
				 close_equity = Double.parseDouble(r[1]);
				 user_id = r[2];
					 Set<String>li = m.get(user_id);
					 if(li == null) {
						 li = new HashSet<String>();
						 li.add(timestamp+" "+close_equity);
						 m.put(user_id, li);
					 }
					 else {
						 li.add(timestamp+" "+close_equity);
					 }
				 
			}
            
		}
		br.close();
		return m;
	}
	public static Map<String, TreeMap<String, Double>> loadPossibleChurnedUser(String file) throws Exception {
		Map<String, TreeMap<String, Double>>m = new HashMap<String, TreeMap<String, Double>>();
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line; 
		String timestamp;
		double close_equity;
		String user_id;
		br.readLine();
		
		while((line = br.readLine())!= null) {
			
			String [] r = line.split(",");
			for(int i = 0; i < r.length; i++) {
				 timestamp = r[0].substring(0, r[0].indexOf("T"));
				 close_equity = Double.parseDouble(r[1]);
				 if(close_equity <= 10 && close_equity >= 10) {
					 user_id = r[2];
					 TreeMap<String, Double>li = m.get(user_id);
					 if(li == null) {
						 li = new TreeMap<String, Double>();
						 li.put(timestamp,close_equity);
						 m.put(user_id, li);
					 }
					 else {
						 li.put(timestamp,close_equity);
					 }
				 }
			}
            
		}
		br.close();
		return m;
	}
}
