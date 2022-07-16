/*
 * This is the code that contain all attributes and seven of all seven bricks
 *Pasang J Sherpa
 *4-20-2021

*/
public abstract class TetrisBrick {


    int[][] position=new int[4][2];
    int orientation=0;
    int numberOfSegments;
    int colorNum;

    public TetrisBrick() {
    }

    public abstract void initPostions();
    int minY() {

        int m = position[0][1];

        for (int i = 0; i < 4; i++) {

            m = Math.min(m, position[i][1]);
        }

        return m;
    }

    public int minX() {

        int m = position[0][0];

        for (int i = 0; i < 4; i++) {

            m = Math.min(m, position[i][0]);
        }

        return m;
    }

    abstract int x(int index);

    abstract int y(int index);

    private void setX(int index, int x) {

        position[index][0] = x;
    }

    private void setY(int index, int y) {

        position[index][1] = y;
    }


    abstract TetrisBrick rotate();
    abstract TetrisBrick unrotate();

    public TetrisBrick moveUp(){
        for(int i=0;i<4;i++){
            position[i][1]--;
        }
        return this;
    }
    public TetrisBrick moveDown(){
        for(int i=0;i<4;i++){
            position[i][1]--;
        }
        return this;
    }
    public TetrisBrick moveLeft(){
        for(int i=0;i<4;i++){
            position[i][0]--;
        }
        return this;
    }
    public TetrisBrick moveRight(){
        for(int i=0;i<4;i++){
            position[i][0]++;
        }
        return this;
    }


}
