package service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.CustomerRating;
import db.DriverRating;

public class Trip {
	private Driver driver;
	private Customer customer;
	private int driverRating;
	private int customerRating;
	
	private static Map<String, ArrayList<String>> cusDriverRelation = new HashMap<String, ArrayList<String>>();
	
	public Trip(Driver driver, Customer customer, int driverRating, int customerRating) {
		super();
		this.driver = driver;
		this.customer = customer;
		this.driverRating = driverRating;
		this.customerRating = customerRating;
		setCustomerDriverRelation(customer, driver);
		setCustomerRating(customer, customerRating);
		DriverRating.setAvgDriverRating(driver, driverRating);
	}
	
	private void setCustomerRating(Customer customer, int customerRating) {
		CustomerRating.setAvgCustomerRating(customer, customerRating);
	}
	
	private void setCustomerDriverRelation(Customer c, Driver d) {
		if (!cusDriverRelation.containsKey(c.getName())) {
			cusDriverRelation.put(c.getName(), new ArrayList<String>());
		}	
		cusDriverRelation.get(c.getName()).add(d.getName());
	}
	
	public static List<String> bookCab(Customer c) {
		double cusRating = CustomerRating.getAvgCustomerRating(c);
		Map<String, Double> map = DriverRating.getAllAvgRating();
		List<String> results = new ArrayList<String>();
		for (Map.Entry<String, Double> entry : map.entrySet()) {
			double value = entry.getValue();
			if (value >= cusRating && value >= 1.0)
				results.add(entry.getKey());
		}
		if (results.size() == 0)
			getDriversForCustomer(c, results);
		return results;
	}
	
	private static void getDriversForCustomer(Customer c, List<String> results) {
		List<String> drivers = cusDriverRelation.get(c.getName());
		for (String d : drivers) {
			if (DriverRating.getAvgDriverRating(d) >= 1.0 ) 
				results.add(d);
		}
	}

	
	
}
