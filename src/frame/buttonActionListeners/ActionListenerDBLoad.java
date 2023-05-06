package frame.buttonActionListeners;

import frame.Interconnector;
import frame.MyButton;
import frame.MyTextArea;
import frame.Table.MyJTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import singleEdit.SearchForm;

/*
 *  This class listens for button presses of the button DB Load.
 *  Everytime it is pressed it will try to load the data that was previsously parsed in the local database.
 *  If there is no data it will display an error message.
 *  If the loading is successful it will display the data to the table and display a confirmation message.
 *  Every action that has been performed will also update the text area in the bottom displaying of the screen.
 */

public class ActionListenerDBLoad implements ActionListener {

    private MyTextArea textArea;
    private String[] columnNames;
    private int columns;
    private MyJTable table;
    private ArrayList<Object[]> data;
    private Object[] datarow;
    private MyButton parse, represent;
    private Interconnector interconnector;
    
    /**
	 *  Create a ActionListenerDBLoad.
	 *
	 *  @param button   <the connection Button>
	 *  @param parseButton  <the parse data to database Button>
	 *  @param representButton  <the represent data Button>
	 */
    public ActionListenerDBLoad(MyButton button,MyButton parseButton,MyButton representButton, Interconnector interconnector) {
        this.interconnector = interconnector;
        button.setEnabled(false);
        parse = parseButton;
        represent = representButton;
    }

    /**
    * <p>
    *   When action is performed.
    * </p>
    * <p2>
    *   Creates a query to collect all the data from the database.
    *   If there was a problem retrieving the data it will display an error message.
    *   In the same case, it will disable the buttons associated with the data.
    * 
    * </p2>
    * @since 1.0
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        columnNames = interconnector.getColumns();
        columns = columnNames.length;
        data = new ArrayList<>();

        try {
            String query = "SELECT * FROM users";
            Statement st = interconnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            int x = 0;
            while(rs.next()){
                x++;
                datarow = new Object[columns];
                for (int i = 0; i < columns; i++) {
                    try {
                        Object obj = rs.getString(columnNames[i]);
                        datarow[i] = obj;
                    } catch (Exception yeet) {
                        yeet.printStackTrace();
                        System.err.println("Couldn't retrieve string.");  
                        continue;
                    }
                }    
                data.add(datarow);          
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Could not load data from the Database."
                       , "Loading from Database failed", JOptionPane.INFORMATION_MESSAGE);
            textArea.addAction("Could not load data from the Database.");
        }
        Object[][] temp = new Object[data.size()][columns];
        for(int i=0;i<data.size();i++){
            temp[i] = data.get(i);
        }
        if(temp.length > 0){
            represent.setEnabled(true);
            parse.setEnabled(true);
        }
        table.refresh(temp,columnNames);
        if(data.size() != 0){
            JOptionPane.showMessageDialog(new JFrame(), "Successfully loaded data from the Database."
                       , "Loading was successful", JOptionPane.INFORMATION_MESSAGE);
            textArea.addAction("Successfully loaded data from the Database.");
        }
        
    }

    public void setConsole(MyTextArea textArea) {
        this.textArea = textArea;
    }

    public void setTable(MyJTable table) {
        this.table = table;
    }
}
