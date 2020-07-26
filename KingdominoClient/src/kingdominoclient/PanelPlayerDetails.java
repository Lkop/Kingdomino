package kingdominoclient;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelPlayerDetails extends JPanel{
   
    private String[] colors = { "Red", "Green", "Blue", "Yellow" };
    private JButton start, start_wait;
    private JTextField name;
    private JComboBox color;

    public PanelPlayerDetails(){
        setLayout(new GridLayout(5,1));
        
        name = new JTextField(15);
        color = new JComboBox();
        start = new JButton("Start");
        start_wait = new JButton("Start - Wait more players");
        
        JPanel l1 = new JPanel(new FlowLayout());
        JPanel l2 = new JPanel(new FlowLayout());
        JPanel l3 = new JPanel(new FlowLayout());
        JPanel l4 = new JPanel(new FlowLayout());
        JPanel l5 = new JPanel(new FlowLayout());
        
        
        l1.add(new JLabel("What's your nickname?"));
        add(l1);
        
        l2.add(name);
        add(l2);
        
        l3.add(new JLabel("Select a color:"));
        
        for(int i=0;i<4;i++){
            color.addItem(colors[i]);
        }
        
        add(l3);
        l4.add(color);
        add(l4);
        l5.add(start);
        l5.add(start_wait);
        add(l5);
    }
    
    public void addActionListener(ActionListener lis) {
        start.addActionListener(lis);
        start_wait.addActionListener(lis);
    }
    
    public JButton getStartButton() {
        return start;
    }
    
    public String getName() {
        return name.getText();
    }
    public String getColor() {
        return color.getSelectedItem().toString();
    }
}
