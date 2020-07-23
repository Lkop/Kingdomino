package kingdominoclient;


public class Domino {
    
    private int id;
    private Tile tile_1;
    private Tile tile_2;
    private Orientation orientation = Orientation.LEFT_RIGHT;
    
    public Domino(int id, Tile tile_1, Tile tile_2){
        this.id = id;
        this.tile_1 = tile_1;
        this.tile_2 = tile_2;
    }
    
    public Tile getTile1() {
        return this.tile_1;
    }
    
    public Tile getTile2() {
        return this.tile_2;
    }
    
    public Orientation getOrientation() {
        return this.orientation;
    }
    
    public void changeOrientationClockwise() {
        if(orientation == Orientation.LEFT_RIGHT)
            orientation = Orientation.UP_DOWN;
        else if(orientation == Orientation.UP_DOWN)
            orientation = Orientation.RIGHT_LEFT;
        else if(orientation == Orientation.RIGHT_LEFT)
            orientation = Orientation.DOWN_UP;
        else
            orientation = Orientation.LEFT_RIGHT;
    }
}
