package frame.buttonActionListeners;

import frame.Interconnector;
import frame.MyButton;
import frame.MyTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import singleEdit.SearchForm;

/*
 *  This class listens for button presses of the button select.
 */

public class ActionListenerSearch implements ActionListener{

    private MyTextArea textArea;
    private Interconnector interconnector;
    
    public ActionListenerSearch(MyButton button, Interconnector interconnector){
        button.setEnabled(false);
        this.interconnector = interconnector;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        new SearchForm(textArea,interconnector);
        textArea.addAction("Opened search form");
    }
    
    public void setConsole(MyTextArea textArea) {
        this.textArea = textArea;
    }
}
