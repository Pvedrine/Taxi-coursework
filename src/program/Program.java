package program;

//This class is the main class of the program and contains the main method
public class Program {

	//Main method of the program
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//We create a year object to store and work with the data
		Year y_2015 = new Year(2015);
		//We load the data from various files
		Reader reader = new Reader(y_2015,"Taxi.txt","Destination.txt","Journey.txt","Previous_destinations.txt");
		reader.load();
		
		//We retrieve the user dir to create the files in the same directory than the program
		String currentDir = System.getProperty("user.dir");
		//We store the data into various files corresponding to the different tasks
		Writer writer = new Writer(y_2015, currentDir + "\\Task1.txt", currentDir + "\\Task2.txt", currentDir + "\\Task3.txt");
		writer.task1();
		writer.task2();
		writer.task3();
	}

}
