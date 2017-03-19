package stage1;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.*;
import taxiClasses.*;

public class Writer {
	/**
	 * @uml.property  name="year"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	/**
	 * In this property is stored the current year
	 */
	private Year year;
	
	/**
	 * @uml.property  name="task1_file"
	 */
	/**
	 * In this property is stored the file to save the task 1
	 */
	private String task1_file;
	
	/**
	 * @uml.property  name="task2_file"
	 */
	/**
	 * In this property is stored the file to save the task 2
	 */
	private String task2_file;
	
	/**
	 * @uml.property  name="task3_file"
	 */
	/**
	 * In this property is stored the file to save the task 3
	 */
	private String task3_file;
	
	/**
	 * All parameters constructor for the class
	 */
	public Writer(Year year, String task1_file, String task2_file, String task3_file) {
		this.year = year;
		this.task1_file = task1_file;
		this.task2_file = task2_file;
		this.task3_file = task3_file;
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
	 * Accessor for the private property task1_file
	 */
	public String get_Task1_file() {
		return task1_file;
	}
	
	/**
	 * Mutator for the private property task2_file
	 */
	public void set_Task1_file(String task1_file) {
		this.task1_file = task1_file;
	}
	
	/**
	 * Accessor for the private property task2_file
	 */
	public String get_Task2_file() {
		return task2_file;
	}
	
	/**
	 * Mutator for the private property task2_file
	 */
	public void set_Task2_file(String task2_file) {
		this.task2_file = task2_file;
	}
	
	/**
	 * Accessor for the private property task3_file
	 */
	public String get_Task3_file() {
		return task3_file;
	}
	
	/**
	 * Mutator for the private property task3_file
	 */
	public void set_Task3_file(String task3_file) {
		this.task3_file = task3_file;
	}
	
	/**
	 * Method to get the task1 done and saved into a file
	 */
	public void task1() {
		//First, we retrieve all the journeys made this year
		//and store them into an array
		ArrayList<Journey> journeys = this.year.get_Journeys();
		
		//We order them by their cost
		//Could be simplified using Comparable Interface on Journey and calling sort method?
		for(int i=0; i<journeys.size(); i++) {
			Journey max = journeys.get(i);
			int index_max=0;
			for(int j=i; j<journeys.size();j++) {
				if(journeys.get(j).get_Cost() > max.get_Cost()) {
					max = journeys.get(j);
					index_max=j;
				}
			}
			Journey tmp = journeys.get(i);
			journeys.set(i, max);
			if(index_max != 0) {
				journeys.set(index_max, tmp);
			}
		}
		
		//We create a buffer to write onto the file
		BufferedWriter writer = null;
		//We try to write
		try {
			//Opens the file
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(this.task1_file), "utf-8"));
		    //Some text to fancy better the results
		    writer.write("CHARGES FOR THE TOP 5 JOURNEYS\n\r");
		    //Then we print the 5 most expensive journeys
		    for(int i=0; i<5; i++) {
		    	writer.write(journeys.get(i).get_Driver().get_Registration_Number()+"  "+
		    					journeys.get(i).get_Place().get_Adress()+"     "+
		    						journeys.get(i).get_Place().get_Distance()+" miles  "+
		    							journeys.get(i).get_Passengers()+" person(s)  "+
		    								"Cost :£"+journeys.get(i).get_Cost()+"\n");
		    }
		  //Some text to fancy better the results
		  //Then we print the 5 cheapest journeys
		    writer.write("\nCHARGES FOR THE CHEAPEST 5 JOURNEYS\n");
		    for(int i=journeys.size()-1; i>journeys.size()-6; i--) {
		    	writer.write(journeys.get(i).get_Driver().get_Registration_Number()+"  "+
		    					journeys.get(i).get_Place().get_Adress()+"     "+
		    						journeys.get(i).get_Place().get_Distance()+" miles  "+
		    							journeys.get(i).get_Passengers()+" person(s)  "+
		    								"Cost :£"+journeys.get(i).get_Cost()+"\n");
		    }
		    writer.close();
		} 
		//To catch the IOException, in case there is a problem with the file opening
		catch (IOException ex) {
			//We print what the error is
			ex.printStackTrace();
			System.exit(1); 
		}
		//Print information message to confirm good execution of task 1
		System.out.println("Task #1 printed in: "+this.task1_file);
	}
	
	/**
	 * Method to get the task2 done and saved into a file
	 */
	public void task2() {
		ArrayList<Journey> journeys = this.year.get_Journeys();
		int i=0;
		while(i<journeys.size()) {
			int index_name;
			int j=0;
			while(j<journeys.size()) {
				Journey name = journeys.get(i);
				if(journeys.get(j).get_Driver().equals(name.get_Driver()) && journeys.get(j)!=name) {
					name = journeys.get(j);
					index_name=j;
					Journey tmp = journeys.get(i+1);
					journeys.set(i+1, name);
					if(index_name != 0) {
						journeys.set(index_name, tmp);
						i++;
					}
				}
				j++;
			}
			i++;
		}
		i=0;
		ArrayList<Taxi> taxies = new ArrayList<Taxi>();
		for(Journey j : journeys) {
			if(!taxies.contains(j.get_Driver())) {
				taxies.add(j.get_Driver());
			}
		}
		ArrayList<ArrayList<String>> dest_per_name = new ArrayList<ArrayList<String>>();
		ArrayList<String> names = new ArrayList<String>();
		String reg_number = journeys.get(i).get_Driver().get_Registration_Number();
		for(i=0;i<journeys.size();i++) {
			if(journeys.get(i).get_Driver().get_Registration_Number().equals(reg_number)) {
				if(!(names.contains(journeys.get(i).get_Place().get_Adress()))) {
					names.add(journeys.get(i).get_Place().get_Adress());
					if(i+1==journeys.size()) {
						dest_per_name.add(names);
					}
				}
			}
			else {
				dest_per_name.add(names);
				names = new ArrayList<String>();
				reg_number = journeys.get(i).get_Driver().get_Registration_Number();
				names.add(journeys.get(i).get_Place().get_Adress());
			}
		}
		
		//Creates a buffer to write onto the file
		BufferedWriter writer = null;
		try {
			//We open the file
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(this.task2_file), "utf-8"));
		    //For all the taxies
		    for(i=0; i<taxies.size(); i++) {
		    	//We write their name
		    	writer.write(taxies.get(i).get_Name()+"\n");
		    	//And the places they visited
		    	for(String s : dest_per_name.get(i)) {
		    		writer.write("   "+s+"\n");
		    	}
		    	writer.write("\n");
		    }
		    //We close the file
		    writer.close();
		} 
		//To catch the IOException, in case there is a problem with the file opening
		catch (IOException ex) {
			//We print what the error is
			ex.printStackTrace();
			System.exit(1); 
		}
		//Print information message to confirm good execution of task 2
		System.out.println("Task #2 printed in: "+this.task2_file);
	}
	
	/**
	 * Method to get the task3 done and saved into a file
	 */
	public void task3() {
		//We get all the journeys of the current year
		ArrayList<Journey> journeys = this.year.get_Journeys();
		//And the destinations from the previous year
		HashSet<String> previous = this.year.get_Previous_year_destinations();
		//From the journeys of the current year we extract the destinations
		HashSet<String> current = new HashSet<String>();
		for(Journey j : journeys) {
			if(!current.contains(j.get_Place().get_Adress())) {
				current.add(j.get_Place().get_Adress());
			}
		}
		
		//We create the sets with the destinations in both sets, or either in one or the other
		//So we get 3 new sets
		HashSet<String> only_previous = this.keep_only_previous(previous, current);
		HashSet<String> only_new = this.keep_new(previous, current);
		HashSet<String> both = this.keep_both(previous, current);
		
		//We crate a buffer to write data onto the file
		int year_nb = this.get_Year().get_Year_nb();
		BufferedWriter writer = null;
		try {
			//We print all the places visited only this year
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(this.task3_file), "utf-8"));
		    writer.write(only_new.size()+" NEW PLACES IN "+year_nb+"\n");
		    for(String s : only_new) {
		    	writer.write(s+"\n");
		    }
		    
		    //We print all the palces visited only last year
		    writer.write("\n");
		    writer.write(only_previous.size()+" PLACES VISITED IN "+(year_nb-1)+" ONLY\n");
		    for(String s : only_previous) {
		    	writer.write(s+"\n");
		    }
		    
		    //We print all the places visited only in both years
		    writer.write("\n");
		    writer.write(both.size()+" PLACES VISITED IN BOTH "+(year_nb-1)+" AND "+year_nb+"\n");
		    for(String s : both) {
		    	writer.write(s+"\n");
		    }
		    writer.write("\n");
		    writer.close();
		} 
		//To catch the IOException, in case there is a problem with the file opening
		catch (IOException ex) {
			//We print what the error is
			ex.printStackTrace();
			System.exit(1); 
		}
		//Print information message to confirm good execution of task 3
		System.out.println("Task #3 printed in: "+this.task3_file);
	}
	
	/**
	 * Method to get the elements that are in the set previous but not in current
	 */
	public HashSet<String> keep_only_previous(HashSet<String> previous, HashSet<String> current) {
		HashSet<String> result = new HashSet<String>();
		//If element s of previous is not in current, we add it to the result. If yes, we go to the next element
		for(String s : previous) {
			if(!current.contains(s)) {
				result.add(s);
			}
		}
		return result;
	}
	
	/**
	 * Method to get the elements that are in the set current but not in previous
	 */
	public HashSet<String> keep_new(HashSet<String> previous, HashSet<String> current) {
		HashSet<String> result = new HashSet<String>();
		//If element s of current is not in previous, we add it to the result. If yes, we go to the next element
		for(String s : current) {
			if(!previous.contains(s)) {
				result.add(s);
			}
		}
		return result;
	}
	
	/**
	 * Method to get the intersection of two sets of strings
	 */
	public HashSet<String> keep_both(HashSet<String> previous, HashSet<String> current) {
		HashSet<String> result = new HashSet<String>();
		//If element s of previous is in current, we add it to the result. If not, we go to the next element
		for(String s : previous) {
			if(current.contains(s)) {
				result.add(s);
			}
		}
		//Returns the sets containing the intersection of the two parameters
		return result;
	}
}
