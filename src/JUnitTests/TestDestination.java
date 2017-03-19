package JUnitTests;

import static org.junit.Assert.*;

import taxiClasses.*;

import org.junit.Test;

public class TestDestination {

	@Test(expected = IllegalArgumentException.class)
	public void InvalidDistance() {
		@SuppressWarnings("unused")
		Destination  dest = new Destination("Hell", -666);
		
	}
	@Test
	public void InvalidDistance2(){
		try{ 
		@SuppressWarnings("unused")
		Destination dest = new Destination("Sydney", 16863);
		fail("Invalid distance, should throw exception");
		}
		catch (IllegalArgumentException e){
			assertTrue(e.getMessage().contains("Over 1000"));
		}
	}
	

}
