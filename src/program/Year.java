package program;
import java.util.HashSet;
import java.util.ArrayList;

//This class contains all the informations about a working year in the taxi company
public class Year {
	/**
	 * @uml.property  name="year_nb"
	 */
	/**
	 * In this property is stored the current year
	 */
	private int year_nb;
	
	/**
	 * @uml.property  name="taxies"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="program.Taxi"
	 */
	/**
	 * In this property are stored the taxies of the company
	 */
	private HashSet<Taxi> taxies;
	
	/**
	 * @uml.property  name="destinations"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="program.Destination"
	 */
	/**
	 * In this property are stored the destinations visited this year
	 */
	private HashSet<Destination> destinations;
	
	/**
	 * @uml.property  name="journeys"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="program.Journey"
	 */
	/**
	 * In this property are stored all the journeys made by the drivers this year
	 */
	private ArrayList<Journey> journeys;
	
	/**
	 * @uml.property  name="previous_year_destinations"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.lang.String"
	 */
	/**
	 * In this property are stored the destinations visited during the previous year
	 */
	private HashSet<String> previous_year_destinations;
	
	/**
	 * All parameters constructor for the class
	 */
	public Year(int year_nb) {
		set_Year_nb(year_nb);
		set_Taxies(new HashSet<Taxi>());
		set_Destinations(new HashSet<Destination>());
		set_Journeys(new ArrayList<Journey>());
		set_Previous_year_destinations(new HashSet<String>());
	}

	/**
	 * Accessor for the private property year_nb
	 */
	public int get_Year_nb() {
		return year_nb;
	}

	/**
	 * Mutator for the private property year_nb
	 */
	public void set_Year_nb(int year_nb) {
		this.year_nb = year_nb;
	}
	
	/**
	 * Accessor for the private property taxies
	 */
	public HashSet<Taxi> get_Taxies() {
		return taxies;
	}

	/**
	 * Mutator for the private property taxies
	 */
	public void set_Taxies(HashSet<Taxi> taxies) {
		this.taxies = taxies;
	}

	/**
	 * Accessor for the private property journeys
	 */
	public ArrayList<Journey> get_Journeys() {
		return journeys;
	}

	/**
	 * Mutator for the private property journeys
	 */
	public void set_Journeys(ArrayList<Journey> journeys) {
		this.journeys = journeys;
	}

	/**
	 * Accessor for the private property destinations
	 */
	public HashSet<Destination> get_Destinations() {
		return destinations;
	}

	/**
	 * Mutator for the private property destinations
	 */
	public void set_Destinations(HashSet<Destination> destinations) {
		this.destinations = destinations;
	}

	/**
	 * Accessor for the private property previous_year_destinations
	 */
	public HashSet<String> get_Previous_year_destinations() {
		return previous_year_destinations;
	}

	/**
	 * Mutator for the private property previous_year_destinations
	 */
	public void set_Previous_year_destinations(
			HashSet<String> previous_year_destinations) {
		this.previous_year_destinations = previous_year_destinations;
	}
	
	/**
	 * Method to get a taxi using its registration_number
	 */
	public Taxi find_Taxi_number(String registration_number) {
		Taxi result = null;
		//We go through all the taxies to find the one with the registration number given in parameter
		for(Taxi t : this.taxies) {
			if(t.get_Registration_Number().equals(registration_number)) {
				result = t;
			}
		}
		return result;
	}

	/**
	 * Method to get the address of a destination
	 */
	public Destination find_Destination_adress(String adress) {
		Destination result = null;
		for(Destination d : this.destinations) {
			if(d.get_Adress().equals(adress)) {
				result = d;
			}
		}
		return result;
	}
	
}
