package frame.Table;

import frame.Interconnector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel{

    private static Object[][] data;
    private String[] tableHeaders;
    
    /**
     * Create a MyTableModel.
     * Extends AbstractTableModel and is used by MyJTable.
     *
     * @param data the table data array
     * @param columnNames names of the table columns
     */
    public MyTableModel(Object[][] data,String[] columnNames){
        MyTableModel.data = data;
        this.tableHeaders = columnNames;
    }
    
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
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
    public void setValueAt(Object obj, int row, int col)
    {  
        boolean flagID = false, flagINT = false;
        if(col == 0){
            try{
                Integer.parseInt(obj.toString());
            }catch(Exception yeet){
                JOptionPane.showMessageDialog(new JFrame(), "'cmplnt_num field' requires integer " + Integer.toString(col), "Failed", JOptionPane.ERROR_MESSAGE);
                flagINT = true;
            }
            for(int i = 0;i<data.length;i++)
                if(data[i][0].toString().equals(obj.toString()) && (row != i)) flagID = true;
        }
        if(flagID) JOptionPane.showMessageDialog(new JFrame(), "cmplnt_num already exists", "Failed", JOptionPane.ERROR_MESSAGE);
        else if(!flagINT){
            data[row][col] = obj;
        }
    }    
    
    /**
     * Refreshes the table to the new data input.
     * @param d the new 2D data array
     */
    public static void setData(Object[][] d){
        data = d;
    }
    
    
}
