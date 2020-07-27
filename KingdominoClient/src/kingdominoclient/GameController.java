package kingdominoclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;


public class GameController {
       
    private Client client;
    
    private Window window = new Window();
    private PanelTable panel_table;
    private PanelMenu panel_menu = new PanelMenu();
    private PanelMiniMenu mini_menu = new PanelMiniMenu();
    private PanelPlayerDetails panel_pd = new PanelPlayerDetails();
    private Player player;
    private Table table;
    private Domino preview_domino;
    private int dominos_r=0;
    private int pos_i, pos_j;
    private boolean mouse_lis_right=false;
    private boolean first_time=true;
    
    private ArrayList<Domino> domino_list_l = new ArrayList<>();
     private ArrayList<Domino> domino_list_r = new ArrayList<>();
   
    public GameController(Client client) {
        this.client = client;  
    }
    
    public void openWindow() {
        displayMenu();
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
                int id = Integer.parseInt(Client.fromServer());
                String name = Client.fromServer();
                String color = Client.fromServer();
                
                player = new Player(id, name, color);
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
        
        //Get number of players
        dominos_r = Integer.parseInt(Client.fromServer());
                
        panel_table = new PanelTable(table.getHeight(), table.getWidth(), dominos_r, player.getColor());
        
        window.add(panel_table, "Table");
        window.setPanel("Table");
        
        window.setJMenuBar(mini_menu);
        
        panel_table.displayPlayerData(player.getName(), player.getColorString(), player.getScore());
        
        play();
    }
    
    private void play() {
        
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
                
                if(mouse_lis_right==true){
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
                                preview_domino = domino_list_r.get(i);
                                panel_table.setPreviewDomino(preview_domino);
                                Client.toServer(i+"");
                                mouse_lis_right = false;
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
                if(!table.isCastleSetted()){
                    table.setCastle(pos_i, pos_j, player.getColor());
                    panel_table.loadTable(table);
                }else{
                    if(table.setDomino(pos_i, pos_j, preview_domino)){
                        panel_table.loadTable(table);
                        Client.toServer("move");
                        mouse_lis_right = true;
                        panel_table.disableOkButton();
                    }else
                        JOptionPane.showMessageDialog(window, "Wrong position");
                    }
            }
        });

        //First column right dominos
        String domino_parts[];
        
        for(int i=0; i<dominos_r; i++){
            domino_parts = Client.fromServer().split(",");
            Domino domino_r1 = new Domino(Integer.parseInt(domino_parts[0]), new Tile(domino_parts[1], Integer.parseInt(domino_parts[2])), new Tile(domino_parts[3], Integer.parseInt(domino_parts[4])));
            panel_table.setRightDomino(i, 0, domino_r1);
            domino_list_l.add(domino_r1);
        }

        for(int i=0; i<dominos_r; i++){
            domino_parts = Client.fromServer().split(",");
            Domino domino_r2 = new Domino(Integer.parseInt(domino_parts[0]), new Tile(domino_parts[1], Integer.parseInt(domino_parts[2])), new Tile(domino_parts[3], Integer.parseInt(domino_parts[4])));
            panel_table.setRightDomino(i, 2, domino_r2);
            domino_list_r.add(domino_r2);
        }

        if(first_time){
            preview_domino = domino_list_l.get(player.getId());
            panel_table.setPreviewDomino(preview_domino);
            first_time = false;
        }

        Client.fromServer();
        panel_table.enableOkButton();
        
        //Nextround
//        Client.fromServer();
    }
}
