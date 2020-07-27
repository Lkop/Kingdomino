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
            
            Player player = new Player(i, parts[0], parts[1]);
            players.add(player);
                
            //Tmp wait for players
            player_wait.add(parts[2]);
            
            /*
            Same name check
            */
            
            //Add turn of player
            turn.add(i);
            
            //Confirm to client player's data
            Server.toClient(i, players.get(i).getId()+"");
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
          
        boolean first_time=true;
        ArrayList<ArrayList<String>> tmp_dominos_list_l = new ArrayList<>();
        ArrayList<ArrayList<String>> tmp_dominos_list_r = new ArrayList<>();

        while(dominos_list.size() > 0){
            
            int tmp_max = dominos_list.size();
            int tmp_min = dominos_list.size()-clients_count;
            for(int i=tmp_max-1; i>tmp_min-1; i--) {
                tmp_dominos_list_r.add(dominos_list.get(i));
                dominos_list.remove(i);
            }
            
            if(first_time){
                tmp_dominos_list_l = (ArrayList)tmp_dominos_list_r.clone();
                tmp_dominos_list_r.clear();
                
                tmp_max = dominos_list.size();
                tmp_min = dominos_list.size()-clients_count;
                for(int i=tmp_max-1; i>tmp_min-1; i--) {
                    tmp_dominos_list_r.add(dominos_list.get(i));
                    dominos_list.remove(i);
                }
                
                first_time = false;
            }
            
            for(int client_id=0; client_id<clients_count; client_id++) {
                //Left
                for(int j=0; j<clients_count; j++){
                    String data = tmp_dominos_list_l.get(j).get(0)+","+tmp_dominos_list_l.get(j).get(1)+","+tmp_dominos_list_l.get(j).get(2)+","+tmp_dominos_list_l.get(j).get(3)+","+tmp_dominos_list_l.get(j).get(4);
                    Server.toClient(client_id, data);
                }
                
                //Right
                for(int j=0; j<clients_count; j++){
                    String data = tmp_dominos_list_r.get(j).get(0)+","+tmp_dominos_list_r.get(j).get(1)+","+tmp_dominos_list_r.get(j).get(2)+","+tmp_dominos_list_r.get(j).get(3)+","+tmp_dominos_list_r.get(j).get(4);
                    Server.toClient(client_id, data);
                }
            }
            
            for(int client_id=0; client_id<clients_count; client_id++) {
                
                Server.toClient(client_id, "play");
                
                //Move
                System.out.println(Server.fromClient(client_id));
                
                //Select
                System.out.println(Server.fromClient(client_id));  
            }
            
            for(int client_id=0; client_id<clients_count; client_id++) {
                Server.toClient(client_id, "nextround");
            }
            
            tmp_dominos_list_l = (ArrayList)tmp_dominos_list_r.clone();
            tmp_dominos_list_r.clear();  
        }
    }
}
