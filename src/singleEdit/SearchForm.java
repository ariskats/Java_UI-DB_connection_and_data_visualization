package singleEdit;

import frame.Interconnector;
import frame.MyTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class SearchForm extends JDialog {

    private MyTextArea textArea;
    private JTextField jtf;
    private JLabel jtfLabel, currentLabel, currentValue;
    private JButton searchButton, saveButton, deleteButton;
    private MiniTable table;
    private Object[] data = new Object[30];
    private JPanel thepanel, topanel, bopanel;
    private String[] columnNames;
    private String current = "none";
    private int columns;

    
    /**
     * Create a new SearchForm.
     * 
     * A form that searches the database by ID and returns
     * the row. From there, you can edit or delete the row.
     * 
     * @param textArea the console
     * @param interconnector carries data throughout the program
     */
    public SearchForm(MyTextArea textArea, Interconnector interconnector) {
        super(new JFrame(), "Single Row Edition", true);
        this.textArea = textArea;
        initComponents(interconnector);

    }

    private void initComponents(Interconnector interconnector) {
        this.setSize(1000, 110);
        this.setResizable(false);

        columnNames = interconnector.getColumns();
        columns = columnNames.length;
        thepanel = new JPanel(new GridLayout(2, 1));
        topanel = new JPanel(new GridLayout(1, 7));
        bopanel = new JPanel(new BorderLayout());
        table = new MiniTable(bopanel,interconnector);
        jtfLabel = new JLabel("          Enter cmplnt_num: ");
        currentLabel = new JLabel("           Selected: ");
        currentValue = new JLabel(current);
        jtf = new JTextField();

        searchButton = new JButton("Search");
        saveButton = new JButton("Save to DB");
        saveButton.setEnabled(false);
        deleteButton = new JButton("Delete selected");
        deleteButton.setEnabled(false);
        addListeners(interconnector);

        build();
        setVisible(true);

    }

    private void build() {
        add(thepanel);
        thepanel.add(topanel);
        thepanel.add(bopanel);
        topanel.add(jtfLabel);
        topanel.add(jtf);
        topanel.add(currentLabel);
        topanel.add(currentValue);
        topanel.add(searchButton);
        topanel.add(saveButton);
        topanel.add(deleteButton);
        bopanel.add(new JScrollPane(table));
    }

    private void addListeners(Interconnector interconnector) {

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag = false, flag2 = false;
                try {
                    try{
                        int x = Integer.parseInt(jtf.getText());
                    }catch(Exception noint){
                        flag2 = true;
                    }
                    if(flag2){
                        JOptionPane.showMessageDialog(new JFrame(), "Please enter an integer in search field.",
                                "Failed", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String query = "SELECT * FROM users WHERE cmplnt_num = " + jtf.getText();
                        Statement st = interconnector.getConnection().createStatement();
                        ResultSet rs = st.executeQuery(query);
                        if(rs.next()){
                            saveButton.setEnabled(true);
                            deleteButton.setEnabled(true);
                            flag = true;
                        }
                        for (int i = 0; i < columns; i++) {
                            try {
                                data[i] = rs.getString(columnNames[i]);
                            } catch (Exception yeet) {
                                continue;
                            }
                        }
                        st.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SearchForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(!flag2){
                    if(flag){
                        updateCurrent(data[0].toString());                
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "Couldn't find a row with the specified cmplnt_num.",
                                "No match found", JOptionPane.ERROR_MESSAGE);
                        textArea.addAction("Couldn't find a row with the specified cmplnt_num.");
                        data = new Object[30];
                        updateCurrent("none");
                    }                
                    table.refresh(data);                    
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                data = table.getModel().getData();
                boolean flag = false;
                try {
                    int x = 0;
                    try{
                        x = Integer.parseInt(data[0].toString());
                    }catch(Exception noint){
                        flag = true;
                    }
                    if(flag){
                        JOptionPane.showMessageDialog(new JFrame(), "Please enter an integer in field 'cmplnt_num'.",
                                "Failed", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String query = "SELECT * FROM users WHERE cmplnt_num = " + x;
                        Statement st = interconnector.getConnection().createStatement();
                        ResultSet rs = st.executeQuery(query);
                        if(rs.next() && !data[0].toString().equals(current)){
                            flag = true;
                            JOptionPane.showMessageDialog(new JFrame(), "cmplnt_num " + x + " already exists in database...",
                                "Failed", JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            query = "UPDATE users SET ";
                            for (int i = 0; i < columns - 1; i++) {
                                query += columnNames[i] + " = " + (char) 34 + data[i].toString() + (char) 34 + ",";
                            }
                            query += columnNames[columns - 1] + " = " + (char) 34 + data[columns - 1].toString() + (char) 34;
                            query += " WHERE cmplnt_num = " + (char) 34 + current + (char) 34;
                            PreparedStatement update = interconnector.getConnection().prepareStatement(query);
                            int affected = update.executeUpdate();
                            System.out.println("Row updated!");
                            JOptionPane.showMessageDialog(new JFrame(), "The row was updated successfully.",
                                "Row updated", JOptionPane.INFORMATION_MESSAGE);
                            textArea.addAction("Row updated successufully.");
                        }                     
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SearchForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(new JFrame(), "There was a problem while updating the row.",
                                "Fatal Error", JOptionPane.ERROR_MESSAGE);
                    textArea.addAction("There was a problem while updating the row.");
                }
                if(!flag) updateCurrent(data[0].toString());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                data = table.getModel().getData();
                try {
                    String query = "DELETE FROM users WHERE cmplnt_num = " + (char) 34 + current + (char) 34;
                    PreparedStatement update = interconnector.getConnection().prepareStatement(query);
                    int affected = update.executeUpdate();
                    System.out.println("Row deleted.");
                    JOptionPane.showMessageDialog(new JFrame(), "Successfully deleted the row.",
                                "Successfully deleted", JOptionPane.INFORMATION_MESSAGE);
                    textArea.addAction("Successfully deleted the row.");
                } catch (SQLException ex) {
                    Logger.getLogger(SearchForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(new JFrame(), "There was a problem while deleting the row.",
                                "Problem deleting row", JOptionPane.ERROR_MESSAGE);
                    textArea.addAction("There was a problem while deleting the row.");
                }
                data = new Object[30];
                table.refresh(data);
                updateCurrent("none");
                saveButton.setEnabled(false);
                deleteButton.setEnabled(false);                
            }
        });
    }
    
    private void updateCurrent(String s){
                current = s;
                currentValue.setText(current);        
    }

}
