
public class Program {
	public static void main(String[] args) {
		Window window = new Window();
		
		WorkerDisplay workdisplay = new WorkerDisplay();
		window.addworkers(workdisplay);
		
		ListDisplay listdisplay = new ListDisplay();
		window.addlists(listdisplay);
		System.out.println(Math.min(listdisplay.getSize_groups(),listdisplay.getSize_taxis()));
		
		
		
	}
}
