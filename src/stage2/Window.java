import java.awt.*;
import javax.swing.*;
 
public class Window extends JFrame {
	public Window() {
		this.setTitle("Taxi kiosk");
	    this.setSize(800, 500);
	    this.setLocationRelativeTo(null);
	    this.setLayout(new GridLayout(2, 1));
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);
	    this.setResizable(false);
	}
	  
	public void addworkers(JPanel panel) {
		this.add(panel);
	}
	  
	public void addlists(JPanel panel) {
		this.add(panel);
	}
}

