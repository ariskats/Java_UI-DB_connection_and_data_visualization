package tableListener;

import frame.Table.MyJTable;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JLabel;


public class ActionListenerCell implements FocusListener{

    private JLabel lbl;
    private MyJTable mjt;
    
    public ActionListenerCell(MyJTable mjt, JLabel lbl) {
        this.lbl = lbl;
        this.mjt = mjt;
    }

    @Override
    public void focusGained(FocusEvent e) {
        System.out.println("Focus GAINED.");
        lbl.setText(mjt.getSelectedColumn() + " + " + mjt.getSelectedRow());
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        System.out.println("Focus LOST.");
    }
}
