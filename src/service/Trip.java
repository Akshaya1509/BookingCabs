package service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.CustomerRating;
import db.DriverRating;
import model.Customer;
import model.Driver;

public class Trip {
	private Driver driver;
	private Customer customer;
	private int driverRating;
	private int customerRating;
	
	private static Map<Customer, ArrayList<Driver>> cusDriverRelation = new HashMap<Customer, ArrayList<Driver>>();
	
	public Trip(Driver driver, Customer customer, int driverRating, int customerRating) {
		super();
		this.driver = driver;
		this.customer = customer;
		this.driverRating = driverRating;
		this.customerRating = customerRating;
		setCustomerDriverRelation(customer, driver);
		CustomerRating.setAvgCustomerRating(customer, customerRating);
		DriverRating.setAvgDriverRating(driver, driverRating);
	}
	
	private void setCustomerDriverRelation(Customer c, Driver d) {
		if (!cusDriverRelation.containsKey(c)) {
			cusDriverRelation.put(c, new ArrayList<Driver>());
		}	
		cusDriverRelation.get(c).add(d);
	}
	
	public static List<Driver> bookCab(Customer c) {
		double cusRating = CustomerRating.getAvgCustomerRating(c);
		List<Driver> results = new ArrayList<Driver>();
		if (cusRating == 0)
			return results;
		Map<Driver, Double> driverAvgRating = DriverRating.getAllAvgRating();
		for (Map.Entry<Driver, Double> entry : driverAvgRating.entrySet()) {
			double value = entry.getValue();
			if (value >= cusRating && value > 1.0)
				results.add(entry.getKey());
		}
		if (results.size() == 0)
			getDriversForCustomer(c, results);
		return results;
	}
	
	private static void getDriversForCustomer(Customer c, List<Driver> results) {
		List<Driver> drivers = cusDriverRelation.get(c);
		for (Driver d : drivers) {
			if (DriverRating.getAvgDriverRating(d) > 1.0 ) 
				results.add(d);
		}
	}

	
}
