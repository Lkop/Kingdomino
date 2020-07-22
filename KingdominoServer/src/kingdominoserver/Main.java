package kingdominoserver;


public class Main {

    public static void main(String[] args) {
        
        Server server = new Server();
        
        //Wait until all client get connected
        int clients_count = server.acceptConnections();
        
        //Save clients-players data (name, color)
        String clients_data[][] = new String[clients_count][2];
        
        //Parse players' data (name, color)
        for(int i=0; i<clients_count; i++) {
            String players_data_unparsed = Server.fromClient(i);
            
            String[] parts = players_data_unparsed.split(",");
            clients_data[i][0] = parts[0];
            clients_data[i][1] = parts[1];
            
            Server.toClient(i, clients_data[i][0]);
            Server.toClient(i, clients_data[i][1]);
        }
        
        for(int i=0; i<clients_count; i++) {
            Server.toClient(i, "start");
        }        
        
//        DominoParser dp = new DominoParser();
        
    }
    
}
