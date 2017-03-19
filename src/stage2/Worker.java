package stage2;

import taxiClasses.Destination;
import taxiClasses.Taxi;

public class Worker {
	private ListDisplay list;
	private Taxi taxi;
	private Destination dest;
	private String[] current_taxis;
	private String[] current_groups;
	private int i =0;
	
	public Worker(ListDisplay list){
		this.list = list;
	}

	public synchronized void allocates() {
		current_taxis = list.get_taxis();
		
		
		String[] new_taxis = new String[current_taxis.length-1];
		
		taxi = new Taxi(current_taxis[0], Integer.toString(i));
		
		for (int j = 1; j< current_taxis.length; j++){
			new_taxis[j-1] = current_taxis[j];
		}
		
		list.set_taxis(new_taxis);
		

		
		current_groups = list.get_groups();
		
		
		String[] new_groups = new String [current_groups.length -2];
		
		dest = new Destination (current_groups[0], Integer.parseInt(current_groups[1]));
		
		for (int j = 2; j<current_groups.length; j++){
			new_groups[j-2] = current_groups[j];
		}
		
		list.set_groups(new_groups);
		
		
	}
}
