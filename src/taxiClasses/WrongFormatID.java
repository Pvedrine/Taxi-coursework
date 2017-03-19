package taxiClasses;

public class WrongFormatID extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongFormatID(String id) {
		super("ID's format is invalid : "+ id);
	}
}
