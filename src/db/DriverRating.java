package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import service.Driver;
import service.Trip;
import utils.MathUtils;

public class DriverRating {
	private Trip trip;
	private Driver driver;
	
	private static Map<String, ArrayList<Integer>> driverMap = new HashMap<String, ArrayList<Integer>>();
	private static Map<String, Double> driverAvgMap = new HashMap<String, Double>();

	public static void setAvgDriverRating(Driver d, Integer rating) {
		String name = d.getName();
		if (!driverMap.containsKey(name)) {
			driverMap.put(name, new ArrayList<Integer>());
		}	
		driverMap.get(name).add(rating);
		getAvgDriverRating(d.getName());
	}
	
	// must be executed in separate thread
	public static double getAvgDriverRating(String d) {
		double avg;
		if (driverMap.containsKey(d)) {
			avg = MathUtils.getAvg(driverMap.get(d));
		} else {
			avg = 0.0;
		}
		driverAvgMap.put(d, avg);
		return avg;
	}
	
	public static Map<String, Double> getAllAvgRating() {
		return driverAvgMap;
	}
	
	
}
