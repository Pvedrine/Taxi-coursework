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


public class View extends JFrame implements Observer{

	JButton processButton;
	Model model;
	ArrayList<Worker> workList;
	int numWorkers;
    private JTextArea [] workers ;

    
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
    	JPanel custPanel = new JPanel(new GridLayout (1, numWorkers));
		workers  = new JTextArea [numWorkers];
		for (int i = 0; i < numWorkers; i++) {
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
    

	/////////////////////////////////////////////////////
	//MVC pattern - allows listeners to be added
	 public void addProcessBookingJourneyListener(ActionListener al) {
			processButton.addActionListener(al);
	    }

	 public void disableProcessButton() {
	    	processButton.setEnabled(false);
	    }
	 
	 public void enableProcessButton(){
		 	processButton.setEnabled(true);
	 }
	/////////////////////////////////////////////////////////
	 
	public synchronized void update(Observable o, Object args) {
    	for (int i = 0; i < numWorkers; i++) {
    		String report = workList.get(i).getProcessingJourney().toString();
			this.workers[i].setText( report);	
			this.workers[i].setForeground(Color.BLACK);

    	}
    }
}
