package singleEdit;

import frame.Interconnector;
import javax.swing.JPanel;
import javax.swing.JTable;

public class MiniTable extends JTable{

    private MiniTableModel model;
    private String[] columnNames;
    private Object[] data;
    private JPanel panel;
    private Object[] dataInside; 
    private Interconnector interconnector;
    
    static Object[][] hollowData = {
        {"", "", "", "", "", "", "", "", "", ""
        , "", "", "", "", "", "", "", "", "", "", "", ""
        , "", "", "", "", "", "", "", ""}
    };    

    /**
     * Create a MiniTable that's pretty much a one-row MyJTable.
     *
     * @param interconnector carries data throughout the program
     */
    public MiniTable(JPanel panel, Interconnector interconnector) {
        super(hollowData,interconnector.getColumns());
        this.interconnector = interconnector;
        columnNames = interconnector.getColumns();
        data = hollowData[0];
        this.panel = panel;
        initComponents();
    }

    private void initComponents() {
        setFillsViewportHeight(true);
        setVisible(true);
        this.setModel(model = new MiniTableModel(data,interconnector));
    }
    
    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
    
    @Override
    public MiniTableModel getModel(){
        return model;
    }

    public String[] getColumnNames() {
        return columnNames;
    }
    
    /**
     * Refreshes the table.
     * @param newdata the new table data
     */
    public void refresh(Object[] newdata){
        this.setModel(model = new MiniTableModel(newdata,interconnector));
    }
    
}
