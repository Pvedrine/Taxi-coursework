package log;

import java.io.*;

//Here is a Singleton pattern so we get only one instance of the object Log.
public class Log {

	   //create an object of Log
	   private static Log instance = new Log();
	   private static String filename;

	   //make the constructor private so that this class cannot be
	   //instantiated more than once.
	   private Log(){
		   Log.filename = System.getProperty("user.dir") + "\\log.txt" ;
		   //just to nicely print the beginning of the recording
		   record("Beginning\n");
	   }

	   //Get the only object available
	 //It is synchronized so only one thread can use this method at the same time
	   public synchronized static Log getInstance(){
	      return instance;
	   }

	   //method to save a string into the log.
	   //It is synchronized so only one thread can execute it and print at the same time
	   public synchronized void record(String string){
		   //This method has for purpose to hide to the soft where the data is stored.
		   //This way it could be a database or a file, it is the same
		   printIntoFile(string);
	   }
	   
	   //Method to write on a file
	   //Private so it is called by another method inside this class
	   //More abstraction 
	   private void printIntoFile(String string){
		   //We create a buffer to write
		   BufferedWriter writer = null;
		   //We try to write
		   try {
			   //Opens the file in append mode
			   writer = new BufferedWriter(new OutputStreamWriter(
					   new FileOutputStream(Log.filename, true), "utf-8"));
			   //We add the message to the end of the file
			   writer.write(string + "\n");

			   writer.close();
			} 
			//To catch the IOException, in case there is a problem with the file opening
			catch (IOException ex) {
				//We print what the error is
				ex.printStackTrace();
				System.out.println("Impossible to printo into log file");
				System.exit(1); 
			}
	   }
	}