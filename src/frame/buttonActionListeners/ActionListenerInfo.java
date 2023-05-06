package frame.buttonActionListeners;

import frame.MyTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 *  This class creates an information message that displays information.
 */

public class ActionListenerInfo implements ActionListener{

    private MyTextArea textArea;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        doInfo();
    }

    private void doInfo() {
        JOptionPane.showMessageDialog(new JFrame(), "PLAN DRIVEN AND AGILE PROGRAMING"
                + "\nWinter Semester 2019", "About", JOptionPane.INFORMATION_MESSAGE);
        textArea.addAction("Opened the credits.");
    }
    
    public void setConsole(MyTextArea textArea) {
        this.textArea = textArea;
    }
}
