package kingdominoclient;

import java.awt.Color;


public class Player {
    
    private String name;
    private String color_str;
    private Color color;
    private Table table;
    
    public Player(String name, String color){
        this.name = name;
        this.color_str = color;
        this.color = strToColor(color);
        this.table = new Table(9,9);
   }
    
    public String getName(){
        return this.name;
    }
    
    public String getColorString(){
        return this.color_str;
    }
    
    public Table getTable() {
        return this.table;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    private Color strToColor(String color) {
        switch(color) { 
            case "Red": 
                return Color.RED;
            case "Green": 
               return Color.GREEN;
            case "Yellow": 
                return Color.YELLOW;
            case "Blue": 
                return Color.BLUE;
        }
        return null;
    }
}
