package thchmain;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import oop.User;

public class Main2Methods {
	
	public static void main (String [] args) throws Exception {
		
		// files
		String file_equity = "equity_value_data.csv";
		String file_features = "features_data.csv";
		
		// load data from files
		List<User> l = Methods.loadUserData(file_features);
		Map<String, TreeMap<String, Double>>m = Methods.loadPossibleChurnedUser(file_equity);
        
		// compute number of churned users
		double churned = m.size();
		System.out.println("Number of churned users is "+churned);
		System.out.println("Given that the total number of users is "+l.size()+" the problem is higly unbalanced");
        double tot_users = l.size();
		double prc_churned = churned/tot_users*100;
		System.out.println("To date, the percentage of churned users is "+prc_churned+ " %");
		
		// create flat file
		Methods.createFlatFile("FlatFile.csv",l, m);
		System.out.println("Flat file created with one additional column of 0 and 1 based on user if it is churned or not. ");
		
		// in order to find ​the​ ​most​ ​important​ ​features​ ​that​ ​correlate​ ​to​ ​user​ ​churn
		// a matrix is builded by substituting all the features like with numbers from 1 to 7 with a step of 2 
		// as follows
        // no_investment_exp = 1);
        // limited_investment_exp = 3);
        // good_investment_exp = 5);
        // extensive_investment_exp = 7);
		
		List<String> matrix = Methods.buildMatrixFromFlatFile("FlatFile.csv");
		// I consider relevant only the following features risk_tolerance,investment_experience,liquidity_needs,time_spent,first_deposit_amount,time_horizon,churned_01
		Methods.print("FlatFileAsMatrixForChurnedUsers.csv",matrix);
		System.out.print("Matrix printed !");
		
		// from this point I will work in Python as for extracting the most important features that
		// correlates to user churn it is easier.
		System.out.println("Now that I have the matrix, I'm going to work in Python as it is easier.");
		
	}

	
}
