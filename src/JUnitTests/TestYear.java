package JUnitTests;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import taxiClasses.Taxi;
import taxiClasses.Year;

public class TestYear {

	@Test (expected = IllegalArgumentException.class)
	public void sameName(){
		Year y = new Year(2015);
		Taxi taxi = new Taxi("Jean-Jacques Goldman", "HW125 AEO");
		y.get_Taxies().add(taxi);
		Taxi taxi1 = new Taxi("Jean-Jacques Goldman", "HW126 OEA");
		y.get_Taxies().add(taxi1);
	}
	@Test (expected = IllegalArgumentException.class)
	public void sameRN(){
		Year y = new Year(2015);
		Taxi taxi = new Taxi("Emile", "HW12 IMG");
		y.get_Taxies().add(taxi);
		Taxi taxi1 = new Taxi("Image", "HW12 IMG");
		y.get_Taxies().add(taxi1);
	}
	
	@Test 
	public void testGetSetTaxies(){
		Year y = new Year(2015);
		HashSet<Taxi> s = new HashSet<Taxi>();
		Taxi taxi = new Taxi("Laurent Voulzy", "HW17 HGE");
		Taxi taxi1 = new Taxi("Alain Souchon", "HW65 JZJE");
		s.add(taxi);
		s.add(taxi1);
		y.set_Taxies(s);
		HashSet<Taxi> actual = y.get_Taxies();
		assertEquals("wrong set", s, actual);
		
	}

}
