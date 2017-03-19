package stage2;


public class Kiosk implements Runnable {
	private Worker worker;
	private ListDisplay list;
	
	public Kiosk(Worker worker, ListDisplay list){
		this.worker = worker;
		this.list = list;
	}
	public void run() {
		for (int i = 0; i < list.getSize_taxis(); i++){
			try {
				Thread.sleep(3000); // wait 3 seconds
				worker.allocates();
			}
			catch (InterruptedException e){
				System.out.println(e.getMessage());
			}
		}
	}
}

