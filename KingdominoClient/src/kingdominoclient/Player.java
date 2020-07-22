package kingdominoclient;


public class Player {
    
    private String name;
    private String color;
    private Table table;
    
    public Player(String name, String color){
        this.name = name;
        this.color = color;
        this.table = new Table(9,9);
   }
    
    public String getName(){
        return this.name;
    }
    
    public String getColor(){
        return this.color;
    }
    
    public Table getTable() {
        return table;
    }
}
