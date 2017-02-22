package program;

public class WrongFormatID extends Exception {
	
	public WrongFormatID(String id) {
		super("ID's format is invalid : "+ id);
	}
}
