package taxiClasses;

//This class contains all the informations about a journey
public class Journey {
	/**
	 * @uml.property  name="driver"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	/**
	 * In this property is stored Taxi doing the journey 
	 */
	private Taxi driver;
	
	/**
	 * @uml.property  name="place"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	/**
	 * In this property is stored the destination to reach
	 */
	private Destination place;
	
	/**
	 * @uml.property  name="passengers"
	 */
	/**
	 * In this property is stored the number of passengers during the journey
	 */
	private int passengers;
	
	/**
	 * @uml.property  name="cost"
	 */
	/**
	 * In this property is stored the price of the journey
	 */
	private float cost;
	
	/**
	 * All parameters constructor for the class
	 */
	public Journey(Taxi driver, Destination place, int passengers) {
		this.driver = driver;
		this.place = place;
		this.passengers = passengers;
		//We try to calculate the price
		try {
			calculate();
		}
		//In case we cannot and the try throws an exception
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Accessor for the private property driver
	 */
	public Taxi get_Driver() {
		return this.driver;
	}

	/**
	 * Mutator for the private property driver
	 */
	public void set_Driver(Taxi driver) {
		this.driver = driver;
	}
	
	/**
	 * Accessor for the private property place
	 */
	public Destination get_Place() {
		return this.place;
	}
	
	/**
	 * Mutator for the private property place
	 */
	public void set_Place(Destination place) {
		this.place = place;
	}
	
	/**
	 * Accessor for the private property passengers
	 */
	public int get_Passengers() {
		return this.passengers;
	}
	
	/**
	 * Mutator for the private property passengers
	 */
	public void set_Passengers(int passengers) {
		this.passengers = passengers;
	}
	
	/**
	 * Accessor for the private property cost
	 */
	public float get_Cost() {
		return this.cost;
	}
	
	/**
	 * Mutator for the private property cost
	 */
	public void set_Cost(float cost) {
		this.cost = cost;
	}
	
	/**
	 * Function to calculate the price based on the number of passengers and the distance
	 * The function is only called in the constructor, so it can be private
	 */
	private void calculate() throws Exception {
		double coefficient = 1;
		//The number of passengers modifies the coefficient, thus the price
		switch (this.passengers) {
			case 1:
				coefficient = 1;
				break;
			case 2:
				coefficient = 1.25;
				break;
			case 3:
				coefficient = 1.5;
				break;
			case 4:
				coefficient = 1.75;
				break;
			default:
				//If there are more than 4 passengers, we throw an exception
				//The car is too small for allow them all to fit
				System.out.println("Exception to handle");
				throw new Exception("To implement");
		}
		// Then, we update the cost
		this.cost = (float) coefficient * this.place.get_Distance();
	}
	
	/**
	 * Function displaying the properties of the object
	 */
	public void show_Properties() {
		System.out.println("Taxi: "+this.driver.get_Registration_Number()+" ; Destination: "+this.place.get_Adress()+" ; Passengers: "+this.passengers+" ; Cost: "+this.cost);
	}
	
	public String toString(){
		return "Taxi: "+this.driver.toString()+" ; Destination: "+this.place.toString()+" ; Passengers: "+this.passengers+" ; Cost: "+this.cost;
	}
}

