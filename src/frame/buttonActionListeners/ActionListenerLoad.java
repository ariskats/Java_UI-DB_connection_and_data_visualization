package frame.buttonActionListeners;

//------------Loads the data from the nypd

import frame.MyButton;
import frame.Table.MyJTable;
import frame.MyTextArea;
import frame.downloadForm.DownloadController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 *  This class opens up a small form to download or load locally the file.
 *  If the user decides to terminate the window, it will display an error message and will not connect.
 *  Else it will display a confirmation message and try to load the data.
 */

public class ActionListenerLoad implements ActionListener{
    
    private MyButton loadButton, parseButton, representButton;
    private MyJTable table;
    private MyTextArea textArea;
    
    /**
	 *  Create a ActionListenerLoad.
	 *
	 *  @param loadButton   <the load button>
	 *  @param parseButton   <the parse button>
	 *  @param representButton   <the represent button>
	 */
    public ActionListenerLoad(MyButton loadButton,MyButton parseButton,MyButton representButton){
        this.loadButton = loadButton;
        this.parseButton = parseButton;
        this.representButton = representButton;
        initComponents();
    }

    private void initComponents() {
        loadButton.setEnabled(false);
    }

    /**
    * <p>
    *   When action is performed.
    * </p>
    * <p2>
    *   Every time it will display a download form with yes or no buttons.
    *   I the user terminates the window it will display an error message.
    *   Else it will display a successful information message.
    * </p2>
    * @since 1.0
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(loadButton.isEnabled()){
            DownloadController form = new DownloadController();
            if(form.isProceed()){
                parseButton.setEnabled(true);
                representButton.setEnabled(true);
                textArea.addAction("Loaded the file successfully.");
                table.setFile(form.getFile());
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Window closed unexpectedly", "Error", JOptionPane.ERROR_MESSAGE);
                textArea.addAction("Window closed unexpectedly");
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "A connection with the database has not been established yet"
                + "\nMake sure to connect to it first", "Database not established", JOptionPane.ERROR_MESSAGE);
            textArea.addAction("A connection with the database has not been established yet. Make sure to connect to it first");
        }
    }
    
    public void setConsole(MyTextArea textArea) {
        this.textArea = textArea;
    }

    public void setTable(MyJTable table) {
        this.table = table;
    }
}
