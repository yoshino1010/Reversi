package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Player implements MouseListener{
	String myName = "player1";
	JFrame myFrame;
	
	Player(String name, JFrame frame){
		myName = name;
		myFrame = frame;
	}
	
	public void put(){
		
	}
	
	public void pass(){
		
	}
	
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
}
