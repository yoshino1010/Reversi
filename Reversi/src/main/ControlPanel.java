package main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yoshino on 2017/05/23.
 */
public class ControlPanel extends JPanel implements ControlListener{
    private JLabel turn, whiteStoneNum, blackStoneNum;
    private int wn = 2, bn = 2;

    ControlPanel(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, Constants.H));

        turn = new JLabel();
        whiteStoneNum = new JLabel(String.valueOf(wn));
        blackStoneNum = new JLabel(String.valueOf(bn));
        add(turn);
        add(whiteStoneNum);
        add(blackStoneNum);
    }

    @Override
    public void setPrecedence(String p) {
        this.turn.setText(p);
    }

    @Override
    public void changePrecedence(String p){
        this.turn.setText(p);
    }

    @Override
    public void changeBlackStoneNum(){
        wn++;
        this.blackStoneNum.setText(String.valueOf(wn));
    }

    @Override
    public void changeWhiteStoneNum() {
        bn++;
        this.whiteStoneNum.setText(String.valueOf(bn));
    }
}
