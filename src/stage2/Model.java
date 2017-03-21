package stage2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import log.Log;
import taxiClasses.Journey;
import taxiClasses.Taxi;

public class Model extends Observable implements  Runnable {
	private HashSet<Taxi> taxies = new HashSet<Taxi>();
	private ArrayList<Journey> journeysToAllocate = new ArrayList<Journey>();
	private ArrayList<Worker> workers = new ArrayList<Worker>();
	private HashSet<Journey> journeysAllocated = new HashSet<Journey>();
	private boolean stateFinished;
	private Thread [] workThreads;
	private View view;
	
	
	public Model(int nbWorkers){
		for (int i = 1; i <=nbWorkers; i++) {
			Worker c = new Worker (i, this);
			workers.add(c);
			
		}
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
		Taxi taxi = this.taxies.iterator().next();
		this.taxies.remove(taxi);
		return taxi;
	}
	public void addJourneyToProcess(Journey journeyToAdd){
		this.journeysToAllocate.add(journeyToAdd);
	}
	
	public void addJourneyProcessed(Journey journeyToAdd){
		this.journeysAllocated.add(journeyToAdd);
	}
	
	public synchronized Journey getFirstJourneyToAllocate(){
		return getAllJourneysToAllocate().get(0);
	}
	
	public ArrayList<Journey> getAllJourneysToAllocate(){
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
    		try {
				workThreads[cind].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	System.out.println("Finishing");
    	Log.getInstance().record("Finishing");
		
	}

}
