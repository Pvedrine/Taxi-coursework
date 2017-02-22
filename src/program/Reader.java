package program;
import java.io.*;

public class Reader {
	/**
	 * @uml.property  name="year"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	/**
	 * In this property is stored the year we are reffering to
	 */
	private Year year;
	
	/**
	 * @uml.property  name="taxi_file"
	 */
	/**
	 * In this property is stored the name of the file used to get list of taxies
	 */
	private String taxi_file;
	
	/**
	 * @uml.property  name="destinations_file"
	 */
	/**
	 * In this property is stored the name of the file used to get the list of destinations
	 */
	private String destinations_file;
	
	/**
	 * @uml.property  name="journeys_file"
	 */
	/**
	 * In this property is stored the name of the file used to get the list of journeys made so far ths year
	 */
	private String journeys_file;
	
	/**
	 * @uml.property  name="previous_destinations_file"
	 */
	/**
	 * In this property is stored the name of the file used to get the list of destinations visited last year
	 */
	private String previous_destinations_file;
	
	/**
	 * All parameters constructor for the class
	 */
	public Reader(Year year, String taxi_file, String destinations_file, String journeys_file, String previous_destinations_file) {
		this.year = year;
		this.taxi_file = taxi_file;
		this.destinations_file = destinations_file;
		this.journeys_file = journeys_file;
		this.previous_destinations_file = previous_destinations_file;
	}
	
	/**
	 * Accessor for the private property year
	 */
	public Year get_Year() {
		return year;
	}
	
	/**
	 * Mutator for the private property year
	 */
	public void set_Year(Year year) {
		this.year = year;
	}
	
	/**
	 * Accessor for the private property taxi_file
	 */
	public String get_Taxi_file() {
		return taxi_file;
	}
	
	/**
	 * Mutator for the private property taxi_file
	 */
	public void set_Taxi_file(String taxi_file) {
		this.taxi_file = taxi_file;
	}
	
	/**
	 * Accessor for the private property destination_file
	 */
	public String get_Destinations_file() {
		return destinations_file;
	}

	/**
	 * Mutator for the private property destination_file
	 */
	public void set_Destinations_file(String destinations_file) {
		this.destinations_file = destinations_file;
	}
	
	/**
	 * Accessor for the private property journeys_file
	 */
	public String get_Journeys_file() {
		return journeys_file;
	}

	/**
	 * Mutator for the private property journeys_file
	 */
	public void set_Journeys_file(String journeys_file) {
		this.journeys_file = journeys_file;
	}
	
	/**
	 * Accessor for the private property previous_destinations_file
	 */
	public String get_Previous_destinations_file() {
		return previous_destinations_file;
	}

	/**
	 * Mutator for the private property previous_destinations_files
	 */
	public void set_Previous_destinations_file(
			String previous_destinations_file) {
		this.previous_destinations_file = previous_destinations_file;
	}	
	
	/**
	 * Method to load all the taxies
	 */
	public void load_taxies() {
		String data [] = new String[2];
		//We create a buffer
		BufferedReader buff = null;
		try {
			//We try to open the file and read it
        	buff = new BufferedReader(new FileReader(this.taxi_file));
	    	String inputLine = buff.readLine();  //read first line
	    	//We read it line per line 
	    	while(inputLine != null){  
	    		//split line into parts
	    		data  = inputLine.split(";");
	    		//We create an object taxi using the split parts of the line
	    		Taxi t = new Taxi(data[0], data[1]);
	    		this.year.get_Taxies().add(t);
	            //read next line
	            inputLine = buff.readLine();
	            
	        }
        }
		//To catch the IOException, in case the file is missing
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
		//To catch the IOException, in case there is a problem with the file opening or reading
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        finally  {
        	//Whether we got an exception or not, we close the buffer
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
	}
	
	/**
	 * Method to load all the destinations available 
	 */
	public void load_destinations() {
		String data [] = new String[2];
		//We create a buffer
		BufferedReader buff = null;
		try {
			//We try to open the file and read it
        	buff = new BufferedReader(new FileReader(this.destinations_file));
	    	String inputLine = buff.readLine();  //read first line
	    	//We read it line per line 
	    	while(inputLine != null){  
	    		//split line into parts
	    		data  = inputLine.split(";");
	    		float distance = Float.parseFloat(data[1].trim());
	    		//We create an object taxi using the split parts of the line
	    		Destination d = new Destination(data[0], distance);
	    		this.year.get_Destinations().add(d);
	            //read next line
	            inputLine = buff.readLine();
	            
	        }
        }
		//To catch the IOException, in case the file is missing
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
		//To catch the IOException, in case there is a problem with the file opening or reading
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        finally  {
        	//Whether we got an exception or not, we close the buffer
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
	}

	/**
	 * Method to load all the journeys of the year
	 */
	public void load_journeys() {
		String data [] = new String[3];
		//We create a buffer
		BufferedReader buff = null;
		try {
			//We try to open the file and read it
        	buff = new BufferedReader(new FileReader(this.journeys_file));
	    	String inputLine = buff.readLine();  //read first line
	    	//We read it line by line
	    	while(inputLine != null){  
	    		//split line into parts
	    		data  = inputLine.split(";");
	    		//We store the data from the file into objects
	    		Taxi t = this.year.find_Taxi_number(data[0]);
	    		Destination d = this.year.find_Destination_adress(data[1]);
	    		int passengers = Integer.parseInt(data[2].trim());
	    		Journey j = new Journey(t, d, passengers);
	    		this.year.get_Journeys().add(j);
	            //read next line
	            inputLine = buff.readLine();
	            
	        }
        }
		//To catch the IOException, in case the file is missing
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
		//To catch the IOException, in case there is a problem with the file opening or reading
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        finally  {
        	//Whether we got an exception or not, we close the buffer
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
	}
	
	/**
	 * Method to load all the destinations visited during the previous year
	 */
	public void load_previous_destinations() {
		String data = null;
		//We create a buffer
		BufferedReader buff = null;
		try {
			//We try to open the file and read it
        	buff = new BufferedReader(new FileReader(this.get_Previous_destinations_file()));
	    	String inputLine = buff.readLine();  //read first line
	    	//We read line by line
	    	while(inputLine != null){  
	    		data = inputLine;
	    		//We add each input from the file to the list of destinations
	    		this.year.get_Previous_year_destinations().add(data);
	            //read next line
	            inputLine = buff.readLine();
	            
	        }
        }
		//To catch the IOException, in case the file is missing
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
		//To catch the IOException, in case there is a problem with the file opening or reading
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        finally  {
        	//Whether we got an exception or not, we close the buffer
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
	}

	/**
	 * Method to load all the data 
	 */
	public void load() {
		load_taxies();
		load_destinations();
		load_journeys();
		load_previous_destinations();
	}
}