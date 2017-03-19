package taxiClasses;

public class NoMatchingDestination extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMatchingDestination(String id) {
		super("Destinations do not match between journey file & destination file : "+ id);
	}
}
