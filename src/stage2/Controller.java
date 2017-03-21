package stage2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

	private Model model;
	private View view;
	
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
		this.view.addProcessBookingJourneyListener(new ProcessBookingJourneyController());
	}


	class ProcessBookingJourneyController  implements ActionListener
	{
	    public void actionPerformed(ActionEvent e) 
	    { 
	    	view.disableProcessButton();
			Thread thread = new Thread (model);
			thread.start();

			view.enableProcessButton();
	    }
	}

}