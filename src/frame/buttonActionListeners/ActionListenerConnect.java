package frame.buttonActionListeners;

//----connects to the database

import database.DBConnection;
import frame.Interconnector;
import frame.MyButton;
import frame.MyTextArea;
import frame.databaseForm.DatabaseForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 *  This class listens for button presses of the button Connect.
 *  Everytime it is pressed it will open a form to connect to the local MySQL database.
 *  It will also return a JOption pane containing weather the action was successfull or not.
 */

public class ActionListenerConnect implements ActionListener{
    private DBConnection db = null;
    private boolean proceed = false;
    private MyButton databaseButton;
    private MyButton loadButton;
    private MyButton searchButton;
    private MyButton addButton;
    private MyButton nukeButton;
    private MyButton db2Button;
    private Interconnector interconnector;
    
    private MyTextArea textArea;
    
    /**
	 *  Create a ActionListenerConnect.
	 *
	 *  @param databaseButton   <the databaseButton Button>
	 *  @param loadButton  <the loadButton Button>
	 *  @param searchButton  <the searchButton Button>
	 *  @param addButton  <the addButton Button>
	 *  @param nukeButton  <the nukeButton Button>
	 *  @param db2Button  <the db2Button Button>
	 */
    public ActionListenerConnect(MyButton databaseButton,MyButton loadButton, MyButton searchButton, MyButton addButton, MyButton nukeButton, MyButton db2Button, Interconnector interconnector){
        this.databaseButton = databaseButton; 
        this.loadButton = loadButton;
        this.searchButton = searchButton;
        this.addButton = addButton;
        this.nukeButton = nukeButton;
        this.db2Button = db2Button;
        this.interconnector = interconnector;
    }
    
    /**
    * <p>
    *   When action is performed.
    * </p>
    * <p2>
    * Creates a form for the user to input the information for the database in order to connect to it.
    * If the user decides to terminate the form, then it will return a false flag and display an error message to the user.
    * If the information the user has put, is not correct, then it will not connect to the database and display an error message.
    * If the information the user has put, is correct, then it will connect to the database and display confirmation message.
    * Also if the connection is successful it will disable the connect button and enable all the before disabled ones.
    * Finally, every time there is an action performed there will be a message displayed in the text area in the bottom of the main frame.
    * </p2>
    * @since 1.0
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        DatabaseForm form = new DatabaseForm(proceed);
        proceed = form.isProceed();
        if(proceed){
            try{
                db = null;
                db = DBConnection.getDBConnection(form.getDbUser(),form.getDbPass(),form.getDbHost(),form.getDbPort(),form.getDbName(),interconnector);
                System.out.println("Connection is valid: " + db.getCon().isValid(0));
                if(db.getCon().isClosed()){
                    JOptionPane.showMessageDialog(new JFrame(), "Connection with the database hasn't been established."
                        + "\nMake sure to connect to it first", "Connection failed", JOptionPane.ERROR_MESSAGE);
                    textArea.addAction("Connection with the database hasn't been established. Make sure to connect to it first");
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Connection was successfully established."
                        , "Successfully connected", JOptionPane.INFORMATION_MESSAGE);
                    textArea.addAction("Connection was successfully established. Successfully connected");
                    databaseButton.setEnabled(false);
                    loadButton.setEnabled(true);
                    searchButton.setEnabled(true);
                    addButton.setEnabled(true);
                    nukeButton.setEnabled(true);
                    db2Button.setEnabled(true);
                }
            }catch(Exception ex){
                System.out.println("Exception in database connection");
                JOptionPane.showMessageDialog(new JFrame(), "Connection with the database hasn't been established."
                        + "\nMake sure the credentials are correct first.", "Connection failed", JOptionPane.ERROR_MESSAGE);
                textArea.addAction("Connection with the database hasn't been established. Make sure to connect to it first");
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Connection with the database hasn't been established."
                + "\nWindow was unexpectedly closed.", "Connection failed", JOptionPane.ERROR_MESSAGE);
            textArea.addAction("Connection with the database hasn't been established. Window was unexpectedly closed.");
            databaseButton.setEnabled(true);
            loadButton.setEnabled(false);
        }
    }
    
    public DBConnection getDb() {
        return db;
    }
    
    public void setConsole(MyTextArea textArea) {
        this.textArea = textArea;
    }
}
