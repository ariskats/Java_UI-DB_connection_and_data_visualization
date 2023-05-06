package tableListener;

import javax.swing.*;

public class ListenerWindow extends JDialog { 
    
    private JLabel lbl;
    
    // ALTERATIONS ARE IN CLASSES: MyPanel, MyToolBar, MyJTable
    
    public ListenerWindow(JLabel jlabel) {
        super(new JFrame(), "TableCellListener", false);
        this.lbl = jlabel;
        initComponents();

    }
    
    private void initComponents(){
        this.setSize(200, 110);
        this.setResizable(false);
        this.add(lbl);        
        this.setVisible(true);
        
    }
    
    

}


