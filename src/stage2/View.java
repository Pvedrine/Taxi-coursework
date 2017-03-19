package stage2;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;


public class View extends JFrame implements Observer{

	JButton processButton;
	Model model;
	


	public View(Model model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}

	/////////////////////////////////////////////////////
	//MVC pattern - allows listeners to be added
	 public void addProcessBidsListener(ActionListener al) {
			processButton.addActionListener(al);
	    }

	 public void disableProcessButton() {
	    	processButton.setEnabled(false);
	    }
	/////////////////////////////////////////////////////////
	 
	@Override
	public synchronized void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
