package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.Customer;
import service.Trip;
import utils.MathUtils;

public class CustomerRating {
	private Trip trip;
	private Customer customer;
	
	private static Map<String, ArrayList<Integer>> customerMap = new HashMap<String, ArrayList<Integer>>();

	public static void setAvgCustomerRating(Customer c, Integer rating) {
		String name = c.getName();
		if (!customerMap.containsKey(name)) {
			customerMap.put(name, new ArrayList<Integer>());
		}	
		customerMap.get(name).add(rating);
	}
	
	public static double getAvgCustomerRating(Customer c) {
		if (customerMap.containsKey(c.getName())) {
			return MathUtils.getAvg(customerMap.get(c.getName()));
		}
		return 0;
	}
	
}
