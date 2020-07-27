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
                
                //ID
                domino.add(parts[0]);
                
                //Image - crowns
                domino.add(parts[1]);
                if(parts[1].charAt(parts[1].length()-1) == 'c')
                    domino.add(parts[1].charAt(parts[1].length()-2)+"");
                else
                    domino.add(0+"");
                        
                domino.add(parts[2]);
                if(parts[2].charAt(parts[2].length()-1) == 'c')
                    domino.add(parts[2].charAt(parts[2].length()-2)+"");
                else
                    domino.add(0+"");
                
                dominos_list.add(domino);
            }
        }catch (IOException ex) {
            
        }
    }
    
    public ArrayList<ArrayList<String>> getDominos() {
        return dominos_list;
    }
}
