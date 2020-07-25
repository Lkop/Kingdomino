package kingdominoclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;


public class PanelTable extends JPanel{
    
    private Table table;
    private JLabel tiles[][];
    
    private Domino preview_domino;
    private JLabel preview_tiles[][];
    
    private JLabel right_tiles[][];
    
    private JButton ok_button;
    
    public PanelTable(Table table) {   
        this.table = table;
        
        setLayout(new BorderLayout());
        add(createPanelTable(), BorderLayout.CENTER);
        add(createRightDominos(), BorderLayout.EAST);
        add(createPreviewDomino(), BorderLayout.SOUTH);
    }
        
    private JPanel createPanelTable() {
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(table.getHeight(),table.getWidth()));
        
        tiles = new JLabel[table.getHeight()][table.getWidth()];

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
                
                panel.add(tiles[i][j]);
            }
        }
        return panel;
    }
    
    private JPanel createPreviewDomino() {
                
        JPanel panel = new JPanel(new FlowLayout());
        
        JPanel domino = new JPanel(new GridLayout(2,2));

        preview_tiles = new JLabel[2][2];
        
        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                preview_tiles[i][j] = new JLabel();
                preview_tiles[i][j].setMinimumSize(new Dimension(100, 100));
                preview_tiles[i][j].setPreferredSize(new Dimension(100, 100));
                preview_tiles[i][j].setMaximumSize(new Dimension(100, 100));
                
                domino.add(preview_tiles[i][j]);
            }
        }
        panel.add(domino);
               
        JButton b1 = new JButton("Rotate");
        JButton b2 = new JButton("Occupied Position");
        ok_button = new JButton("Set Domino");
        
        panel.add(b1);
        panel.add(b2);
        panel.add(ok_button);
        
        //Rotate right every time
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {             
                rotateDominoClockwise();               
            }
        });
        
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        return panel;
    }
    
     private JPanel createRightDominos() {
        JPanel dominos = new JPanel(new GridLayout(1,2,20,0));
        
        //Left
        JPanel inner[] = new JPanel[2];
        inner[0] = new JPanel(new GridLayout(4,1,0,10));
        inner[1] = new JPanel(new GridLayout(4,1,0,10));
        
        right_tiles = new JLabel[4][4];

        for(int i=0; i<4; i++) {
            for(int j=0; j<2; j++) {

                right_tiles[i][j] = new JLabel(i+" = "+j);
                right_tiles[i][j].setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
                right_tiles[i][j].setMinimumSize(new Dimension(100, 100));
                right_tiles[i][j].setPreferredSize(new Dimension(100, 100));
                right_tiles[i][j].setMaximumSize(new Dimension(100, 100));

                inner[0].add(right_tiles[i][j]);
            }
        }
        dominos.add(inner[0]);

        for(int i=0; i<4; i++) {
            for(int j=2; j<4; j++) {

                right_tiles[i][j] = new JLabel(i+" = "+j);
                right_tiles[i][j].setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
                right_tiles[i][j].setMinimumSize(new Dimension(100, 100));
                right_tiles[i][j].setPreferredSize(new Dimension(100, 100));
                right_tiles[i][j].setMaximumSize(new Dimension(100, 100));

                inner[1].add(right_tiles[i][j]);
            }
        }
        dominos.add(inner[1]);
        
        return dominos;
    }
     
    public void loadTable(Table table) {
        for(int i=0; i<table.getHeight(); i++) {
            for(int j=0; j<table.getWidth(); j++) {
                
                Tile tile = table.getTile(i, j);
                if(tile != null)
                    setTileImage(i, j, tile.getImage());   
            }
        }
    }
        
    public void setTileImage(int i, int j, String image) {  
        tiles[i][j].setIcon(new ImageIcon(new ImageIcon("tiles/"+image+".png").getImage().getScaledInstance(tiles[i][j].getWidth(), tiles[i][j].getHeight(), java.awt.Image.SCALE_SMOOTH)));
//        tiles[i][j].setIcon(new ImageIcon(image));
    }
    
    public void addMouseListener(MouseListener ml) {
        for(int i=0; i<table.getHeight(); i++) {
            for(int j=0; j<table.getWidth(); j++) {
               tiles[i][j].addMouseListener(ml); 
            }
        }    
    }
    
    public JLabel getViewTile(int i, int j) {
        return tiles[i][j];
    }
    
    public void setPreviewDomino(Domino domino){
        this.preview_domino = domino;       
        preview_tiles[0][0].setIcon(new ImageIcon("tiles/"+preview_domino.getTile1().getImage()+".png"));
        preview_tiles[0][1].setIcon(new ImageIcon("tiles/"+preview_domino.getTile2().getImage()+".png"));
    }
        
    public void addOkButtonListener(ActionListener al) {
        ok_button.addActionListener(al);
    }
    
    public void setRightDomino(int i, int j, Domino domino) {  
        if(j == 0 || j == 2) {
            right_tiles[i][j].setIcon(resizeImage(domino.getTile1().getImage(), right_tiles[i][j]));
            right_tiles[i][j+1].setIcon(resizeImage(domino.getTile2().getImage(), right_tiles[i][j+1]));
        }
    }
    
    private ImageIcon resizeImage(String image, JLabel label_tile) {
        return new ImageIcon(new ImageIcon("tiles/"+image+".png").getImage().getScaledInstance(label_tile.getWidth(), label_tile.getHeight(), java.awt.Image.SCALE_DEFAULT));
    }
    
    private void rotateDominoClockwise() {
        preview_domino.changeOrientationClockwise();
        
        //Clear domino preview
        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                preview_tiles[i][j].setIcon(null);
            }
        }
        
        Orientation orientation = preview_domino.getOrientation();
        if(orientation == Orientation.LEFT_RIGHT) {
            preview_tiles[0][0].setIcon(new ImageIcon("tiles/"+preview_domino.getTile1().getImage()+".png"));
            preview_tiles[0][1].setIcon(new ImageIcon("tiles/"+preview_domino.getTile2().getImage()+".png"));
        }else if(orientation == Orientation.UP_DOWN) {
            preview_tiles[0][1].setIcon(new ImageIcon("tiles/"+preview_domino.getTile1().getImage()+".png"));
            preview_tiles[1][1].setIcon(new ImageIcon("tiles/"+preview_domino.getTile2().getImage()+".png"));
        }else if(orientation == Orientation.RIGHT_LEFT) {
            preview_tiles[0][0].setIcon(new ImageIcon("tiles/"+preview_domino.getTile2().getImage()+".png"));
            preview_tiles[0][1].setIcon(new ImageIcon("tiles/"+preview_domino.getTile1().getImage()+".png"));
        }else{
            preview_tiles[0][1].setIcon(new ImageIcon("tiles/"+preview_domino.getTile2().getImage()+".png"));
            preview_tiles[1][1].setIcon(new ImageIcon("tiles/"+preview_domino.getTile1().getImage()+".png"));
        }
    }
}
