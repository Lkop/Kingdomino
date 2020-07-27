package kingdominoserver;


public class Player {
    
    private int id;
    private String name;
    private String color;
    
    public Player(int id, String name, String color){
        this.id = id;
        this.name = name;
        this.color = color;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getColor() {
        return this.color;
    }
}
