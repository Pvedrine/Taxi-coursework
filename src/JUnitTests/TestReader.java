package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import program.*;
public class TestReader {

	@Test
	public void fileNotFound() {
		Year y_2015 = new Year(2015);
		Reader reader = new Reader(y_2015, "Taxi.txt", "Destination.txt", "WrongFile.txt", "Previous_destinations.txt");
		reader.load();
	}

}
