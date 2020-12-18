package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class test {

    GameState state;
    Snake newFrame;
    Welcome welcome;
    GamePanel newGamePanel;
    int score = 0;

    boolean windowState = true;

    public enum GameState{
        START,RUNNING,GAMEOVER;
    }

    test(){

        init();

    }
    void init(){
        newFrame = new Snake();
        state = GameState.START;
        newFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                windowState = false;
            }
        });
    }
    void loadStartScreen(){
        //maybe initialize a new welcome first?

        welcome = new Welcome(score);
        newFrame.getContentPane().add(welcome);
    }


    public static void main(String[] args) throws InterruptedException {

        test game1 = new test();

        while (game1.windowState) {
            //loop through to check the state of the game
            //listens to the window to detect whether the window is closed to terminate the while loop
            switch (game1.state) {

                case START:
                    //load start screen
                    game1.loadStartScreen();
                    game1.newFrame.revalidate();
                    while (game1.state == GameState.START) {
                        //need to receive state change from action performed
                        Thread.sleep(100);
                        game1.state = game1.welcome.returnState();
                    }
                    System.out.println(game1.state);
                    //need to wait until action performed
                    break;

                case RUNNING:
                    //main game loop, should not enter until action performed
                    //pass score from GamePanel to Welcome
                    System.out.println("In RUNNING");
                    game1.newGamePanel = new GamePanel();
                    game1.newFrame.getContentPane().removeAll();
                    game1.newFrame.getContentPane().add(game1.newGamePanel);
                    game1.newFrame.repaint();
                    while (game1.state == GameState.RUNNING) {

                        try {
                            Thread.sleep(50);

                            game1.newGamePanel.move();
                            game1.newGamePanel.edgeDetection();
                            //should receive state from checkCollision
                            game1.state = game1.newGamePanel.checkCollision();
                            //refreshes every 50 milliseconds
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }

                        game1.newGamePanel.eatApple();
                    }
                    break;

                case GAMEOVER:
                    game1.score = game1.newGamePanel.score;
                    game1.newFrame.getContentPane().removeAll();
                    game1.state = GameState.START;
                    break;
            }
        }

    }
}
