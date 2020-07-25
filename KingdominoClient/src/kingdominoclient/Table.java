package kingdominoclient;


public class Table {
    
    private int height;
    private int width;
    
    private Tile domino_tile[][];
    
    public Table(int height, int width){
        this.height = height;
        this.width = width;
        
        domino_tile = new Tile[width][width];
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public Tile getTile(int i, int j) {
        return this.domino_tile[i][j];
    }
    
    public void setDomino(int i, int j, Domino domino) {
        
        Orientation orientation = domino.getOrientation();
        if(orientation == Orientation.LEFT_RIGHT) {
            domino_tile[i][j] = domino.getTile1();
            domino_tile[i][j+1] = domino.getTile2();
        }else if (orientation == Orientation.UP_DOWN) {
            domino_tile[i][j] = domino.getTile1();
            domino_tile[i+1][j] = domino.getTile2();
        }else if (orientation == Orientation.RIGHT_LEFT) {
            domino_tile[i][j] = domino.getTile2();
            domino_tile[i][j+1] = domino.getTile1();
        }else{
            domino_tile[i][j] = domino.getTile2();
            domino_tile[i+1][j] = domino.getTile1();
        }
    }    
}
