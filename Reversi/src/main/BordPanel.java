package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Created by yoshino on 2017/05/23.
 */
class BordPanel extends JPanel implements MouseListener{
    private int precedence;
    private int turn = 0;
    private ControlListener listener;

    private Bord mybord;

    BordPanel(Bord bord, ControlListener listener){
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(Constants.W, Constants.H));
        addMouseListener(this);
        System.out.println("turn: " + turn);
        this.mybord = bord;
        precedence = setPrecedence();
        this.listener = listener;
        if (precedence == 1){
            System.out.println("You are black.");
            this.listener.setPrecedence("black");
        }
        else{
            System.out.println("You are white.");
            this.listener.setPrecedence("white");
        }
    }

    private int setPrecedence(){
        Random rnd = new Random();
        return rnd.nextInt(2);
    }

    private boolean put(int x, int y, int color){
        if (mybord.put(x, y, color)){
            repainting();
            return true;
        }
        return false;
    }

    public void paint(Graphics g){
        for (int x = 0; x < 8 * 50; x += 50){
            for (int y = 0; y < 8 * 50; y += 50){
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

    private void repainting(){
        repaint();
    }

    public void mouseClicked(MouseEvent e){
        Point point = e.getPoint();
        if (precedence == 0){
            if (put(point.x / 50, point.y / 50, Bord.WHITE)){
                System.out.println("turn: " + turn);
                System.out.println("You are black.");
                this.listener.changePrecedence("black");
                this.listener.changeBlackStoneNum();
                turn++;
                precedence = 1;
            }
        }else{
            if (put(point.x / 50, point.y / 50, Bord.BLACK)){
                System.out.println("turn: " + turn);
                System.out.println("You are white.");
                this.listener.changePrecedence("white");
                this.listener.changeWhiteStoneNum();
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
