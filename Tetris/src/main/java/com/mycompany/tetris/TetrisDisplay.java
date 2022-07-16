/*
 * This is the code that is responisble for the event listeners for telling the game which moves were made
 * "Space bar" is for pause
 * N is for New Game
 * (up) is for rotation
 * (down) arrow is for "drop"
 *D is for accelerating the fall
 *Pasang J Sherpa
 *4-20-2021
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

enum state{
    Menu,Play;
}
public class TetrisDisplay extends JPanel {
    public int BOARD_HEIGHT=22;
    public int BOARD_WIDTH=12;
    public int SQUARE_SIZE=20;
    public int win_Width=255;
    public int win_Height=480;
    private TetrisGame game;
    private int start_x;
    private int start_y;
    private int cell_size;
    private int squareHeight=2;
    private TetrisBrick currPiece;
    private final int PERIOD_INTERVAL = 300;
    private boolean hasReachedBottom =false;
    private static int linesRemoved =0;
    private JLabel score;
    private Timer t;
    boolean gameOver=false;
    private state State;
    private Menu menu;
    boolean isPaused=false;

    public TetrisDisplay(TetrisGame g){
        game=g;
        cell_size=2;
        game.spawnBrick();
        currPiece=game.falling_brick;
        start_x = BOARD_WIDTH / 2 + 1;
        start_y = BOARD_HEIGHT - 1 + currPiece.minY();
        if (!processMove(currPiece, start_x, start_y)) {
            currPiece=new NoBrick();
            var msg = String.format("Game over. Score: %d", linesRemoved *100);
            score.setText(msg);
            gameOver=true;
        }
        score=new JLabel();
        score.setLocation(0,500);
        add(score);
        menu=new Menu(this);
        State=state.Menu;
        add(menu);

    }
    public void startGame(){
        t=new Timer(PERIOD_INTERVAL,new GameCycle());
        t.start();
        this.addKeyListener(new TAdapter());
        setFocusable(true);
        State=state.Play;
        this.remove(menu);
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.setSize(win_Width,win_Height);

    }
    @Override
    public void paintComponent(Graphics g){
        if(!gameOver && State==state.Play) {
            score.setText("Score: " + linesRemoved * 100);
            super.paintComponent(g);

            int boardTop = (int) 2 * squareHeight;

            for (int x = 0; x < BOARD_HEIGHT; x++) {

                for (int y = 0; y < BOARD_WIDTH; y++) {
                    TetrisBrick shape = game.shapePresentAtLocation(y, BOARD_HEIGHT - x - 1);

                    if (!(shape instanceof NoBrick)) {
                        drawPieceOnBoard(g, y * SQUARE_SIZE, boardTop + x * SQUARE_SIZE, shape);

                    }
                }
            }

            if (!(currPiece instanceof NoBrick)) {

                for (int i = 0; i < 4; i++) {

                    int x = start_x + currPiece.x(i);
                    int y = start_y - currPiece.y(i);

                    drawPieceOnBoard(g, x * SQUARE_SIZE, boardTop + (BOARD_HEIGHT - y - 1) * SQUARE_SIZE, currPiece);
                }
            }
        }
        else if(gameOver){
            super.paintComponent(g);
            saveScoreToFile();
            t.stop();
        }
        else if(State==state.Menu){
            add(menu);
        }
    }
    private void drawPieceOnBoard(Graphics g, int x, int y, TetrisBrick shape) {
        Color color=null;
        if(shape.colorNum==1){
            color=Color.orange;
        }

        if(shape.colorNum==2){
            color=Color.yellow;
        }

        if(shape.colorNum==3){
            color=Color.green;
        }

        if(shape.colorNum==4){
            color=Color.red;
        }

        if(shape.colorNum==5){
            color=Color.BLUE;
        }

        if(shape.colorNum==6){
            color=new Color(102,0,153);
        }

        if(shape.colorNum==7){
            color=Color.CYAN;
        }

        g.setColor(color);
        g.fillRect(x + 1, y + 1, SQUARE_SIZE, SQUARE_SIZE);
        g.setColor(Color.black);
        g.drawLine(x + 1, y + SQUARE_SIZE - 1, x + SQUARE_SIZE - 1, y + SQUARE_SIZE - 1);
        g.drawLine(x + SQUARE_SIZE - 1, y + SQUARE_SIZE - 1, x +SQUARE_SIZE - 1, y + 1);
    }
    private boolean processMove(TetrisBrick newPiece, int newX, int newY) {
        if(game.validateMove(newPiece,newX,newY)) {
            currPiece = newPiece;
            start_x = newX;
            start_y = newY;

            repaint();
            return true;
        }else{
            return false;
        }

    }
    public void translateKey(int keycode){
       if(keycode==KeyEvent.VK_N){
           game.newGame();
           TetrisDisplay.linesRemoved =0;
       }
       else if(keycode==KeyEvent.VK_LEFT) {
           processMove(currPiece, start_x-1, start_y);
       }
       else if(keycode==KeyEvent.VK_RIGHT) {
           processMove(currPiece, start_x+1, start_y);
       }
       else if(keycode==KeyEvent.VK_UP) {
           processMove(game.rotate(), start_x, start_y);
       }
       else if(keycode==KeyEvent.VK_DOWN) {
           processMove(currPiece.unrotate(), start_x, start_y);
       }
       else if(keycode==KeyEvent.VK_D) {
           if (!processMove(currPiece, start_x, start_y - 1)) {

               for (int i = 0; i < 4; i++) {

                   int x = start_x + currPiece.x(i);
                   int y = start_y - currPiece.y(i);
                   game.transferColor(((y * BOARD_WIDTH) + x),currPiece);

               }

               score.setText("Score: "+ linesRemoved *100);
               int numFullLines = 0;

               for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {

                   boolean lineIsFull = true;

                   for (int j = 0; j < BOARD_WIDTH; j++) {

                       if (game.shapePresentAtLocation(j, i) instanceof NoBrick) {

                           lineIsFull = false;
                           break;
                       }
                   }

                   if (lineIsFull) {

                       numFullLines++;
                       score.setText("Score: "+ linesRemoved *100);
                       for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                           for (int j = 0; j < BOARD_WIDTH; j++) {
                               game.background[(k * BOARD_WIDTH) + j] = game.shapePresentAtLocation(j, k + 1);
                           }
                       }
                   }
               }

               if (numFullLines > 0) {

                   linesRemoved += numFullLines;
                   hasReachedBottom = true;
                   currPiece=new NoBrick();
               }

               if (!hasReachedBottom) {

                   game.spawnBrick();
                   currPiece=game.falling_brick;
                   start_x = BOARD_WIDTH / 2 + 1;
                   start_y = BOARD_HEIGHT - 1 + currPiece.minY();
                   if (!processMove(currPiece, start_x, start_y)) {

                       currPiece=new NoBrick();


                       var msg = String.format("Game over. Score: %d", linesRemoved *100);
                       score.setText(msg);
                       gameOver=true;
                   }
               }
           }
       }
       else if(keycode==KeyEvent.VK_X) {
           int newY = start_y;

           while (newY > 0) {

               if (!processMove(currPiece, start_x, newY - 1)) {

                   break;
               }

               newY--;
           }

           for (int i = 0; i < 4; i++) {

               int x = start_x + currPiece.x(i);
               int y = start_y - currPiece.y(i);
               game.transferColor(((y * BOARD_WIDTH) + x),currPiece);

           }

           score.setText("Score: "+ linesRemoved *100);
           int numFullLines = 0;

           for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {

               boolean lineIsFull = true;

               for (int j = 0; j < BOARD_WIDTH; j++) {

                   if (game.shapePresentAtLocation(j, i) instanceof NoBrick) {

                       lineIsFull = false;
                       break;
                   }
               }

               if (lineIsFull) {

                   numFullLines++;
                   score.setText("Score: "+ linesRemoved *100);
                   for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                       for (int j = 0; j < BOARD_WIDTH; j++) {
                           game.background[(k * BOARD_WIDTH) + j] = game.shapePresentAtLocation(j, k + 1);
                       }
                   }
               }
           }

           if (numFullLines > 0) {

               linesRemoved += numFullLines;

               // statusbar.setText(String.valueOf(numLinesRemoved));
               hasReachedBottom = true;
               currPiece=new NoBrick();
           }

           if (!hasReachedBottom) {

               game.spawnBrick();
               currPiece=game.falling_brick;
               start_x = BOARD_WIDTH / 2 + 1;
               start_y = BOARD_HEIGHT - 1 + currPiece.minY();
               if (!processMove(currPiece, start_x, start_y)) {

                   currPiece=new NoBrick();


                   var msg = String.format("Game over. Score: %d", linesRemoved *100);
                   score.setText(msg);
                   gameOver=true;
               }
           }
       }
       else if(keycode==KeyEvent.VK_SPACE) {
           if(isPaused){
               t.start();
               isPaused=false;
           }
           else{
               t.stop();
               isPaused=true;

           }
       }
    }
    private void saveScoreToFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt", true));
            writer.write(linesRemoved *100+"\n");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            if (hasReachedBottom) {

                hasReachedBottom = false;
                game.spawnBrick();
                currPiece=game.falling_brick;
                start_x = BOARD_WIDTH / 2 + 1;
                start_y = BOARD_HEIGHT - 1 + currPiece.minY();
                if (!processMove(currPiece, start_x, start_y)) {
                    currPiece=new NoBrick();
                    String msg = String.format("Game over. Score: %d", linesRemoved *100);
                    score.setText(msg);
                    gameOver=true;
                }
            } else {

                if (!processMove(currPiece, start_x, start_y - 1)) {

                    for (int i = 0; i < 4; i++) {

                        int x = start_x + currPiece.x(i);
                        int y = start_y - currPiece.y(i);
                        game.transferColor(((y * BOARD_WIDTH) + x),currPiece);

                    }

                    score.setText("Score: "+ linesRemoved *100);
                    int numFullLines = 0;

                    for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {

                        boolean lineIsFull = true;

                        for (int j = 0; j < BOARD_WIDTH; j++) {

                            if (game.shapePresentAtLocation(j, i) instanceof NoBrick) {

                                lineIsFull = false;
                                break;
                            }
                        }

                        if (lineIsFull) {

                            numFullLines++;
                            score.setText("Score: "+ linesRemoved *100);
                            for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                                for (int j = 0; j < BOARD_WIDTH; j++) {
                                    game.background[(k * BOARD_WIDTH) + j] = game.shapePresentAtLocation(j, k + 1);
                                }
                            }
                        }
                    }

                    if (numFullLines > 0) {

                        linesRemoved += numFullLines;
                        hasReachedBottom = true;
                        currPiece=new NoBrick();
                    }

                    if (!hasReachedBottom) {

                        game.spawnBrick();
                        currPiece=game.falling_brick;
                        start_x = BOARD_WIDTH / 2 + 1;
                        start_y = BOARD_HEIGHT - 1 + currPiece.minY();
                        if (!processMove(currPiece, start_x, start_y)) {

                            currPiece=new NoBrick();
                            var msg = String.format("Game over. Score: %d", linesRemoved *100);
                            score.setText(msg);
                            gameOver=true;
                        }
                    }
                }
            }
            repaint();
        }
    }
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (currPiece instanceof NoBrick) {

                return;
            }

            int keycode = e.getKeyCode();
            translateKey(keycode);
        }
    }

}
