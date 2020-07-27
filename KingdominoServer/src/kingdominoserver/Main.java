package kingdominoserver;

import java.util.ArrayList;
import java.util.Collections;


public class Main {
    
    public static void main(String[] args) {
        
        Server server = new Server();
        
        //Parse domino.txt
        DominoParser dp = new DominoParser();
        
        //Get dominos list
        ArrayList<ArrayList<String>> dominos_list = dp.getDominos();
        
        //Shuffle dominos
        Collections.shuffle(dominos_list);
        
        //Handle player's turn
        ArrayList<Integer> turn = new ArrayList<>();

        //Save clients-players data (name, color)
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<String> player_wait = new ArrayList<>();
        
        //Parse players' data (name, color)
        for(int i=0; i<4; i++) {
            
            //Wait until all client get connected
            server.acceptConnection();
        
            //Split data (name, color, wait)
            String players_data_unparsed = Server.fromClient(i);
            String[] parts = players_data_unparsed.split(",");
            
            Player player = new Player(parts[0], parts[1]);
            players.add(player);
                
            //Tmp wait for players
            player_wait.add(parts[2]);
            
            /*
            Same name check
            */
            
            //Add turn of player
            turn.add(i);
            
            //Confirm to client player's data
            Server.toClient(i, players.get(i).getName());
            Server.toClient(i, players.get(i).getColor());
            
            //Wait for other players
            if(i >= 1) {
                boolean wait = false;
                for(int j=0; j<player_wait.size(); j++) {
                    if(player_wait.get(j).equals("yes")) {
                        wait = true;
                        break;
                    }
                }
                
                //Stop waiting
                if(!wait)
                    break;
            }
        }
        
        int clients_count = players.size();
        System.out.println(clients_count);
        
        for(int i=0; i<clients_count; i++) {
            Server.toClient(i, "start");
            Server.toClient(i, clients_count+"");
        }

        //Shuffle turns to start the game
        Collections.shuffle(turn);
        System.out.println(turn);

        int list_size_full = dominos_list.size();

        for(int i=list_size_full-1; i>12*clients_count-1; i--){
            dominos_list.remove(i);
        }        
        
        for(int i=0; i<clients_count; i++) {
            for(int j=0; j<clients_count; j++){
                Server.toClient(i, "0,land,0,land,0");
            }
            for(int j=0; j<clients_count; j++){
                Server.toClient(i, "0,lake,0,lake,0");
            }
        }
        
        while(true){
        
        }  
    }
}
