package taxiClasses;

public class NoMatchingTaxi extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMatchingTaxi(String id) {
		super("Taxies do not match between journey file & taxi file : "+ id);
	}
}
