package stage2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import taxiClasses.Taxi;
import taxiClasses.Journey;


public class View extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    private JPanel panelWorkers;
    private JPanel panelLists;

    
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
 
        
        JPanel panel = new JPanel(new GridLayout(2,1));
        JPanel panel2 = createCustWorkPanel();
        JPanel panel3 = createCustListPanel();
        panel.add(panel2);
        panel.add(panel3);
        
        setPanelWorkers(panel2);
        setPanelLists(panel3);
        
        
        //add button panel and result field to the content pane
        Container contentPane = getContentPane();
        contentPane.add(createNorthPanel(), BorderLayout.NORTH);
        contentPane.add(panel, BorderLayout.CENTER);
        //pack and set visible
        pack();
        setVisible(true);
        this.setResizable(false);
      	//To initialize the view with the data at startup
      	this.model.modelChanged();
    }
    
   
    private JPanel createCustWorkPanel() {
    	//We want as many columns as we have workers
    	JPanel custPanel = new JPanel(new GridLayout (1, numWorkers));
		workers  = new JTextArea [numWorkers];
		for (int i = 0; i < numWorkers; i++) {
			workers [i]= new JTextArea(20,40);
			//monospaced allows nice tabular layout
			//workers[i].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			workers[i].setBorder(BorderFactory.createMatteBorder(30, 4, 4, 4, Color.LIGHT_GRAY));
			custPanel.add(workers[i]);
		}
		return custPanel;
    }
    
    private JPanel createCustListPanel() {
    	//We want as many columns as we have workers
    	JPanel custPanel = new JPanel(new GridLayout (1, 2));
		for (int i = 0; i < 2; i++) {
			JTextArea list = new JTextArea(20,40);
			list.setBorder(BorderFactory.createMatteBorder(30, 4, 4, 4, Color.LIGHT_GRAY));
			custPanel.add(list);
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
    	for (int i = 1; i < numWorkers; i++) {
    		String report = "";
    		if(workList.get(i).getProcessingJourney()!= null)
    			report = workList.get(i).getProcessingJourney().toString();
    		LinkedList<Taxi> taxies = model.getAllTaxies();
    		LinkedList<Journey> journeys = model.getAllJourneysToAllocate();
    		String str_taxies = "";
    		String str_journeys = "";
    		for(int j=0;j<taxies.size();j++) {
    			str_taxies += taxies.get(j).toString() + "\n";
    		}
    		for(int j=0;j<journeys.size();j++) {
    			str_journeys += journeys.get(j).toString() + "\n";
    		}
    		JTextArea jt_wk = (JTextArea) panelWorkers.getComponent(i);
			jt_wk.setText(report);	
			jt_wk.setForeground(Color.BLACK);
			
			JTextArea jt_tx = (JTextArea) panelLists.getComponent(1);
			jt_tx.setText(str_taxies);
			jt_tx.setForeground(Color.BLACK);
			
			JTextArea jt_jr = (JTextArea) panelLists.getComponent(0);
			jt_jr.setText(str_journeys);
			jt_jr.setForeground(Color.BLACK);
    	}
    }
	
	public JPanel getPanelWorkers() {
		return panelWorkers;
	}


	public void setPanelWorkers(JPanel panelWorkers) {
		this.panelWorkers = panelWorkers;
	}


	public JPanel getPanelLists() {
		return panelLists;
	}


	public void setPanelLists(JPanel panelLists) {
		this.panelLists = panelLists;
	}
}
