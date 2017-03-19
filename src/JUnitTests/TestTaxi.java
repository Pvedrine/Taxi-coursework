package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import taxiClasses.*;
public class TestTaxi {

	@Test
	public void testgetRN() {
		String expected = "HW01 AAA" ;
		Taxi taxi = new Taxi("Freddie Mercury", "HW01 AAA");
		String actual = taxi.get_Registration_Number();
		assertEquals("Not the same RN", expected, actual);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testRN(){
		Taxi taxi = new Taxi("Daniel Balavoine", "HE L1 C0pter");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void rnNoSpace(){
		Taxi taxi = new Taxi("Johnny Hallyday", "HW41 FEU");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLengthRN(){
		Taxi taxi = new Taxi("Joe Dassin", "HW55 LETEINDIEN");
	}
}
