package kingdominoclient;


public class Main {

    public static void main(String[] args) {
        
        Client client = new Client();
        
        if(client.connect()) {
            GameController game = new GameController(client);
            game.openWindow();
            
        }
    }  
}
