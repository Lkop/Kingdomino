package kingdominoclient;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;


public class GameController {
       
    private Client client;
    
    private Window window = new Window();
    private PanelTable panel_table;
    private PanelMenu panel_menu = new PanelMenu();
    private PanelPlayerDetails panel_pd = new PanelPlayerDetails();
    private Player player;
    private Table table;
    private int pos_i, pos_j;
   
    public GameController(Client client) {
        this.client = client;  
    }
    
    public void openWindow() {
        displayMenu();

//        //Debugging
//        player = new Player("Test", "Blue");
//        displayTable();
    }
    
    public void displayMenu() {
        window.add(panel_menu, "Menu");
        window.setPanel("Menu");
        
        panel_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPlayerDetails();
            }
        });
    }
    
    public void displayPlayerDetails() {
        window.add(panel_pd, "PlayerDetails");
        window.setPanel("PlayerDetails");
        
        panel_pd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(e.getSource() == panel_pd.getStartButton())
                    Client.toServer(panel_pd.getName()+","+panel_pd.getColor()+",no");
                else
                    Client.toServer(panel_pd.getName()+","+panel_pd.getColor()+",yes");
                
                //Players data response from server
                String name = Client.fromServer();
                String color = Client.fromServer();
                
                player = new Player(name, color);
                System.out.println(player.getName()+" - "+player.getColorString());

                //Wait to start the game
                String res = Client.fromServer();
                if(res.equals("start"))
                    displayTable();
            }
        });
    }
    
    public void displayTable() {
        table = player.getTable();
        panel_table = new PanelTable(table);
        
        window.add(panel_table, "Table");
        window.setPanel("Table");
        
        play();
    }
    
    private void play() {
        //First column right dominos
        String domino_parts[];
        for(int i=0; i<4; i++){
            domino_parts = Client.fromServer().split(",");
            Domino domino_r = new Domino(Integer.parseInt(domino_parts[0]), new Tile(domino_parts[1], Integer.parseInt(domino_parts[2])), new Tile(domino_parts[3], Integer.parseInt(domino_parts[4])));
            panel_table.setRightDomino(i, 0, domino_r);
        }

        for(int i=0; i<4; i++){
            domino_parts = Client.fromServer().split(",");
            Domino domino_r = new Domino(Integer.parseInt(domino_parts[0]), new Tile(domino_parts[1], Integer.parseInt(domino_parts[2])), new Tile(domino_parts[3], Integer.parseInt(domino_parts[4])));
            panel_table.setRightDomino(i, 2, domino_r);
        }
        
        Domino domino = new Domino(1, new Tile("farm", 0), new Tile("lake1c", 0));
        panel_table.setPreviewDomino(domino);
        
                
        panel_table.addMouseListener("table", new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                //Get position from JLabel
                for(int i=0; i<table.getHeight(); i++) {
                    for(int j=0; j<table.getWidth(); j++) {
                        if(((JLabel)e.getSource()) == panel_table.getViewTile("table", i, j)){
                            pos_i = i;
                            pos_j = j;
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        panel_table.addMouseListener("right", new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                //Get position from JLabel
                for(int i=0; i<4; i++) {
                    for(int j=2; j<4; j++) {
                        if(((JLabel)e.getSource()) == panel_table.getViewTile("right", i, j)){
                            if(j==2) {
                                panel_table.getViewTile("right", i, j).setBorder(new MatteBorder(2, 2, 2, 0, player.getColor()));
                                panel_table.getViewTile("right", i, j+1).setBorder(new MatteBorder(2, 0, 2, 2, player.getColor()));   
                            }else{
                                panel_table.getViewTile("right", i, j-1).setBorder(new MatteBorder(2, 2, 2, 0, player.getColor()));
                                panel_table.getViewTile("right", i, j).setBorder(new MatteBorder(2, 0, 2, 2, player.getColor()));   
                            }
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        panel_table.addOkButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
                if(table.setDomino(pos_i, pos_j, domino))
                    panel_table.loadTable(table);
                else
                    JOptionPane.showMessageDialog(window, "Wrong position");
            }
        });
    }
    
    public void setPlayerData(String name, String color) {
        player = new Player(name, color);
    }
}
