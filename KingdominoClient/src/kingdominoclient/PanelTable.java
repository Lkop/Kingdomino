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
    
    private JLabel name, color, score;
    
    private int table_h, table_w, dominos_r;
    private Color color_c;
    
    private JLabel tiles[][];
    
    private Domino preview_domino;
    private JLabel preview_tiles[][];
    
    private JLabel right_tiles[][];
    
    private JButton ok_button;
    
    public PanelTable(int table_h, int table_w, int dominos_r, Color color) {   
        this.table_h = table_h;
        this.table_w = table_w;
        this.dominos_r = dominos_r;
        this.color_c = color;
        
        setLayout(new BorderLayout());
        add(createPlayerData(), BorderLayout.NORTH);
        add(createPanelTable(), BorderLayout.CENTER);
        add(createRightDominos(), BorderLayout.EAST);
        add(createPreviewDomino(), BorderLayout.SOUTH);
    }
    
    private JPanel createPlayerData() {
        JPanel panel = new JPanel(new FlowLayout());
        
        name = new JLabel();
        color = new JLabel();
        score = new JLabel();
        
        panel.add(name);
        panel.add(color);
        panel.add(score);
        
        panel.setSize(new Dimension(100,100));
        
        return panel;
    }

    private JPanel createPanelTable() {
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(table_h,table_w));
        
        tiles = new JLabel[table_h][table_w];

        for(int i=0; i<table_h; i++) {
            for(int j=0; j<table_w; j++) {
                
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
        ok_button.setEnabled(false);
        
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
        inner[0] = new JPanel(new GridLayout(dominos_r,1,0,10));
        inner[1] = new JPanel(new GridLayout(dominos_r,1,0,10));
        
        right_tiles = new JLabel[dominos_r][4];

        for(int i=0; i<dominos_r; i++) {
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

        for(int i=0; i<dominos_r; i++) {
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
                if(tile != null){
                    if(tile.getImage().equals("castle")){
                        tiles[i][j].setBackground(color_c);
                        tiles[i][j].setOpaque(true);
                    }else{
                        setTileImage(i, j, tile.getImage()); 
                    } 
                }
            }
        }
    }
        
    public void setTileImage(int i, int j, String image) {  
        tiles[i][j].setIcon(new ImageIcon(new ImageIcon("tiles/"+image+".png").getImage().getScaledInstance(tiles[i][j].getWidth(), tiles[i][j].getHeight(), java.awt.Image.SCALE_SMOOTH)));
//        tiles[i][j].setIcon(new ImageIcon(image));
    }
    
    public void addMouseListener(String type, MouseListener ml) {
        if(type.equals("right")){
            for(int i=0; i<dominos_r; i++) {
                    for(int j=2; j<4; j++) {
                       right_tiles[i][j].addMouseListener(ml); 
                    }
                }
            return;
        }
        
        for(int i=0; i<table_h; i++) {
            for(int j=0; j<table_w; j++) {
               tiles[i][j].addMouseListener(ml); 
            }
        }    
    }

    public JLabel getViewTile(String type, int i, int j) {
        if(type.equals("right"))
            return right_tiles[i][j];

        return tiles[i][j];
    }
    
    public void setPreviewDomino(Domino domino){
        this.preview_domino = domino;       
        preview_tiles[0][0].setIcon(resizeImage(domino.getTile1().getImage(), preview_tiles[0][0]));
        preview_tiles[0][1].setIcon(resizeImage(domino.getTile2().getImage(), preview_tiles[0][1]));
    }
    
    public void enableOkButton() {
        ok_button.setEnabled(true);
    }
    
    public void disableOkButton() {
        ok_button.setEnabled(false);
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
    
    public void displayPlayerData(String name, String color, int score) {
        this.name.setText(name);
        this.color.setText(color);
        this.score.setText(score+"");
    }
    
    public void selectRightDomino(int pos) {
    
        
    }

}
