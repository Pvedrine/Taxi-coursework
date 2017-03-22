package stage2;

/**
 * This is the main class of the app
 */
public class Program {

	/**
	 * This is the main method for the stage 2 of the app
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//We create the model 
		//With for argument number of workers+1
		//So arg = 4 => 3 workers
		Model model = new Model(3);
		
		//we create the view
        View   view  = new View(model);
        
        //we creathe the controller
        @SuppressWarnings("unused")
		Controller controller = new Controller(model, view);   
        
        //The components of the MVC are created, we show the GUI
        view.setVisible(true); 
	}

} 
