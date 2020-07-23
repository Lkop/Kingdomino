package kingdominoclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;


public class GameController {
       
    private Client client;
    
    private Window window = new Window();
    private PanelTable panel_table;
    private PanelMenu panel_menu = new PanelMenu();
    private PanelPlayerDetails panel_pd = new PanelPlayerDetails();
    private Player player;
    private Table table;
   
    
    public GameController(Client client) {
        this.client = client;  
    }
    
    public void openWindow() {
//        displayMenu();

        //Debugging
        player = new Player("a", "b");
        displayTable();
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
                Client.toServer(panel_pd.getName()+","+panel_pd.getColor());
                
                //Players data response from server
                String name = Client.fromServer();
                String color = Client.fromServer();
                
                player = new Player(name, color);
                System.out.println(player.getName()+" - "+player.getColor());

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
        
        panel_table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                //Get position from JLabel
                for(int i=0; i<table.getHeight(); i++) {
                    for(int j=0; j<table.getWidth(); j++) {
                        if(((JLabel)e.getSource()) == panel_table.getViewTile(i, j)){
                            System.out.println(i+" - "+j);
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

        panel_table.setTileImage(0, 0, "lake1c");
        
        Domino domino = new Domino(1, new Tile("farm", 0), new Tile("lake", 0));
        panel_table.setPreviewDomino(domino);
        
        
        play();
    }
    
    private void play() {
        //client.toServer("reeeeeee");
        //System.out.println(client.fromServer());  
    }
    
    public void setPlayerData(String name, String color) {
        player = new Player(name, color);
    }
}
