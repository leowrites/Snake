package main;

import java.util.Random;

public class Apple{

    Random generator = new Random();
    int positionX;
    int positionY;

    public void createApple(){

        positionX = 20 * generator.nextInt(30); //only multiplication of 20
        positionY = 20 * generator.nextInt(30);

    }

}
