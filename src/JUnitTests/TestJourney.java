package JUnitTests;

import org.junit.Test;
import taxiClasses.*;
public class TestJourney {

	
	//Too many passengers 
	@Test (expected = WrongNumberPassengersException.class)
	public void testBigTaxi() {
		Taxi taxi1 = new Taxi("Freddie Mercury", "HW01 AAA");
		Destination dest = new Destination ("Glasgow", 42);
		try {
			@SuppressWarnings("unused")
			Journey journey = new Journey(taxi1, dest, 5);
		} catch (WrongNumberPassengersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Taxi not in the list
	@Test (expected = WrongFormatID.class)
	public void testFakeTaxi(){
		Destination dest = new Destination ("Glasgow", 42);
		try {
			@SuppressWarnings("unused")
			Journey journey = new Journey(new Taxi("Matt Bellamy", "HW12 ABC"), dest, 4 );
		} catch (WrongNumberPassengersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
