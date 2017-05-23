package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

class Game{
    Game(String title){
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setVisible(true);
        frame.setBounds(400, 200, Constants.W + 200, Constants.H);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        ControlPanel cp = new ControlPanel();
        frame.add(cp, BorderLayout.EAST);

        BordPanel bp = new BordPanel(new Bord(), cp);
        frame.add(bp,BorderLayout.WEST);
    }
	

	

}
