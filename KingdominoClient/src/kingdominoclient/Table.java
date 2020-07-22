package kingdominoclient;


public class Table {
    
    private int height;
    private int width;
    
    private TableData[][] data;
    
    public Table(int height, int width){
        this.height = height;
        this.width = width;
        
        data = new TableData[width][width];
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    
//    public createTable(){
//        JPanel a = new JPanel(new GridLayout(5,5));
//        JButton b[][] = new JButton[5][5];
//
//        for (int i = 0; i < 5; i++)
//            for (int j = 0; j < 5; j++){
//                b[i][j] = new JButton(new ImageIcon("farm.png"));
//                a.add(b[i][j]);
//            }
//        return a;
//    }
    
}
