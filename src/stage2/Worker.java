package stage2;

import java.util.ArrayList;
import log.Log;
import taxiClasses.Journey;
import taxiClasses.Taxi;

//This class is used within a thread, so it has to implement runnable
public class Worker implements Runnable {
	
	/**
	 * In this property is stored the ID of the current worker
	 */
	private int workerID;
	
	/**
	 * In this property is stored a reference of the model of our MVP
	 */
	private Model model;
	
	/**
	 * In this property is stored the journey currently processed
	 */
	private Journey journeyBeingProcessed;
	
	/**
	 * In this property is stored the list of the journeys this worker has processed
	 */
	private ArrayList<Journey> processedJourneys;
	
	/**
	 * constructor for this class with for parameter the ID of the worker and a reference of the model of the MVP
	 */
	public Worker(int id, Model model){
		this.workerID = id;
		this.model = model;
		this.processedJourneys = new ArrayList<Journey>();
	}
	
	/**
	 * Method to add a journey to the list of journey processed by this worker
	 */
	private void addProcessedJourney(Journey processed){
		this.processedJourneys.add(processed);
	}
	
	/**
	 * Accessor for the private property worker ID
	 */
	public int getWorkerId(){
		return this.workerID;
	}
	
	/**
	 * Accessor for the private property processingJourney
	 */
	public Journey getProcessingJourney(){
		return this.journeyBeingProcessed;
	}
	
	/**
	 * Overridden method run, purpose of the worker
	 */
	public void run(){
		//We create a local object
		Taxi taxiBeingBooked;
		
		//while there are still a taxi and a journey left
		while(model.isFinished()){
			
			//if worker 1
			if(this.workerID == 1){
				//Insert a new journey to the list of journey 
				this.model.addRandomJourneyToProcess(this);
				try {
					//Sleep for some time
					//Enough so the other threads can work and we don't have an infinite list of journeys
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//If worker other than 1
			else{
				//Get the first list to process from the model
				this.journeyBeingProcessed = model.getFirstJourneyToAllocate();
				//Get the first taxi available from the model
				taxiBeingBooked = model.getOneTaxi();
				//Associate the taxi with the journey
				this.journeyBeingProcessed.set_Driver(taxiBeingBooked);
				//Add this journey to the list of processed journeys
				model.addJourneyProcessed(this.journeyBeingProcessed);
				//Record this in the log
				Log.getInstance().record("Worker #" + this.workerID + ": " + this.journeyBeingProcessed.toString() + "\n");
				
				try {
					//Sleep for some time. Not too much otherwise worker 1 will work too much
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Add this journey to the local list of processed journeys
				addProcessedJourney(this.journeyBeingProcessed);
			}
			//Notify the view the model has changed
			model.modelChanged();
		}
		this.journeyBeingProcessed = null;
		model.modelChanged();
	}
}
