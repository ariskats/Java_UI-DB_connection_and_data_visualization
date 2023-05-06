package frame.buttonActionListeners;

import frame.MyButton;
import frame.MyTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import json_handler.JsonParser;

/*
 *  This class displays a small form for the user to choose what to visualize.
 */

public class ActionListenerRepresent implements ActionListener{
    
    private MyButton representButton;
    private MyTextArea textArea;  
    private Object[][] data;
    private String[] columnNames;
    
    /**
	 *  Create a ActionListenerRepresent.
	 *
	 *  @param representButton   <the represent button>
	 */
    public ActionListenerRepresent(MyButton representButton) {
        this.representButton = representButton;
        initComponenets();
        
    }
    
    /**
    * <p>
    *   When action is performed.
    * </p>
    * <p2>
    *   Every time it is pressed it will parse the data to the Json parser class.
    * </p2>
    * @since 1.0
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.addAction("Opened the presentation form.");
        new JsonParser(textArea,data,columnNames).activate();
    }

    private void initComponenets() {
        representButton.setEnabled(false);
    }
    
    public void setConsole(MyTextArea textArea) {
        this.textArea = textArea;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }
    
}
