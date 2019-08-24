package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Customer;
import utils.MathUtils;

public class CustomerRating {
	private static Map<Customer, ArrayList<Integer>> customerMap = new HashMap<Customer, ArrayList<Integer>>();

	public static void setAvgCustomerRating(Customer c, Integer rating) {
		if (!customerMap.containsKey(c)) {
			customerMap.put(c, new ArrayList<Integer>());
		}	
		customerMap.get(c).add(rating);
	}
	
	public static double getAvgCustomerRating(Customer c) {
		if (customerMap.containsKey(c)) {
			return MathUtils.getAvg(customerMap.get(c));
		}
		return 0;
	}
	
}
