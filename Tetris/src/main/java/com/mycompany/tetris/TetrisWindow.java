/*
 * This is the solution to the Tetris Part 1 for CSCI282
 *More advanced study on creating shapes on java
 *Creation of a GUI based game related with shapes
 *This runs the tetris game(contains main method)
 Pasang J Sherpa
 4-20-2021

/**
 *
 * @author pasangsherpa
 */
import javax.swing.*;

public class TetrisWindow extends JFrame {
    TetrisDisplay display;
    TetrisGame game;
    int win_Width=300;
    int win_Height=480;

    public TetrisWindow(){
        game=new TetrisGame();
        display=new TetrisDisplay(game);

        setTitle("Tetris");
        setSize(win_Width, win_Height);
        add(display);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[]args){
        TetrisWindow window=new TetrisWindow();
    }
}
