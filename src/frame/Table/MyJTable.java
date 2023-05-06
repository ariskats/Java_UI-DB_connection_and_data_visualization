package frame.Table;

import frame.MyPanel;
import frame.MyTextArea;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import tableListener.ActionListenerCell;
import tableListener.ActionListenerTable;

/*
 *  This class creates the Table of data that will be displayed in the middle 
 *  of the main frame. 
 */

public class MyJTable extends JTable{
    
    private MyTextArea textArea;
    private static MyTableModel model;
    private String[] columnNames;
    private Object[][] data;
    private MyPanel panel;
    private JsonArray jsonArray;
    private JsonObject obj;
    private File file;
    private ArrayList<Object[]> dataList = new ArrayList<>();
    private Object[] dataInside;
    private static MyJTable singelInstanceMyJTable;
    
    /**
    * Creates a constructor used by MyJPanel component to display the JTable component.
    * @param columnNames  <Array of String component that holds all the column names>
    * @param data  <2D array of Object component that holds the data of the JTable component>
    * @param panel  <MyPanel component> 
    * @param textArea  <MyTextArea component that holds the console simulation> 
    */
    private MyJTable(String[] columnNames,Object[][] data,MyPanel panel,MyTextArea textArea,JLabel lbl) {
        super(data,columnNames);
        this.columnNames = columnNames;
        this.data = data;
        this.panel = panel;
        this.textArea = textArea;
        initComponents();
    // ALTERED
        addFocusListener(new ActionListenerCell(this, lbl));
    }

    /**
    * <p>The initial components of this constructor.
    * </p>
    * <p2>Sets whether or not this table is always made large enough
    * to fill the height of an enclosing viewport.
    * And sets it visible.
    * </p2>
    * @since 1.0
    */
    private void initComponents() {        
        //scrollPane = new JScrollPane(this);
        setFillsViewportHeight(true);
        //scrollPane.setVisible(true);
        setVisible(true);
    }

    /**
    * <p>
    * The initial components of makeJSONArray().
    * </p>
    * <p2>
    * Creates a InputStream component and JsonReader component.
    * The InputStream component will handle the given file.
    * The JsonReader component will read each line of the InputStream component
    * and parse it to the JsonArray component.
    * Finally it will terminate the JsonReader component to release resources, 
    * and call the makeUnderstandableByTheTable() method.
    * </p2>
    * @since 1.0
    */
    private void makeJSONArray() {
        InputStream is;
        JsonReader reader;
        try{
            is = new FileInputStream(file);
            reader = Json.createReader(is);
            jsonArray = reader.readArray();
            reader.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        makeUnderstandableByTheTable();
    }
    
    /**
    * <p>
    * The initial components of makeUnderstandableByTheTable().
    * </p>
    * <p2>
    * Brief explanation:
    *   Parses the the JsonArray component to the JTable component.
    * Detailed explanation:
    *   As long as the JsonArray is, it creates a for-loop.
    *   It parses a single line of of the JsonArray component to a JsonObject component.
    *   Creates a new Object array component as long as the length of the String array component columnNames is.
    *   Starts a nested for-loop that it lasts as long as the length of the String array component columnNames is.
    *   Tries to extract the data from the JsonObject component and parses it to the Object array component.
    *   If it fails to do the above try, it sets the given Object array component as null, and continues the nested for-loop.
    *   After the nested for-loop is ended it adds to the ArrayList<Object[]> component the completed Object array component dataInside.
    *   Later, it creates a new 2D Object array component that indicates the data of the JTable component.
    *   With a double for-loop for the 2D array, it parses each Object respectively.
    *   If there is an exception it will continue the procedure.
    *   Finally, it updates the new model to the MyJTable component 
    *   and also updates the MyToolBar component of its data.
    * </p2>
    * @since 1.0
    */
    private void makeUnderstandableByTheTable() {
        for(int i=0;i<jsonArray.size();i++){
            obj = jsonArray.getJsonObject(i);
            dataInside = new Object[columnNames.length];
            for(int e=0;e<columnNames.length;e++){
                try{
                    
                    dataInside[e] = obj.get(columnNames[e]);
                }catch(Exception ss){
                    ss.printStackTrace();
                    dataInside[e] = null;
                    continue;
                }
            }
            
            dataList.add(dataInside);          
        }
        data = new Object[jsonArray.size()][columnNames.length];
        for(int i=0;i<jsonArray.size();i++){
            for(int e=0;e<columnNames.length;e++){
                try{
                    data[i][e] = dataList.get(i)[e];
                }catch(Exception ss){
                    ss.printStackTrace();
                    continue;
                }
            }
        }
        singelInstanceMyJTable.setModel(model = new MyTableModel(data,columnNames)); //<--this updates the jtable
        panel.getToolBar().setData(data);
    }
    
    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public void setData(Object[][] data) {
        singelInstanceMyJTable.data = data;
    }
    
    /**
    * <p>The initial components of refresh(Object[][] data, String[] columnNames) method.
    * </p>
    * <p2>Every time there is an update to the JTable this method is called.
    * It updates the data wherever it was initialized.
    * </p2>
    * @param data <Object[][] the data of the JTable>
    * @param data <String[] the name of the columns of the JTable>
    * @since 1.0
    */
    public void refresh(Object[][] data, String[] columnNames){
        
        if(data.length == 0){
            JOptionPane.showMessageDialog(new JFrame(), "Database is empty...",
                "Failed", JOptionPane.ERROR_MESSAGE);
            textArea.addAction("Database is empty.");
        }    
        else{
            singelInstanceMyJTable.setModel(model = new MyTableModel(data,columnNames));
            this.data = data;
            panel.setData(data);
            panel.getToolBar().setData(data);
            panel.getToolBar().setTable(this);
        }
    }

    /**
    * <p>The initial components of setFile(File file) method.
    * </p>
    * <p2>It calls the makeJSONArray() method and updates the data where ever it needs to be updated.
    * </p2>
    * @param file <File the actual .json file>
    * @since 1.0
    */
    public void setFile(File file) {
        this.file = file;
        makeJSONArray();
        panel.setData(data);
        panel.getToolBar().setTable(this);
    }

    public static Object[][] getData() {
        return singelInstanceMyJTable.data;
    }

    public String[] getColumnNames() {
        return columnNames;
    }
    
    // ALTERED
    public static MyJTable getMyJTable(String[] columnNames,Object[][] data,MyPanel panel,MyTextArea textArea, JLabel lbl){
        if(singelInstanceMyJTable == null) singelInstanceMyJTable = new MyJTable(columnNames,data,panel,textArea,lbl);
        
        return singelInstanceMyJTable;
    }
    
}
