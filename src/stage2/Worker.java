package stage2;

import java.util.ArrayList;
import java.util.Random;

import log.Log;
import taxiClasses.Destination;
import taxiClasses.Journey;
import taxiClasses.Taxi;

public class Worker implements Runnable {
	private int workerID;     //id
	private Model model;
	private Journey journeyBeingProcessed;
	private ArrayList<Journey> processedJourneys;
	
	public Worker(int id, Model model){
		this.workerID = id;
		this.model = model;
		this.processedJourneys = new ArrayList<Journey>();
	}
	
	public void addProcessedJourney(Journey processed){
		this.processedJourneys.add(processed);
	}
	
	public int getWorkerId(){
		return this.workerID;
	}
	
	public Journey getProcessingJourney(){
		return this.journeyBeingProcessed;
	}
	
	public void run(){
		Taxi taxiBeingBooked;
		while(model.isFinished()){
			if(this.workerID == 1){
				this.model.addRandomJourneyToProcess();
				try {
					Thread.sleep(400*this.workerID);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				this.journeyBeingProcessed = model.getFirstJourneyToAllocate();
				taxiBeingBooked = model.getOneTaxi();
				this.journeyBeingProcessed.set_Driver(taxiBeingBooked);
				model.addJourneyProcessed(this.journeyBeingProcessed);
				Log.getInstance().record("Worker #" + this.workerID + ": " + this.journeyBeingProcessed.toString() + "\n");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
