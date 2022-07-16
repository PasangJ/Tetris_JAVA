/*
 * This is the code for the Long Shape Tetris Brick
 *Pasang J Sherpa
 *4-20-2021
 */
public class LongShape extends TetrisBrick{

    public LongShape(){
        colorNum=4;
        numberOfSegments=1;
        initPostions();
    }
    @Override
    public void initPostions() {
        int[][]coords={{0, -1}, {0, 0}, {0, 1}, {0, 2}};

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
        if(orientation==0) {

            this.position = new int[][]{{-1, 0}, {0, 0}, {1, 0}, {2, 0}};
            orientation=1;
            numberOfSegments=4;
            return this;
        }
        if(orientation==1){

            this.position = new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}};
            orientation=0;
            numberOfSegments=1;
            return this;

        }
        return null;
    }

    @Override
    TetrisBrick unrotate() {
        if(orientation==0) {

            this.position = new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}};
            orientation=1;
            numberOfSegments=4;
            return this;

        }
        if(orientation==1){
            this.position = new int[][]{{-1, 0}, {0, 0}, {1, 0}, {2, 0}};
            orientation=0;
            numberOfSegments=1;
            return this;

        }
        return null;
    }
}
