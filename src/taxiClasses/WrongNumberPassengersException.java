package taxiClasses;

public class WrongNumberPassengersException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongNumberPassengersException(int number) {
		super("Number of passengers invalid: "+ number);
	}
}
