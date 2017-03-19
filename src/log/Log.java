package log;

import java.io.*;

//Here is a Singleton pattern so we get only one instance of the object LogFile.
public class Log {

	   //create an object of SingleObject
	   private static Log instance = new Log();
	   private static String filename = System.getProperty("user.dir") + "\\log.txt" ;

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private Log(){}

	   //Get the only object available
	   public static Log getInstance(){
	      return instance;
	   }

	   public void record(String string){
		   printIntoFile(string);
	   }
	   
	   private void printIntoFile(String string){
		   BufferedWriter writer = null;
		   //We try to write
		   try {
			   //Opens the file in append mode
			   writer = new BufferedWriter(new OutputStreamWriter(
					   new FileOutputStream(this.filename, true), "utf-8"));
			   //We add the message to the end of the file
			   writer.write(string + "\n");

			   writer.close();
			} 
			//To catch the IOException, in case there is a problem with the file opening
			catch (IOException ex) {
				//We print what the error is
				ex.printStackTrace();
				System.exit(1); 
			}
	   }
	}