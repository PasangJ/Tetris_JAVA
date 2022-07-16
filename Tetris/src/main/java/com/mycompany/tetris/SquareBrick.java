/*
 * This is the code for the Square Shape Tetris Brick
 *Pasang J Sherpa
 *4-20-2021
 */

public class SquareBrick extends TetrisBrick{

    public SquareBrick(){
        colorNum=5;
        numberOfSegments=2;
        initPostions();
    }
    @Override
    public void initPostions() {
        int[][]coords={{0, 0}, {1, 0}, {0, 1}, {1, 1}};

        for(int i=0;i<4;i++){
            for(int j=0;j<2;j++){
                position[i][j]=coords[i][j];
            }
        }
    }
    @Override

    int x(int index) {

        return position[index][0];
    }

    @Override

    int y(int index) {

        return position[index][1];
    }

    @Override
    TetrisBrick rotate() {
        return this;
    }

    @Override
    TetrisBrick unrotate() {
        return this;
    }
}
