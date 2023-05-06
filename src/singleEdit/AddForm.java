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

public class AddForm extends JDialog {
    
    private MyTextArea textArea;
    private JButton addButton;
    private MiniTable table;
    private Object[] data = new Object[30];
    private JPanel thepanel, topanel, bopanel;
    private String[] columnNames;
    private int columns;
    private int x = 8;

    /**
     * Create an AddForm inside a new JFrame.
     * Takes data input and saves it to the database.
     *
     * @param textArea the console
     * @param interconnector carries data throughout the program
     */
    public AddForm(MyTextArea textArea, Interconnector interconnector) {
        super(new JFrame(), "Add Single Row", true);
        this.textArea = textArea;
        initComponents(interconnector);

    }

    private void initComponents(Interconnector interconnector) {
        this.setSize(1000, 110);
        this.setResizable(false);

        columnNames = interconnector.getColumns();
        columns = columnNames.length;
        thepanel = new JPanel(new GridLayout(2, 1));
        topanel = new JPanel(new GridLayout(1, x));
        bopanel = new JPanel(new BorderLayout());
        table = new MiniTable(bopanel,interconnector);

        addButton = new JButton("Add");
        addListeners(interconnector);

        build();
        setVisible(true);

    }

    private void build() {
        add(thepanel);
        thepanel.add(topanel);
        thepanel.add(bopanel);
        for(int i=0;i<x;i++) topanel.add(new JLabel(" "));
        topanel.add(addButton);
        bopanel.add(new JScrollPane(table));
    }

    private void addListeners(Interconnector interconnector) {

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                data = table.getModel().getData();
                boolean flag = false;
                try {
                    int x = 0;
                    try{
                        x = Integer.parseInt(data[0].toString());
                        for(int i=0;i<columns;i++)
                            if(data[i].toString().length() == 0)  throw new Exception();
                    }catch(Exception yeet){
                        flag = true;
                    }
                    if(flag){
                        JOptionPane.showMessageDialog(new JFrame(), "Please don't leave any fields blank.",
                                "Failed", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String query = "SELECT * FROM users WHERE cmplnt_num = " + x;
                        Statement st = interconnector.getConnection().createStatement();
                        ResultSet rs = st.executeQuery(query);
                        if(rs.next()){
                            flag = true;
                            JOptionPane.showMessageDialog(new JFrame(), "cmplnt_num " + x + " already exists in database...",
                                "Failed", JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            query = "INSERT INTO users VALUES(";
                            for (int i = 0; i < columns - 1; i++) {
                                query += (char) 34 + data[i].toString() + (char) 34 + ",";
                            }
                            query += (char) 34 + data[columns - 1].toString() + (char) 34 + ")";
                            PreparedStatement update = interconnector.getConnection().prepareStatement(query);
                            int affected = update.executeUpdate();
                            System.out.println("Row added!"); 
                            JOptionPane.showMessageDialog(new JFrame(), "Row added successfully", "Action was successfull", JOptionPane.INFORMATION_MESSAGE);
                            textArea.addAction("Row added successfully.");
                        }                     
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AddForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(new JFrame(), "Could not add the row", "Failed to add row", JOptionPane.ERROR_MESSAGE);
                    textArea.addAction("Could not add the row.");
                }
            }
        });
    }

}


