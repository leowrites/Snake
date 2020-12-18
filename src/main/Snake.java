package main;
import javax.swing.*;
import java.awt.*;

public class Snake extends JFrame {
    /*
    Snake
    Started: 2020-12-14
    Completed: 2020-12-15
    Leo Liu
     */
    // Launcher of the game

    Snake() {
        super("Snake By Leo");
        Dimension dimension = new Dimension(800, 800);
        setSize(dimension);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
