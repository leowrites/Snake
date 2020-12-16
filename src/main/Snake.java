package main;
import javax.swing.*;
import java.awt.*;

public class Snake extends JFrame {
    // Launcher of the game

    Snake(){

        Dimension dimension = new Dimension(800, 800);
        setSize(dimension);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        while (true) {
            Snake newSnake = new Snake();
            GamePanel newGame = new GamePanel();
            newSnake.getContentPane().add(newGame);
            newGame.game();
            if (!newGame.inGame) {
                newSnake.dispose();
                newSnake = new Snake();
                newGame = new GamePanel();
                newSnake.getContentPane().add(newGame);
                newGame.game();
            }
        }
    }

}
