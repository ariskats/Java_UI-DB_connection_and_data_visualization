package tableListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;


public class ActionListenerTable implements ActionListener{

    private JLabel lbl;
    
    public ActionListenerTable(JLabel lbl) {
        this.lbl = lbl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ListenerWindow(lbl);
    }
}
