package taxiClasses;

public class NoMatchingTaxi extends Exception {
	
	public NoMatchingTaxi(String id) {
		super("Taxies do not match between journey file & taxi file : "+ id);
	}
}
