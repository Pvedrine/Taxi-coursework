package program;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.*;

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
		ArrayList<Journey> journeys = this.year.get_Journeys();
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
		
		BufferedWriter writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(this.task1_file), "utf-8"));
		    writer.write("CHARGES FOR THE TOP 5 JOURNEYS\n\r");
		    for(int i=0; i<5; i++) {
		    	writer.write(journeys.get(i).get_Driver().get_Registration_Number()+"  "+
		    					journeys.get(i).get_Place().get_Adress()+"     "+
		    						journeys.get(i).get_Place().get_Distance()+" miles  "+
		    							journeys.get(i).get_Passengers()+" person(s)  "+
		    								"Cost : £"+journeys.get(i).get_Cost()+"\n");
		    }
		    writer.write("\nCHARGES FOR THE CHEAPEST 5 JOURNEYS\n");
		    for(int i=journeys.size()-1; i>journeys.size()-6; i--) {
		    	writer.write(journeys.get(i).get_Driver().get_Registration_Number()+"  "+
		    					journeys.get(i).get_Place().get_Adress()+"     "+
		    						journeys.get(i).get_Place().get_Distance()+" miles  "+
		    							journeys.get(i).get_Passengers()+" person(s)  "+
		    								"Cost : £"+journeys.get(i).get_Cost()+"\n");
		    }
		    writer.close();
		} 
		catch (IOException ex) {
			ex.printStackTrace();
			System.exit(1); 
		}
		System.out.println("Task 1 printed in: "+this.task1_file);
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
		
		BufferedWriter writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(this.task2_file), "utf-8"));
		    for(i=0; i<taxies.size(); i++) {
		    	writer.write(taxies.get(i).get_Name()+"\n");
		    	for(String s : dest_per_name.get(i)) {
		    		writer.write("   "+s+"\n");
		    	}
		    	writer.write("\n");
		    }
		    writer.close();
		} 
		catch (IOException ex) {
			ex.printStackTrace();
			System.exit(1); 
		}
		System.out.println("Task 2 printed in: "+this.task2_file);
	}
	
	/**
	 * Method to get the task3 done and saved into a file
	 */
	public void task3() {
		ArrayList<Journey> journeys = this.year.get_Journeys();
		
		HashSet<String> previous = this.year.get_Previous_year_destinations();
		HashSet<String> current = new HashSet<String>();
		for(Journey j : journeys) {
			if(!current.contains(j.get_Place().get_Adress())) {
				current.add(j.get_Place().get_Adress());
			}
		}
		HashSet<String> only_previous = this.keep_only_previous(previous, current);
		HashSet<String> only_new = this.keep_new(previous, current);
		HashSet<String> both = this.keep_both(previous, current);
		
		int year_nb = this.get_Year().get_Year_nb();
		BufferedWriter writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(this.task3_file), "utf-8"));
		    writer.write(only_new.size()+" NEW PLACES IN "+year_nb+"\n");
		    for(String s : only_new) {
		    	writer.write(s+"\n");
		    }
		    writer.write("\n");
		    writer.write(only_previous.size()+" PLACES VISITED IN "+(year_nb-1)+" ONLY\n");
		    for(String s : only_previous) {
		    	writer.write(s+"\n");
		    }
		    writer.write("\n");
		    writer.write(both.size()+" PLACES VISITED IN BOTH "+(year_nb-1)+" AND "+year_nb+"\n");
		    for(String s : both) {
		    	writer.write(s+"\n");
		    }
		    writer.write("\n");
		    writer.close();
		} 
		catch (IOException ex) {
			ex.printStackTrace();
			System.exit(1); 
		}
		System.out.println("Task 3 printed in: "+this.task3_file);
	}
	
	public HashSet<String> keep_only_previous(HashSet<String> previous, HashSet<String> current) {
		HashSet<String> result = new HashSet<String>();
		for(String s : previous) {
			if(!current.contains(s)) {
				result.add(s);
			}
		}
		return result;
	}
	
	public HashSet<String> keep_new(HashSet<String> previous, HashSet<String> current) {
		HashSet<String> result = new HashSet<String>();
		for(String s : current) {
			if(!previous.contains(s)) {
				result.add(s);
			}
		}
		return result;
	}
	
	public HashSet<String> keep_both(HashSet<String> previous, HashSet<String> current) {
		HashSet<String> result = new HashSet<String>();
		for(String s : previous) {
			if(current.contains(s)) {
				result.add(s);
			}
		}
		return result;
	}
}
