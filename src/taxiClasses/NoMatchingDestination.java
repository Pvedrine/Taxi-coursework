package taxiClasses;

public class NoMatchingDestination extends Exception {

	public NoMatchingDestination(String id) {
		super("Destinations do not match between journey file & destination file : "+ id);
	}
}
