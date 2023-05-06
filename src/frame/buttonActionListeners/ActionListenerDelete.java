package frame.buttonActionListeners;

import frame.Interconnector;
import frame.MyButton;
import frame.MyTextArea;
import frame.Table.MyJTable;
import frame.downloadForm.NukeDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import singleEdit.SearchForm;

/*
 *  This class listens for button presses of the button Nuke db.
 *  Everytime it is pressed it will try delete all the data from the local database.
 *  If there is no data in the database it will display an error message.
 *  Else it will display a confirmation message.
 *  Every action that has been performed will also update the text area in the bottom displaying of the program.
 */

public class ActionListenerDelete implements ActionListener{

    private MyTextArea textArea;
    private Interconnector interconnector;
    
    /**
	 *  Create a ActionListenerDelete.
	 *
	 *  @param button   <the nuke db Button>
	 */
    public ActionListenerDelete(MyButton button, Interconnector interconnector){
        button.setEnabled(false);
        this.interconnector = interconnector;
    }
    
    /**
    * <p>
    *   When action is performed.
    * </p>
    * <p2>
    *   Creates a query to delete all the data from the local database.
    *   It opens up a confirmation form for the user to input the last input.
    *   If the user selects to nuke the database and the deletion is successful it will display a confirmation message.
    *   Else it will display an error message.
    * 
    * </p2>
    * @since 1.0
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.addAction("Opened the NUKE form.");
        new NukeDB();
        if(NukeDB.resolve()){
            try {
                    String query = "DELETE FROM users";
                    PreparedStatement update = interconnector.getConnection().prepareStatement(query);
                    int affected = update.executeUpdate();
                    System.out.println("DB nuked...");
                    JOptionPane.showMessageDialog(new JFrame(), "Successfully deleted all the rows from the database."
                       , "Deletion was successfully", JOptionPane.INFORMATION_MESSAGE);
                    textArea.addAction("Successfully deleted all the rows from the database.");
                } catch (SQLException ex) {
                    Logger.getLogger(SearchForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(new JFrame(), "There was a problem deleting the database."
                       , "Deletion failed", JOptionPane.ERROR_MESSAGE);
                    textArea.addAction("There was a problem deleting the database.");
                }
        }
    }
    
    public void setConsole(MyTextArea textArea) {
        this.textArea = textArea;
    }
}
