package kingdominoserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class DominoParser {
        
    private ArrayList<ArrayList<String>> dominos_list = new ArrayList<>();
    
    public DominoParser() {
        try {
            File file = new File("dominos.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while((st = br.readLine()) != null) {
                
                String[] parts = st.split(",");
                
                ArrayList<String> domino = new ArrayList<>();
                domino.add(parts[0]);
                domino.add(parts[1]);
                domino.add(parts[2]);
                
                dominos_list.add(domino);
            }
        }catch (IOException ex) {
            
        }
    }
    
    public ArrayList<ArrayList<String>> getDominos() {
        return dominos_list;
    }
}
