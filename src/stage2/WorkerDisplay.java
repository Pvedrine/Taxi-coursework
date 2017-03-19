import java.awt.*;
import javax.swing.*;

public class WorkerDisplay extends JPanel {
	
	public WorkerDisplay() {
		super();
		update();
	}
	
	public void update() {
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
        g.drawRect(10,10,770,150);
        g.drawRect(380, 10, 0, 150);
        g.drawString("WINDOW 1", 40, 40);
        g.drawString("WINDOW 2", 410, 40);
	}
}
