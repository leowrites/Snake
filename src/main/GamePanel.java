package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel{
    //maybe display score?

    //using key binding
    Action toleft = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            left();
        }
    };
    Action toright = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            right();
        }
    };
    Action toup = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            up();
        }
    };
    Action todown = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            down();
        }
    };
    GamePanel(){
        init();
        setSize(800,800);
        setBackground(Color.blue);
        setFocusable(true);
        requestFocusInWindow();
        //Using key binding
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),"left");
        getActionMap().put("left",toleft);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"),"up");
        getActionMap().put("up",toup);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),"right");
        getActionMap().put("right",toright);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"),"down");
        getActionMap().put("down",todown);

//        addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//                int keyCode = e.getKeyCode();
//                switch(keyCode) {
//
//                    case KeyEvent.VK_UP:
//                        up = true;
//                        down = false;
//                        left = false;
//                        right = false;
//                        break;
//                    case KeyEvent.VK_DOWN:
//                        down = true;
//                        up = false;
//                        left = false;
//                        right = false;
//                        break;
//                    case KeyEvent.VK_LEFT:
//                        left = true;
//                        right = false;
//                        up = false;
//                        down = false;
//                        break;
//                    case KeyEvent.VK_RIGHT:
//                        right = true;
//                        left = false;
//                        up = false;
//                        down = false;
//                        break;
//
//                }
//            }
//        });
    }
    void left(){
        left = true;
        right = false;
        up = false;
        down = false;
    }
    void right(){
        right = true;
        left = false;
        up = false;
        down = false;
    }
    void down(){
        down = true;
        up = false;
        left = false;
        right = false;
    }
    void up(){
        down = false;
        up = true;
        left = false;
        right = false;
    }

    test.GameState state = test.GameState.RUNNING;
    Apple apple = new Apple();
    int appleAmount = 0;
    int snakeLength;
    int currentX = 400;
    int currentY = 400;
    boolean up = false;
    boolean down = false;
    boolean right = true;
    boolean left = false;
    int unitSize = 20;
    int score = 0;
    ArrayList<Integer> snakePositionX = new ArrayList<>();
    ArrayList<Integer> snakePositionY = new ArrayList<>();
    JLabel scorePresent;

    public void init(){
        scorePresent = new JLabel("Score: " + score);
        add(scorePresent,BorderLayout.LINE_END);
        snakeLength = 3;
        for (int i = 0; i < snakeLength; i++) {
            snakePositionX.add(currentX - 20 * i);
            snakePositionY.add(currentY);
        }
    }

    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        drawApple(graphics);
        drawSnake(graphics);
        repaint();
        graphics.dispose();

    }

    public void drawApple(Graphics graphics){

        graphics.setColor(Color.green);
        graphics.fillRect(apple.positionX,apple.positionY,unitSize,unitSize);

    }

    public void drawSnake(Graphics graphics){
        for (int i = 0; i < snakeLength; i++) {
            graphics.setColor(Color.red);
            graphics.fillRect(snakePositionX.get(i), snakePositionY.get(i), unitSize, unitSize);

        }
    }

    public void eatApple(){

        if (appleAmount == 0){
            apple.createApple();
            appleAmount++;
        }

        if (snakePositionX.get(0) == apple.positionX && snakePositionY.get(0) == apple.positionY){
            appleAmount --;
            for (int i = 0; i < 3; i++){
               snakePositionX.add(-200);
               snakePositionY.add(-200);
            }
            snakeLength += 3;
            score++;

        }

    }

    public void edgeDetection(){

        if (snakePositionX.get(0) < 0 ){
            snakePositionX.set(0,800);
        }
        if (snakePositionY.get(0) < 0){
            snakePositionY.set(0,800);
        }
        if (snakePositionX.get(0) >  800){
            snakePositionX.set(0,0);
        }
        if (snakePositionY.get(0) > 800){
            snakePositionY.set(0,0);
        }
    }

    public void move (){

        currentX = snakePositionX.get(0);
        currentY = snakePositionY.get(0);
        if (up) {
            currentY -= unitSize;
        }
        else if (down){
            currentY += unitSize;
        }
        else if (left){
            currentX -= unitSize;
        }
        else if (right){
            currentX += unitSize;
        }
        snakePositionX.add(0, currentX);
        snakePositionX.remove(snakeLength);
        snakePositionY.add(0,currentY);
        snakePositionY.remove(snakeLength);
    }

    public test.GameState checkCollision(){

        for (int i = 1; i < snakeLength; i ++){
            if (snakePositionX.get(0).equals(snakePositionX.get(i)) && snakePositionY.get(0).equals(snakePositionY.get(i))){
                //if collides with itself
                state = test.GameState.GAMEOVER;
            }
        }
        return state;
    }
    public void updateLabel(){
        scorePresent = new JLabel("Score:" + score);
    }
}
