package log;

public class LogFile {

	   //create an object of SingleObject
	   private static LogFile instance = new LogFile();

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private LogFile(){}

	   //Get the only object available
	   public static LogFile getInstance(){
	      return instance;
	   }

	   public void showMessage(String string){
	      System.out.println(string);
	   }
	}