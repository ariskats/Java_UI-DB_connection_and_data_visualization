package frame.buttonActionListeners;

import frame.Interconnector;
import frame.MyButton;
import frame.MyTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import singleEdit.AddForm;

/*
 *  This class listens for button presses of the button Add.
 *  Everytime it is pressed it will open a form to add a new row to the table.
 */

public class ActionListenerAdd implements ActionListener{

    private MyTextArea textArea;
    private Interconnector interconnector;
    
    
    public ActionListenerAdd(MyButton button, Interconnector interconnector){
        button.setEnabled(false);
        this.interconnector = interconnector;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        new AddForm(textArea,interconnector);
        textArea.addAction("Opened add row form.");
    }
    
    public void setConsole(MyTextArea textArea) {
        this.textArea = textArea;
    }
}
