package stage2;

//Useful imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class is for the controller module of our MVC pattern
public class Controller {

	/**
	 * In this property is stored a reference of the model
	 */
	private Model model;
	
	/**
	 * In this property is stored a reference of the view
	 */
	private View view;
	
	/**
	 * All parameters constructor for this class
	 */
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
		//Add an event handler from the view to the model
		this.view.addProcessBookingJourneyListener(new ProcessBookingJourneyController());
	}

	/**
	 * Event handler, with creation of a new thread, so the GUI is non-blocking
	 */
	class ProcessBookingJourneyController  implements ActionListener
	{
	    public void actionPerformed(ActionEvent e) 
	    { 
	    	//Deactivate the ProcessButton not to be able to click again
	    	view.disableProcessButton();
	    	//creathe thread for non blocking GUI
			Thread thread = new Thread (model);
			thread.start();
			//Enable the ProcessButton to launch the app again
			view.enableProcessButton();
	    }
	}

}