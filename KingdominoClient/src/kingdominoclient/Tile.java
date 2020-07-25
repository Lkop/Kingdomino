package kingdominoclient;


public class Tile {
    
    private String image;
    private int crowns;
    
    public Tile(String image, int crowns) {
        this.image = image;
        this.crowns = crowns;
    }
    
    public String getImage() {
        return this.image;
    }
        
    public int getCrowns() {
        return this.crowns;
    }
}
