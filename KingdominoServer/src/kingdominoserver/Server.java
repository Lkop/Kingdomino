package kingdominoserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Server {

    private ServerSocket serverSocket = null;
    
    private static Map<Integer, Socket> clients = new HashMap<>();
    private int client_id=0;
    
    public Server() {
        try {
            serverSocket = new ServerSocket(7777);
        }catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println("Server is running...");
    }
    
    public void acceptConnection() {

//        for(int i=clients.size(); i<2; i++) {

            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();             
            }catch (IOException ex) {
                System.err.println(ex);
            }
            clients.put(client_id, clientSocket);
            client_id++;
            
            System.out.println("Server accepted a connection");        
        //}
//        return clients.size();
    }
    
    
    public static void toClient(int i, String data) {
        try {
            PrintWriter to_client = new PrintWriter(clients.get(i).getOutputStream(), true);
            to_client.println(data);
        }catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    public static String fromClient(int i) {
        
        Scanner from_client = null;
        try {
            from_client = new Scanner(clients.get(i).getInputStream());
        }catch (IOException ex) {
            System.err.println(ex);
        }
        
        if(from_client.hasNextLine()){
            return from_client.nextLine();
        }else{
            return null;
        }
    }
}
