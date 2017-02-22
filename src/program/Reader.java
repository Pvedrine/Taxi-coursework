package program;
import java.io.*;

public class Reader {
	/**
	 * @uml.property  name="year"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Year year;
	/**
	 * @uml.property  name="taxi_file"
	 */
	private String taxi_file;
	/**
	 * @uml.property  name="destinations_file"
	 */
	private String destinations_file;
	/**
	 * @uml.property  name="journeys_file"
	 */
	private String journeys_file;
	/**
	 * @uml.property  name="previous_destinations_file"
	 */
	private String previous_destinations_file;
	
	public Reader(Year year, String taxi_file, String destinations_file, String journeys_file, String previous_destinations_file) {
		this.year = year;
		this.taxi_file = taxi_file;
		this.destinations_file = destinations_file;
		this.journeys_file = journeys_file;
		this.previous_destinations_file = previous_destinations_file;
	}
	
	public Year get_Year() {
		return year;
	}
	public void set_Year(Year year) {
		this.year = year;
	}
	public String get_Taxi_file() {
		return taxi_file;
	}
	public void set_Taxi_file(String taxi_file) {
		this.taxi_file = taxi_file;
	}
	
	public String get_Destinations_file() {
		return destinations_file;
	}

	public void set_Destinations_file(String destinations_file) {
		this.destinations_file = destinations_file;
	}
	
	public String get_Journeys_file() {
		return journeys_file;
	}

	public void set_Journeys_file(String journeys_file) {
		this.journeys_file = journeys_file;
	}
	
	public String get_Previous_destinations_file() {
		return previous_destinations_file;
	}

	public void set_Previous_destinations_file(
			String previous_destinations_file) {
		this.previous_destinations_file = previous_destinations_file;
	}	
	
	public void load_taxies() throws WrongFormatID {
		String data [] = new String[2];
		BufferedReader buff = null;
		try {
        	buff = new BufferedReader(new FileReader(this.taxi_file));
	    	String inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		//split line into parts
	    		data  = inputLine.split(";");
	    		if((!(data[0].matches("HW[0-9]{2} .*"))||(data[0].length()>8))) {
	    			throw new WrongFormatID(data[0]);
	    		}
	    		else {
	    			Taxi t = new Taxi(data[0], data[1]);
		    		this.year.get_Taxies().add(t);
		            //read next line
		            inputLine = buff.readLine();
	    		}  
	        }
        }
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        finally  {
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
	}
	
	public void load_destinations() {
		String data [] = new String[2];
		BufferedReader buff = null;
		try {
        	buff = new BufferedReader(new FileReader(this.destinations_file));
	    	String inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		//split line into parts
	    		data  = inputLine.split(";");
	    		float distance = Float.parseFloat(data[1].trim());
	    		Destination d = new Destination(data[0], distance);
	    		this.year.get_Destinations().add(d);
	            //read next line
	            inputLine = buff.readLine();
	            
	        }
        }
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        finally  {
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
	}

	public void load_journeys() throws WrongDistanceException, NoMatchingTaxi, NoMatchingDestination {
		String data [] = new String[3];
		BufferedReader buff = null;
		try {
        	buff = new BufferedReader(new FileReader(this.journeys_file));
	    	String inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		//split line into parts
	    		data  = inputLine.split(";");
	    		Taxi t = this.year.find_Taxi_number(data[0]);
	    		Destination d = this.year.find_Destination_adress(data[1]);
	    		if(d.get_Distance()<0 || d.get_Distance() > 1000) {
	    			throw new WrongDistanceException(d.get_Distance());
	    		}
	    		int passengers = Integer.parseInt(data[2].trim());
	    		Journey j = new Journey(t, d, passengers);
	    		this.year.get_Journeys().add(j);
	            //read next line
	            inputLine = buff.readLine();
	            
	        }
        }
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        finally  {
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
	}
	
	public void load_previous_destinations() {
		String data = null;
		BufferedReader buff = null;
		try {
        	buff = new BufferedReader(new FileReader(this.get_Previous_destinations_file()));
	    	String inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		data = inputLine;
	    		this.year.get_Previous_year_destinations().add(data);
	            //read next line
	            inputLine = buff.readLine();
	            
	        }
        }
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        finally  {
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
	}

	public void load() throws WrongDistanceException, WrongFormatID, NoMatchingTaxi, NoMatchingDestination {
		load_taxies();
		load_destinations();
		load_journeys();
		load_previous_destinations();
	}
}