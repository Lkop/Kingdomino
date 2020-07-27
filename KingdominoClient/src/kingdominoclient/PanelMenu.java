package kingdominoclient;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PanelMenu extends JPanel{
   
    private JButton start;
    
    public PanelMenu(){
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(new JLabel("<html><h2 style=\"text-align:center;\">Kingdomino</h2><BR></html>"));
        
        JPanel buttons = new JPanel(new GridLayout(0,1));
        
        start = new JButton("Start Game");
        start.setBorderPainted(false);
//        start.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                start.setBackground(Color.RED);
//            }
//
//            @Override
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                start.setBackground(UIManager.getColor("control"));
//            }
//        });
                
        JButton stop = new JButton("Stop Game");
        stop.setEnabled(false);
        stop.setBorderPainted(false);
        
        JButton exit = new JButton("Exit");
        //exit.setContentAreaFilled(false);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        exit.setBorderPainted(false);
        
        JButton help = new JButton("Help");
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Create a JFrame
                JFrame frame = new JFrame("JOptionPane showMessageDialog example");

                //Show a joptionpane dialog using showMessageDialog
                JOptionPane.showMessageDialog(frame,
                    "In Kingdomino, you are a lord seeking new lands in which to expand your kingdom. \n"+
                    "You must explore all the lands, including wheat fields, lakes, and mountains, \n"+
                    "in order to spot the best plots, while competing with other lords to acquire them first.\n"+
                    "\n"+
                    "The game uses tiles with two sections, similar to Dominoes. \n"+
                    "Each turn, each player will select a new domino to connect to their existing kingdom, \n"+
                    "making sure at least one of its sides connects to a matching terrain \ntype already in play. The order of who picks first depends on \n"+
                    "which tile was previously chosen, with better tiles forcing players to \npick later in the next round. The game ends when each player \n"+
                    "has completed a 5x5 grid (or failed to do so), and points are counted based \non number of connecting tiles and valuable crown symbols.",
                    "Help", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        help.setBorderPainted(false);
        
        JButton about = new JButton("About Kingdomino");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Create a jframe
                JFrame frame = new JFrame("JOptionPane showMessageDialog example");

                //Show a joptionpane dialog using showMessageDialog
                JOptionPane.showMessageDialog(frame,
                    "Loukas Kopanias 2022201700078\n"+
                    "Eirini Barbouni 2022201700120",
                    "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
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