package kingdominoclient;


public class Domino {
    
    private int id;
    private String left;
    private String right;
    private int crowns;
    
    public Domino(int id, String left, String right, int crowns){
        this.id = id;
        this.left = left;
        this.right = right;
        this.crowns = crowns;
    }
    
}
