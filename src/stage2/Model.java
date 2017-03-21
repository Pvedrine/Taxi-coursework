package stage2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import log.Log;
import taxiClasses.*;

public class Model extends Observable implements  Runnable {
	private LinkedList<Taxi> taxies = new LinkedList<Taxi>();
	private LinkedList<Journey> journeysToAllocate = new LinkedList<Journey>();
	private ArrayList<Worker> workers = new ArrayList<Worker>();
	private ArrayList<Journey> journeysAllocated = new ArrayList<Journey>();
	private boolean stateFinished;
	private Thread [] workThreads;
	private View view;
	
	
	public Model(int nbWorkers){
		for (int i = 1; i <=nbWorkers; i++) {
			Worker c = new Worker (i, this);
			workers.add(c);
			
		}
		this.taxies.add(new Taxi("ABH222", "Richard"));
		this.taxies.add(new Taxi("ABH111", "Didier"));
		this.taxies.add(new Taxi("ABH333", "Robert"));
		this.taxies.add(new Taxi("ABH444", "Raoul"));
		this.journeysToAllocate.add(new Journey(null, new Destination("Edinburgh", 3), 2));
		this.journeysToAllocate.add(new Journey(null, new Destination("London", 7), 4));
		this.journeysToAllocate.add(new Journey(null, new Destination("Glasgow", 7), 1));
		this.journeysToAllocate.add(new Journey(null, new Destination("Glasgow", 7), 3));
	}
	
	///////////////////////////////////////////////////////////
	//returns whether the program should be terminated or not
	public boolean isFinished() {
		
		return stateFinished;
	}
	
	//indicates end of program
	public void setFinished(boolean state) {
		stateFinished = state;
	
	}

	public synchronized Taxi getOneTaxi(){
		return this.taxies.removeFirst();
	}
	public void addJourneyToProcess(Journey journeyToAdd){
		this.journeysToAllocate.add(journeyToAdd);
	}
	
	public void addJourneyProcessed(Journey journeyToAdd){
		this.journeysAllocated.add(journeyToAdd);
	}
	
	public synchronized Journey getFirstJourneyToAllocate(){
		return this.journeysToAllocate.removeFirst();
	}
	
	public LinkedList<Journey> getAllJourneysToAllocate(){
		return this.journeysToAllocate;
	}
	
	public ArrayList<Worker> getWorkerList(){
		return this.workers;
	}

	@Override
	public void run() {
		workThreads = new Thread[workers.size()];
    	for (int cind = 0; cind < workers.size(); cind ++)
    	{
    		workThreads[cind] = new Thread(workers.get(cind));
    		workThreads[cind].start();
    	}
    	for(int cind = 0; cind < workers.size(); cind ++){
    		try {
				workThreads[cind].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	System.out.println("Finishing\n");
    	Log.getInstance().record("Finishing\n");
		
	}

}
