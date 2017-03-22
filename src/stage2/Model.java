package stage2;

//useful imports 
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;
import log.Log;
import taxiClasses.*;

//This class contains all the informations about the model and how to use it
//The class implements runnable so we can use it in a thread
//The class extends Observable so we can use MVC
public class Model extends Observable implements  Runnable {
	
	/**
	 * In this property is stored the list of taxies that can be assigned for a journey
	 */
	private LinkedList<Taxi> taxies = new LinkedList<Taxi>();
	
	/**
	 * In this property is stored the list of the journeys that have to be allocated
	 */
	private LinkedList<Journey> journeysToAllocate = new LinkedList<Journey>();
	
	/**
	 * In this property is stored the list of all the workers we have
	 */
	private ArrayList<Worker> workers = new ArrayList<Worker>();
	
	/**
	 * In this property is stored the list of all the journeys that have been allocated so far
	 */
	private ArrayList<Journey> journeysAllocated = new ArrayList<Journey>();
	
	/**
	 * In this property is stored the list of threads we use
	 */
	private Thread [] workThreads;
	
	/**
	 * In this property is stored a reference of the view from MVC
	 */
	@SuppressWarnings("unused")
	private View view;
	
	/**
	 * Constructor for this class with the number of workers for parameter
	 */
	public Model(int nbWorkers){
		
		//We create all the workers we need according to the parameter
		for (int i = 1; i <=nbWorkers; i++) {
			Worker c = new Worker (i, this);
			workers.add(c);
			
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//we add some data here for simplicity
		this.taxies.add(new Taxi("ABH222", "Richard"));
		this.taxies.add(new Taxi("ABH111", "Didier"));
		this.taxies.add(new Taxi("ABH333", "Robert"));
		this.taxies.add(new Taxi("ABH444", "Raoul"));
		this.journeysToAllocate.add(new Journey(null, new Destination("Edinburgh", 3), 2));
		this.journeysToAllocate.add(new Journey(null, new Destination("London", 7), 4));
		this.journeysToAllocate.add(new Journey(null, new Destination("Glasgow", 7), 3));
		///////////////////////////////////////////////////////////////////////////////////////////
	}
	
	/**
	 * Method to get the state of the lists of journeys and taxies
	 * It is synchronized so one thread only can access it at a time
	 * return true if at least one of the lists is empty
	 */
	public boolean isFinished() {
		return !(this.journeysToAllocate.isEmpty() || this.taxies.isEmpty());
	}

	/**
	 * Method to get the first item in the LinkedList<Taxi> property
	 * It is synchronized so one thread only can access it at a time
	 */
	public synchronized Taxi getOneTaxi(){
		return this.taxies.removeFirst();
	}
	
	/**
	 * Method to add a journey to the list of journey that have to be processed
	 */
	public void addJourneyToProcess(Journey journeyToAdd){
		this.journeysToAllocate.add(journeyToAdd);
	}

	/**
	 * Method to add a journey to the list of journey that have to be processed
	 * Some arguments are random, others are fixed so it is simpler
	 */
	public void addRandomJourneyToProcess(){
		Random rand = new Random(); 
		Journey j1 = new Journey(null, new Destination("Inverness", rand.nextInt(15 - 0 + 1) + 0), rand.nextInt(4 - 1 + 1) + 1);
		addJourneyToProcess(j1);
	}
	
	/**
	 * Method to add a journey to the list of the journey that have been processed
	 */
	public void addJourneyProcessed(Journey journeyToAdd){
		this.journeysAllocated.add(journeyToAdd);
	}
	
	/**
	 * Method to get the first item in the LinkedList<Journey> property
	 * It is synchronized so one thread only can access it at a time
	 */
	public synchronized Journey getFirstJourneyToAllocate(){
		return this.journeysToAllocate.removeFirst();
	}
	
	/**
	 * Accessor for the private property LinkedList<Journey>
	 * It is synchronized so one thread only can access it at a time
	 */
	public synchronized LinkedList<Journey> getAllJourneysToAllocate(){
		return this.journeysToAllocate;
	}
	
	/**
	 * Accessor for the private property LinkedList<Taxi>
	 * It is synchronized so one thread only can access it at a time
	 */
	public synchronized LinkedList<Taxi> getAllTaxies(){
		return this.taxies;
	}
	
	/**
	 * Accessor for the private property ArrayList<Workers>
	 */
	public ArrayList<Worker> getWorkerList(){
		return this.workers;
	}

	/**
	 * overridden run method to create the works and their corresponding thread
	 */
	@Override
	public void run() {
		//We get the number of workers
		workThreads = new Thread[workers.size()];
		//From 1 to the number of workers 
    	for (int cind = 0; cind < workers.size(); cind ++)
    	{
    		//We create a thread
    		workThreads[cind] = new Thread(workers.get(cind));
    		//We start the thread
    		workThreads[cind].start();
    	}
    	//From 1 to the number of workers
    	for(int cind = 0; cind < workers.size(); cind ++){
    		try {
    			//We join all the threads so the main threads does not finish before all others 
    			//And thus we avoid zombie threads
				workThreads[cind].join();
			//In case there is a problem we catch it
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//We inform the user the app has terminated in the console
    	System.out.println("Finishing\n");
    	//Record in the log that the app has terminated
    	//We do it here because we cannot do it just before the Log object is destroyed by the GC
    	Log.getInstance().record("Finishing\n");
		
	}
	
	/**
	 * Method to notify observers that state has changed
	 */
	public synchronized void modelChanged(){
		setChanged();
		notifyObservers();
    	clearChanged();
	}

}
