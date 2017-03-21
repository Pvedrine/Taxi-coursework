package stage2;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model(3);   //the model
        View   view  = new View(model);
        Controller controller = new Controller(model, view);   
        view.setVisible(true);
	}

}
