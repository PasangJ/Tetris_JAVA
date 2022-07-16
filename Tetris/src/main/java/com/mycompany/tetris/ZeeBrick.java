/*
 * This is the code for the Zee Shape Tetris Brick
 *Pasang J Sherpa
 *4-20-2021
 */
public class ZeeBrick extends TetrisBrick{
    public ZeeBrick(){
        colorNum=7;
        numberOfSegments=2;
        initPostions();
    }
    @Override
    public void initPostions() {
        int[][]coords={{0, -1}, {0, 0}, {-1, 0}, {-1, 1}};

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
        if(orientation==0){
            int[][]coords={{0, -1}, {0, 0}, {1, -1}, {-1, 0}};

            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    position[i][j]=coords[i][j];
                }
            }
            numberOfSegments=3;
            orientation=1;
            return this;
        }
        else if(orientation==1){
            int[][]coords={{0, -1}, {0, 0}, {-1, 0}, {-1, 1}};

            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    position[i][j]=coords[i][j];
                }
            }
            orientation=0;
            numberOfSegments=2;
            return this;
        }

        return null;
    }

    @Override
    TetrisBrick unrotate() {
        if(orientation==0){

            int[][]coords={{0, -1}, {0, 0}, {-1, 0}, {-1, 1}};

            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    position[i][j]=coords[i][j];
                }
            }
            orientation=1;
            numberOfSegments=3;
            return this;
        }
        else if(orientation==1){
            int[][]coords={{0, -1}, {0, 0}, {1, -1}, {-1, 0}};

            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    position[i][j]=coords[i][j];
                }
            }
            orientation=0;
            numberOfSegments=2;
            return this;
        }

        return null;
    }
}
