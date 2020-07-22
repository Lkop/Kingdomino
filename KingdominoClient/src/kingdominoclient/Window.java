package kingdominoclient;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window extends JFrame{
    
    private CardLayout cl = new CardLayout();
    
    public Window() {
//        this.cl = cl;
        
        setTitle("Kingdomino");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 500));
        setMinimumSize(new Dimension(600, 500));
        setLayout(cl);
        
        //Close button event
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent we) {
//                Client.close();
//                System.exit(0);
//            }
//        });
    }
    
    public void setPanel(String name) {
        cl.show(this.getContentPane(), name);
    }
}