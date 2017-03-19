import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class ListDisplay extends JPanel {
	private int size_groups;
	private int size_taxis;
	private String[] groups;
	private String[] taxis;
	
	public ListDisplay() {
		super();
		this.read_groups();
		this.read_taxis();
		this.update();
	}
	
	public void update() {
		repaint();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
        g.drawRect(10,0,550,210);
        g.drawRect(360, 0, 0, 210);
        g.drawString("PASSENGER GROUPS", 40, 30);
        int y_groups = 70;
        for(int i = 0;i<this.groups.length;i++) {
        	if(i%2==0) {
        		g.drawString(groups[i], 40, y_groups);
        		y_groups += 15;
        	}
        	else {
        		if(groups[i].equals("1")) { g.drawString(groups[i]+" person", 40, y_groups); }
        		else { g.drawString(groups[i]+" people", 40, y_groups); } 
        		y_groups += 30;
        	}
        }
        g.drawString("TAXIS", 390, 30);
        int y_taxis = 70;
        for(int i = 0; i<this.taxis.length; i++) {
        	g.drawString(taxis[i], 390, y_taxis);
        	y_taxis += 20;
        }        
	}
	
	public void read_groups() {
		BufferedReader buff = null;
		try {
        	buff = new BufferedReader(new FileReader("journeys.txt"));
        	int i=0;
	    	String inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		i++;
	    		inputLine = buff.readLine();
	        }
	    	buff.close();
	    	this.setSize_groups(i/2);
	    	this.groups = new String[i];
	    	buff = new BufferedReader(new FileReader("journeys.txt"));
        	i=0;
	    	inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		this.groups[i] = inputLine;
	    		inputLine = buff.readLine();
	    		i++;
	        }
	    	
        }
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        finally  {
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
	}
	
	public void read_taxis() {
		BufferedReader buff = null;
		try {
        	buff = new BufferedReader(new FileReader("taxis.txt"));
        	int i=0;
	    	String inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		i++;
	    		inputLine = buff.readLine();
	        }
	    	buff.close();
	    	this.setSize_taxis(i);
	    	this.taxis = new String[i];
	    	buff = new BufferedReader(new FileReader("taxis.txt"));
        	i=0;
	    	inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		this.taxis[i] = inputLine;
	    		inputLine = buff.readLine();
	    		i++;
	        }
	    	
        }
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        finally  {
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
	}

	public int getSize_groups() {
		return size_groups;
	}

	public void setSize_groups(int size_groups) {
		this.size_groups = size_groups;
	}

	public int getSize_taxis() {
		return size_taxis;
	}

	public void setSize_taxis(int size_taxis) {
		this.size_taxis = size_taxis;
	}
	public String[] get_groups() {
		return groups;
	}
	public String[] get_taxis() {
		return taxis;
	}
	public String[] set_groups(String[] groups){
		this.groups = groups;
	}
	public String[] set_taxis(String[] taxis){
		this.taxis = taxis;
	}
}

