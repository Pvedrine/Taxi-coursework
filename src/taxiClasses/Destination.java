package taxiClasses;

//This class contains all the informations about a destination
public class Destination {
	/**
	 * @uml.property  name="address"
	 */
	/**
	 * In this property is stored the address for the destination
	 */
	private String address;
	/**
	 * @uml.property  name="distance"
	 */
	/**
	 * In this property is stored the distance to reach the destination
	 */
	private float distance;
	
	/**
	 * All parameters constructor for the class
	 */
	public Destination(String adress, float distance) {
		this.address = adress;
		this.distance = distance;
	}
	
	/**
	 * Accessor for the private property address
	 */
	public String get_Adress() {
		return this.address;
	}
	
	/**
	 * Mutator for the private property address
	 */
	public void set_Adress(String adress) {
		this.address = adress;
	}
	
	/**
	 * Accessor for the private property distance
	 */
	public float get_Distance() {
		return this.distance;
	}
	
	/**
	 * Mutator for the private property distance
	 */
	public void set_Distance(float distance) {
		this.distance = distance;
	}
	
	/**
	 * Function displaying the properties of the object
	 * @return 
	 */
	public String toString() {
		return "Address: "+this.address+" ; Distance: "+this.distance;
	}
}
