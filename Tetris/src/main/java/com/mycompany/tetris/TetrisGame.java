/*
 * This code is responsible for all the logic such as
 * creating random shape
 *Pasang J Sherpa
 *4-20-2021

/**
 *
 * @author pasangsherpa
 */

import java.util.Random;

public class TetrisGame {
    TetrisBrick falling_brick;
    TetrisBrick[]background;
    public int BOARD_HEIGHT=22;
    public int BOARD_WIDTH=12;
    Random randomGen =new Random();

    public TetrisGame(){

        spawnBrick();
        initBoard();
    }
    public void initBoard(){
        background=new TetrisBrick[BOARD_HEIGHT*BOARD_WIDTH];
        for(int i=0;i<BOARD_HEIGHT*BOARD_WIDTH;i++){
            background[i]=new NoBrick();
        }
    }
    public void spawnBrick(){

        int r= randomGen.nextInt(7);
        if(r==0){
            falling_brick=new ElBrick();
        }
        else if(r==1){
            falling_brick=new EssBrick();
        }
        else if(r==2){
            falling_brick=new SquareBrick();
        }
        else if(r==3){
            falling_brick=new JayBrick();
        }
        else if(r==4){
            falling_brick=new LongShape();
        }
        else if(r==5){
            falling_brick=new ZeeBrick();
        }
        else if(r==6){
            falling_brick=new StackBrick();
        }

    }
    public void makeMove(int moveCode){
        if(moveCode==1){
            falling_brick.moveUp();
        }
        else if(moveCode==2){
            falling_brick.moveDown();
        }
        else if(moveCode==3){
            falling_brick.moveLeft();
        }
        else if(moveCode==4){
            falling_brick.moveRight();
        }
    }
    public boolean validateMove(TetrisBrick newPiece, int newX, int newY){
        for (int i = 0; i < 4; i++) {

            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {

                return false;
            }

            if (!(shapePresentAtLocation(x, y) instanceof NoBrick)) {

                return false;
            }
        }
        return true;
    }
    public void newGame(){
        initBoard();
    }
    public TetrisBrick rotate(){
        return falling_brick.rotate();
    }
    public TetrisBrick shapePresentAtLocation(int x, int y) {

        return background[(y*BOARD_WIDTH) + x];
    }
    public void transferColor(int i, TetrisBrick currPiece) {
        background[i]=currPiece;
    }


}
