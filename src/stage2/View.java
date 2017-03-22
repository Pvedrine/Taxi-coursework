package stage2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * The class implements Observer for the use of MVC
 * The class extends JFrame to use its graphical elements
 */
public class View extends JFrame implements Observer{

	/**
	 * In this property is stored the button to process the data
	 */
	JButton processButton;
	
	/**
	 * In this property is stored a reference of the model of the MVP
	 */
	Model model;
	
	/**
	 * In this property is stored the list of all the workers
	 */
	ArrayList<Worker> workList;
	
	/**
	 * In this property is stored the number of workers
	 */
	int numWorkers;
	
	/**
	 * In this property is stored the list of graphical elements for the workers
	 */
    private JTextArea [] workers ;

    /**
	 * constructor of the class
	 * The graphical elements are created here
	 */
    public View(Model model)
    {
        this.model = model;
        model.addObserver(this);
        workList = model.getWorkerList();
        numWorkers = workList.size();
        
        //set up window title
        setTitle("Taxi Booking");
        //ensure program ends when window closes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(100,600);
        setLocation(10,20);
 
        
        //add button panel and result field to the content pane
        Container contentPane = getContentPane();
        contentPane.add(createNorthPanel(), BorderLayout.NORTH);
        contentPane.add(createCustPanel(), BorderLayout.CENTER);
        //pack and set visible
        pack();
        setVisible(true);
    }
    
   
    private JPanel createCustPanel() {
    	//cheating - know there are 6 customers
    	JPanel custPanel = new JPanel(new GridLayout (0, numWorkers/2));
		workers  = new JTextArea [numWorkers];
		for (int i = 1; i < numWorkers; i++) {
			workers [i]= new JTextArea(15,80);
			//monospaced allows nice tabular layout
			workers[i].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			workers [i].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
			custPanel.add(workers[i]);
		}
		return custPanel;
    }

    private JPanel createNorthPanel() {
        //north panel shows the button to start processing
        processButton = new JButton("Launch program");
        JPanel northPanel = new JPanel();
        northPanel.add(processButton);
        return northPanel;
    }
    

    /**
	  * Method to add an event listener to the button
	  */
	 public void addProcessBookingJourneyListener(ActionListener al) {
			processButton.addActionListener(al);
	    }

	 /**
	  * Method to disable the button to be pushed
	  */
	 public void disableProcessButton() {
	    	processButton.setEnabled(false);
	    }
	 
	 /**
	  * Method to enable the button to be pushed
	  */
	 public void enableProcessButton(){
		 	processButton.setEnabled(true);
	 }
	 
	/**
	 * Required method for Observable interface
	 * Used to update graphical elements
	 */
	public synchronized void update(Observable o, Object args) {
		//For all workers but worker 1 (because it doesn't process data, it adds some)
    	for (int i = 1; i < numWorkers; i++) {
    		//We create a local variable
    		String report = "";
    		//If the worker is processing something
    		//When everything has been processed we update the view for graphical effect and everything must be clear
    		if(workList.get(i).getProcessingJourney() != null){
    			//Get the data to reload
    			report = workList.get(i).getProcessingJourney().toString();
    		}
    		//Set the data to display into the GUI
			this.workers[i].setText( report);	
			this.workers[i].setForeground(Color.BLACK);

    	}
    }
}
