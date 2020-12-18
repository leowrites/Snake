package main;

import sun.misc.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JPanel implements ActionListener {

    test.GameState state = test.GameState.START;

    int score;
    JButton begin = new JButton("Start");
    JLabel label = new JLabel("SNAKE BY LEO");

    Welcome(int score){
        this.score = score;
        setSize(800,800);
        setBackground(Color.black);
        setLayout(new FlowLayout());
        begin.addActionListener(this);
        label.setFont(new Font("Serif", Font.BOLD,50));
        label.setForeground(Color.WHITE);
        Font title = new Font("Serif",Font.BOLD,50);
        begin.setFont(title);
        begin.setPreferredSize(new Dimension(200,200));
        begin.setBackground(Color.BLACK);
        begin.setBorderPainted(false);
        begin.setForeground(Color.WHITE);
        begin.setBounds(300,300,20,20);
        JLabel presentScore = new JLabel("You score from last game is: " + score);
        presentScore.setFont(new Font("Serif", Font.BOLD, 20));
        presentScore.setForeground(Color.WHITE);
        add (label,BorderLayout.PAGE_START);
        add(begin, BorderLayout.CENTER);
        add(presentScore,BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            System.out.println("Clicked");
            state = test.GameState.RUNNING;
            System.out.println("State changed to " +state);
        }

    public test.GameState returnState(){
        return state;
    }

}
