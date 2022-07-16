/*
 * This is the code for the El Shape Tetris Brick
 *Pasang J Sherpa
 *4-20-2021
 */

public class ElBrick extends TetrisBrick{
    public ElBrick(){
        colorNum=1;
        numberOfSegments=2;
        initPostions();
    }
    @Override
    public void initPostions() {
        int[][]coords={{-1, -1}, {0, -1}, {0, 0}, {0, 1}};

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
            int[][]coords={{-2, 0}, {-1, 0}, {0, 0}, {0, -1}};

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
            int[][]coords={{0, -2}, {0, -1}, {0, 0}, {1, 0}};

            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    position[i][j]=coords[i][j];
                }
            }
            orientation=2;numberOfSegments=2;
            return this;
        }
        else if(orientation==2){
            int[][]coords={{1, 0}, {2, 0}, {0, 0}, {0, 1}};

            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    position[i][j]=coords[i][j];
                }
            }
            orientation=3;
            numberOfSegments=3;
            return this;
        }
        else if(orientation==3){
            int[][]coords={{-1, -1}, {0, -1}, {0, 0}, {0, 1}};

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
            int[][]coords={{-1, -1}, {0, -1}, {0, 0}, {0, 1}};

            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    position[i][j]=coords[i][j];
                }
            }
            orientation=3;
            numberOfSegments=3;
            return this;
        }
        else if(orientation==1){
            int[][]coords={{-2, 0}, {-1, 0}, {0, 0}, {0, -1}};

            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    position[i][j]=coords[i][j];
                }
            }
            orientation=0;
            numberOfSegments=2;
            return this;
        }
        else if(orientation==2){
            int[][]coords={{1, 0}, {2, 0}, {0, 0}, {0, 1}};

            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    position[i][j]=coords[i][j];
                }
            }
            orientation=1;
            numberOfSegments=3;
            return this;
        }
        else if(orientation==3){
            int[][]coords={{1, 0}, {2, 0}, {0, 0}, {0, 1}};

            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    position[i][j]=coords[i][j];
                }
            }
            orientation=2;
            numberOfSegments=2;
            return this;
        }
        return null;
    }
}
