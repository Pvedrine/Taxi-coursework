package taxiClasses;

public class WrongDistanceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongDistanceException(float nb) {
		super("Distance is invalid : "+ nb);
	}
}
