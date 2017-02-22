package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import program.*;
public class TestJourney {

	
	//Too many passengers 
	@Test (expected = IllegalArgumentException.class)
	public void testBigTaxi() {
		Taxi taxi1 = new Taxi("Freddie Mercury", "HW01 AAA");
		Destination dest = new Destination ("Glasgow", 42);
		Journey journey = new Journey(taxi1, dest, 5);
		
	}
	
	//Taxi not in the list
	@Test (expected = IllegalArgumentException.class)
	public void testFakeTaxi(){
		Destination dest = new Destination ("Glasgow", 42);
		Journey journey = new Journey(new Taxi("Matt Bellamy", "HW12 ABC"), dest, 4 );
		
	}
}
