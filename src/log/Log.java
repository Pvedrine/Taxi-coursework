package log;

//Here is a Singleton pattern so we get only one instance of the object LogFile.
public class LogFile {

	   //create an object of SingleObject
	   private static LogFile instance = new LogFile();
	   private static String filename = System.getProperty("user.dir"); + "\\log.txt"

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private LogFile(){}

	   //Get the only object available
	   public static LogFile getInstance(){
	      return instance;
	   }

	   public void printIntoLogFile(String string){
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