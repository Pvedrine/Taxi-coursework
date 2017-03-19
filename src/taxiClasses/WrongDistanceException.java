package taxiClasses;

public class WrongDistanceException extends Exception {
	
	public WrongDistanceException(float nb) {
		super("Distance is invalid : "+ nb);
	}
}
