package taxiClasses;
import java.util.ArrayList;

//This class contains all the informations about a Taxi
public class Taxi {
	/**
	 * @uml.property  name="registration_number"
	 * @uml.associationEnd  
	 */
	/**
	 * In this property is stored the registration number of the taxi
	 */
	private String registration_number;
	
	/**
	 * @uml.property  name="name"
	 */
	/**
	 * In this property is stored the taxi driver's name
	 */
	private String name;
	
	/**
	 * @uml.property  name="places_visited"
	 */
	/**
	 * In this property are stored all the places the driver has been so far
	 */
	private ArrayList <Destination> places_visited;

	/**
	 * All parameters constructor for the class
	 */
	public Taxi(String registration_number, String name) {
		this.registration_number = registration_number;
		this.name = name;
		this.set_Places_visited(new ArrayList<Destination>());
	}
	
	/**
	 * Accessor for the private property registration_number
	 */
	public String get_Registration_Number() {
		return this.registration_number;
	}
	
	/**
	 * Mutator for the private property registration_number
	 */
	public void set_Registration_Number(String registration_number) {
		this.registration_number = registration_number;
	}
	
	/**
	 * Accessor for the private property name
	 */
	public String get_Name() {
		return this.name;
	}
	
	/**
	 * Mutator for the private property name
	 */
	public void set_Name(String name) {
		this.name = name;
	}

	/**
	 * Accessor for the private property places_visited
	 */
	public ArrayList <Destination> get_Places_visited() {
		return places_visited;
	}

	/**
	 * Mutator for the private property registration_number
	 */
	public void set_Places_visited(ArrayList <Destination> places_visited) {
		this.places_visited = places_visited;
	}
	
	/**
	 * Function displaying the properties of the object
	 */
	public void show_Properties() {
		System.out.println("Reg.number: "+this.registration_number+" ; Name: "+this.name);
	}
	
	public String toString(){
		return "Reg number: "+this.registration_number+" ; Name: "+this.name;
	}
}

