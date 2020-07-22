package kingdominoclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
    
    private static PrintWriter to_server;
    private static Scanner from_server;
    
    public boolean connect() {
        try {
            Socket echoSocket = new Socket("localhost", 7777);
            
            to_server = new PrintWriter(echoSocket.getOutputStream(), true);
            from_server = new Scanner(echoSocket.getInputStream());
            
        }catch (UnknownHostException ex) {
            System.err.println(ex);
        }catch (IOException ex) {
            System.err.println(ex);
        }
        
        return true;
    }
    
    public static void toServer(String data) {
        to_server.println(data);
    }
    
    public static String fromServer() {
        return from_server.nextLine();
    }

}
