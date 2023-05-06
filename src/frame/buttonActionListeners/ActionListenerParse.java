package frame.buttonActionListeners;

import database.DBWriting;
import frame.Interconnector;
import frame.MyButton;
import frame.MyTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *  This class parses the data of the displaying table to the local database.
 */

public class ActionListenerParse implements ActionListener{
        
    private MyButton parseButton;
    private MyTextArea textArea;
    private Interconnector interconnector;
    
    /**
	 *  Create a ActionListenerParse.
	 *
	 *  @param parseButton   <the parse button>
	 */
    public ActionListenerParse(MyButton parseButton, Interconnector interconnector) {
        this.parseButton = parseButton;
        this.interconnector = interconnector;
        initComponents();
    }
    
    /**
    * <p>
    *   When action is performed.
    * </p>
    * <p2>
    *   Every time it is pressed it will parse the data to the local database.
    * </p2>
    * @since 1.0
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        new DBWriting(textArea,interconnector);
        textArea.addAction("Parsed JTable.");
    }

    private void initComponents() {
        parseButton.setEnabled(false);
    }
    
    public void setConsole(MyTextArea textArea) {
        this.textArea = textArea;
    }
}
