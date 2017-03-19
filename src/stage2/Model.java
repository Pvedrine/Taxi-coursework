package stage2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;

import taxiClasses.Journey;
import taxiClasses.Taxi;

public class Model extends Observable implements  Runnable {
	private HashSet<Taxi> taxies;
	private ArrayList<Journey> journeysToAllocate;
	private HashSet<Journey> journeysAllocated;
	private boolean stateFinished;
	private Thread [] custThreads;
	private View view;
	
	///////////////////////////////////////////////////////////
	//returns whether the program should be terminated or not
	public boolean isFinished() {
	return stateFinished;
	}
	
	//indicates end of program
	public void setFinished() {
		stateFinished = true;
	
	}

	
	public void addJourney(Journey journeyToAdd){
		this.journeysToAllocate.add(journeyToAdd);
	}
	
	public Journey getFirstJourneyToAllocate(){
		return getAllJourneysToAllocate().get(0);
	}
	
	public ArrayList<Journey> getAllJourneysToAllocate(){
		return this.journeysToAllocate;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
