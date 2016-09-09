package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Frame extends JFrame{
	private static final int SIZE = 50;
	private static final int W = SIZE * 8 + 1;
	private static final int H = SIZE * 8 + 23;
	
	private Bord mybord;
	
	Frame(String title, Bord bord){
		mybord = bord;
		setTitle(title);
		setVisible(true);
		setBounds(400, 200, W + 200, H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp = new JPanel();
		jp.setBackground(Color.GREEN);
		
		Container contentPane = getContentPane();
	    contentPane.add(jp);
		contentPane.add(jp, BorderLayout.CENTER);
	}
	
	public void paint(Graphics g){
		for (int x = 0; x < 8 * 50; x += 50){
			for (int y = 22; y < 8 * 50 + 22; y += 50){
				g.setColor(Color.GREEN);
				g.fillRect(x, y, 50, 50);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, 50, 50);
				if(mybord.getState(x / 50, y / 50) == Bord.WHITE){
					g.setColor(Color.WHITE);
					g.fillArc(x, y, 50, 50, 0, 360);
				}else if(mybord.getState(x / 50, y / 50) == Bord.BLACK){
					g.setColor(Color.BLACK);
					g.fillArc(x, y, 50, 50, 0, 360);
				}
			}
		}
	}
}
