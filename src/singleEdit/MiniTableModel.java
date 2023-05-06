package singleEdit;

import frame.Interconnector;
import javax.swing.table.AbstractTableModel;

public class MiniTableModel extends AbstractTableModel{

    private Object[] data;
    private String[] tableHeaders;
    
    /**
     * Create a new MiniTableModel.
     * For use by the MiniTable class.
     * 
     * @param data the initial table data array
     * @param interconnector carries data throughout the program
     */
    public MiniTableModel(Object[] data, Interconnector interconnector){
        this.data = data;
        tableHeaders = interconnector.getColumns();
    }
    
    public Object[] getData(){
        return data;
    }

    @Override
    public int getColumnCount() {
        return data.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return tableHeaders[columnIndex];
    }
    
    /**
     * Tells if the cell is editable.
     * @param rowIndex cell row (x axis)
     * @param columnIndex cell column (y axis)
     * @return boolean
     */
    @Override
    public boolean isCellEditable(int rowIndex,int columnIndex){
        return true;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[columnIndex];
    }
    
    @Override
    public void setValueAt(Object obj, int row, int col){
        data[col] = obj;
    }
    
    
}
