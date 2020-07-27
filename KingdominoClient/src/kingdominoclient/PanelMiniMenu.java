package kingdominoclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;


public class PanelMiniMenu extends JMenuBar{
	
    public PanelMiniMenu(){
        
        //Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;


        //Build the first menu.
        menu = new JMenu("Παιχνίδι");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The menu game");
        menuItem = new JMenuItem("Συμμετοχή σε παιχνίδι",
                         KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Connects to a game");
        menu.add(menuItem);
        menuItem = new JMenuItem("Διακοπή παιχνιδιού",
                         KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Stops the game");
        menu.add(menuItem);
        menuItem = new JMenuItem("Έξοδος",
                         KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_3, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Exits the program");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        menu.add(menuItem);
        add(menu);
        
        //Build the second menu.
        menu = new JMenu("Βοήθεια");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The menu help");
        menuItem = new JMenuItem("Βοήθεια",
                         KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Help");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // create a jframe
                JFrame frame = new JFrame("JOptionPane showMessageDialog example");

                // show a joptionpane dialog using showMessageDialog
                JOptionPane.showMessageDialog(frame,
                    "In Kingdomino, you are a lord seeking new lands in which to expand your kingdom. \nYou must explore all the lands, including wheat fields, lakes, and mountains, \nin order to spot the best plots, while competing with other lords to acquire them first.\n" +
"\n" +
"The game uses tiles with two sections, similar to Dominoes. \nEach turn, each player will select a new domino to connect to their existing kingdom, \nmaking sure at least one of its sides connects to a matching terrain \ntype already in play. The order of who picks first depends on \nwhich tile was previously chosen, with better tiles forcing players to \npick later in the next round. The game ends when each player \nhas completed a 5x5 grid (or failed to do so), and points are counted based \non number of connecting tiles and valuable crown symbols.",
                    "Help",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Σχετικά με το «Kingdomino»",
                         KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "About game");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // create a jframe
                JFrame frame = new JFrame("JOptionPane showMessageDialog example");

                // show a joptionpane dialog using showMessageDialog
                JOptionPane.showMessageDialog(frame,
                    "Loukas Kopanias 2022201700078\nEirini Barbouni 2022201700120",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(menuItem);
        add(menu);
    }
}