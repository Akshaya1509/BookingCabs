package application;

import java.util.List;

import db.CustomerRating;
import service.Customer;
import service.Driver;
import service.Trip;

public class Application {

	public static void main(String[] args) {
		// avg cus rating
		Customer amar = new Customer("Amar");
		Customer akbar = new Customer("Akbar");
		Customer anthony = new Customer("Anthony");
		
		Driver raghu = new Driver("Raghu");
		Driver ram = new Driver("Ram");
		Driver rajan = new Driver("Rajan");
		
		Trip trip1 = new Trip(raghu, amar, 4, 5);
		Trip trip2 = new Trip(ram, amar, 5, 1);
		Trip trip3 = new Trip(rajan, amar, 3, 2);
		new Trip(raghu, akbar , 5,4);
		new Trip(ram, amar, 5, 1);
		new Trip(ram, akbar, 5, 5);
		new Trip(ram, anthony, 4, 5);
		new Trip(rajan, akbar, 4, 5);
		new Trip(rajan, anthony, 3, 3);
		
		System.out.println(CustomerRating.getAvgCustomerRating(amar));
		
//		Trip trip1 = new Trip(new Driver("Raghu"), amar, 2, 5);
//		Trip trip2 = new Trip(new Driver("Ram"), amar, 0, 1);
//		Trip trip3 = new Trip(new Driver("Rajan"), amar, 0, 2);
//		System.out.println(CustomerRating.getAvgCustomerRating(amar));
		
		// assign drivers
		List<Driver> drivers = Trip.bookCab(amar);
		for(Driver d : drivers) {
			System.out.println(d.getName());
		}
		
	}

}
