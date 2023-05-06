package frame;

import frame.Table.MyJTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/*
 *  This class creates the text field located in the top right hand corner of the main frame.
 *  It is used to filter the table to what the user input is.
 */

public class MyFilter extends JTextField{
    
    private MyJTable table;
    private String[] columnNames;
    private Object[][] data;
    private TableRowSorter<TableModel> rowSorter;
    
    /**
    * Creates a constructor used by JTable to filter out the given parameters.
    * @param table  <The JTable component needed for the filter to be aplied.>
    */
    public MyFilter(MyJTable table){
        super();
        this.table = table;
        this.columnNames = table.getColumnNames();
        this.data = table.getData();
        initComponents();
    }

    /**
    * <p>The initial components of this class.
    * </p>
    * <p2>Adds a TableRowSorter component given the tableModel and sets it to the filter,
    * adds a DocumentListener component that updates the filter every time there is an update to the tableModel.
    * </p2>
    * @since 1.0
    */
    private void initComponents() {
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        setVisible(true);
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
        initComponents();
    }

    public void setData(Object[][] data) {
        this.data = data;
        initComponents();
    }
    
}
