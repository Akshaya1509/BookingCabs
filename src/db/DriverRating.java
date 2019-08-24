package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Driver;
import utils.MathUtils;

public class DriverRating {
	private static Map<Driver, ArrayList<Integer>> driverMap = new HashMap<Driver, ArrayList<Integer>>();
	private static Map<Driver, Double> driverAvgMap = new HashMap<Driver, Double>();

	public static void setAvgDriverRating(Driver d, Integer rating) {
		if (!driverMap.containsKey(d)) {
			driverMap.put(d, new ArrayList<Integer>());
		}	
		driverMap.get(d).add(rating);
		getAvgDriverRating(d);
	}
	
	// must be executed in separate thread
	public static double getAvgDriverRating(Driver d) {
		double avg;
		if (driverMap.containsKey(d)) {
			avg = MathUtils.getAvg(driverMap.get(d));
		} else {
			avg = 0.0;
		}
		driverAvgMap.put(d, avg);
		return avg;
	}
	
	public static Map<Driver, Double> getAllAvgRating() {
		return driverAvgMap;
	}
	
	
}
