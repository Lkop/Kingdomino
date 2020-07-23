package kingdominoclient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class PanelMenu extends JPanel{
   
    private JButton start;
    
    public PanelMenu(){
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(new JLabel("<html><h2 style=\"text-align:center;\">Kingdomino</h2><BR></html>"));
        
        JPanel buttons = new JPanel(new GridLayout(0,1));
        
        start = new JButton("Συμμετοχή σε παιχνίδι");
        //start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                start.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                start.setBackground(UIManager.getColor("control"));
            }
        });
                
        JButton stop = new JButton("Διακοπή παιχνιδιού");
        //stop.setContentAreaFilled(false);
        stop.setBorderPainted(false);
        
        JButton exit = new JButton("Έξοδος");
        //exit.setContentAreaFilled(false);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        exit.setBorderPainted(false);
        
        JButton help = new JButton("Βοήθεια");
        //help.setContentAreaFilled(false);
        help.setBorderPainted(false);
        
        JButton about = new JButton("Σχετικά με το kingdomino");
        //about.setContentAreaFilled(false);
        about.setBorderPainted(false);
        
        buttons.add(start);
        buttons.add(stop);
        buttons.add(exit);
        buttons.add(help);
        buttons.add(about);

        add(buttons);
    }
    
    public void addActionListener(ActionListener lis){
        start.addActionListener(lis);
    }
}