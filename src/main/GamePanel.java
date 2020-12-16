package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel{

    GamePanel(){
        init();
        setSize(800,800);
        setBackground(Color.blue);
        setFocusable(true);
        System.out.println(isFocusable());
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                int keyCode = e.getKeyCode();
                switch(keyCode){

                    case KeyEvent.VK_UP:
                        up = true;
                        down = false;
                        left = false;
                        right = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = true;
                        up = false;
                        left = false;
                        right = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        left = true;
                        right = false;
                        up = false;
                        down = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = true;
                        left = false;
                        up = false;
                        down = false;
                        break;

                }
            }
        });
    }

    Apple apple = new Apple();
    int appleAmount = 0;
    boolean inGame = true;
    int snakeLength;
    int currentX = 400;
    int currentY = 400;
    boolean up = false;
    boolean down = false;
    boolean right = true;
    boolean left = false;
    int unitSize = 20;
    ArrayList<Integer> snakePositionX = new ArrayList<>();
    ArrayList<Integer> snakePositionY = new ArrayList<>();

    public void game() {

        while (inGame) {
            try {
                Thread.sleep(50);
                move();
                edgeDetection();
                checkCollision();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            eatApple();
        }
    }

    public void init(){

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

    public void checkCollision(){
        if (snakePositionX.get(0).equals(snakePositionX.get(snakePositionX.size()-1)) && snakePositionY.get(0).equals(snakePositionY.get(snakePositionX.size()-1))){
            inGame = false;
        }
    }

}
