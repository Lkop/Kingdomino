package kingdominoclient;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;


public class PanelTable extends JPanel{
    
    private Table table;
    
    public PanelTable(Table table) {   
        this.table = table;
        
        createPanelTable();
    }
        
    private void createPanelTable() {
        setLayout(new GridLayout(table.getHeight(),table.getWidth()));
        
        JLabel tiles[][] = new JLabel[table.getHeight()][table.getWidth()];

        for(int i=0; i<table.getHeight(); i++) {
            for(int j=0; j<table.getWidth(); j++) {
                
                tiles[i][j] = new JLabel();
                
                //Adding pretty border
                Border border;
                if(i==0 && j==0)
                    border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
                else if(i==0)
                    border = new MatteBorder(1, 0, 1, 1, Color.BLACK);
                else if(j==0)    
                    border = new MatteBorder(0, 1, 1, 1, Color.BLACK);
                else
                    border = new MatteBorder(0, 0, 1, 1, Color.BLACK);
                
                tiles[i][j].setBorder(border);
                
                add(tiles[i][j]);
            }
        }
    }
    
}
