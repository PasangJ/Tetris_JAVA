/*
 * This is the code for the No Shape Tetris Brick
 *Pasang J Sherpa
 *4-20-2021
 */
public class NoBrick extends TetrisBrick{
    public NoBrick(){
        initPostions();
    }
    @Override
    public void initPostions() {
        int[][]coords={{0, 0}, {0, 0}, {0, 0}, {0, 0}};

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
        return null;
    }

    @Override
    TetrisBrick unrotate() {
        return null;
    }
}
