package main;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Game implements MouseListener{
	private Bord bord = new Bord();
	private Frame frame = new Frame("Reversi", bord);
	private int precedence;
	private int turn = 0;
	
	public void Start(){
		frame.addMouseListener(this);
		precedence = setPrecedence(); //先行を決める
		System.out.println("turn: " + turn);
		if (precedence == 1) System.out.println("You are black.");
		else System.out.println("You are white.");
	}
	
	public int setPrecedence(){
		Random rnd = new Random();
		return rnd.nextInt(2);
	}
	
	public void mouseClicked(MouseEvent e){
		Point point = e.getPoint();
		if (precedence == 0){
			if (frame.put(point.x / 50, (point.y - 26) / 50, Bord.WHITE)){
				System.out.println("turn: " + turn);
				System.out.println("You are black.");
				turn++;
				precedence = 1;
			}
		}else{
			if (frame.put(point.x / 50, (point.y - 26) / 50, Bord.BLACK)){
				System.out.println("turn: " + turn);
				System.out.println("You are white.");
				turn++;
				precedence = 0;
			}
		}
	 }

	  public void mouseEntered(MouseEvent e){}
	  public void mouseExited(MouseEvent e){}
	  public void mousePressed(MouseEvent e){}
	  public void mouseReleased(MouseEvent e){}
}
