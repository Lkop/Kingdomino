package kingdominoserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Server {

    private ServerSocket serverSocket = null;
    
    private static Map<Integer, Socket> clients = new HashMap<>();
    private int client_id=0;
    
    private static ArrayList<PrintWriter> to_client = new ArrayList<>();
    private static ArrayList<Scanner> from_client = new ArrayList<>();
            
    public Server() {
        try {
            serverSocket = new ServerSocket(7777);
        }catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println("Server is running...");
    }
    
    public void acceptConnection() {

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();             
        }catch (IOException ex) {
            System.err.println(ex);
        }
        
        clients.put(client_id, clientSocket);
        
        try {
            PrintWriter to = new PrintWriter(clients.get(client_id).getOutputStream(), true);
            to_client.add(to);

            Scanner from = new Scanner(clients.get(client_id).getInputStream());
            from_client.add(from);
        }catch (IOException ex) {
            System.err.println(ex);
        }
        
        client_id++;

        System.out.println("Server accepted a connection");
    }

    public static void toClient(int i, String data) {
        to_client.get(i).println(data); 
    }
    
    public static String fromClient(int i) {
        return from_client.get(i).nextLine();
    }
}
