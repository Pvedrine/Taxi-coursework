package stage2;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model(4);   //the model
        View   view  = new View(model);
        @SuppressWarnings("unused")
		Controller controller = new Controller(model, view);   
        view.setVisible(true);
	}

}
