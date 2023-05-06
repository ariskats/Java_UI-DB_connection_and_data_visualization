package frame;

import frame.Table.MyJTable;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 *  This class creates a panel that holds the three main compononents: tool bar, text area, table.
 */

public class MyPanel extends JPanel{
    
    //Default values
    static String[] columnNames = {"cmplnt_num", "addr_pct_cd", "boro_nm", "cmplnt_fr_dt", "cmplnt_fr_tm", "cmplnt_to_dt", "cmplnt_to_tm", "crm_atpt_cptd_cd", "hadevelopt", "housing_psa"
            , "jurisdiction_code", "juris_desc", "ky_cd", "law_cat_cd", "loc_of_occur_desc", "ofns_desc", "parks_nm", "patrol_boro", "pd_cd", "pd_desc", "prem_typ_desc", "rpt_dt"
            , "station_name", "susp_age_group", "susp_race", "susp_sex", "transit_district", "vic_age_group", "vic_race", "vic_sex"};
    static Object[][] data = {
        {"", "", "", "", "", "", "", "", "", ""
        , "", "", "", "", "", "", "", "", "", "", "", ""
        , "", "", "", "", "", "", "", ""}
    };
        
    private MyToolBar toolBar;
    private MyTextArea textArea = new MyTextArea();
    private MyJTable table;
    
    /**
    * Creates a constructor used by MyJFrame component to hold and display information.
     * @param interconnector
    */
    public MyPanel(Interconnector interconnector){
        super();
        initComponents(interconnector);
    }
    
    /**
    * <p>The initial components of this constructor.
    * </p>
    * <p2>sets the layout, adds the tool bar, adds a scrollable MyJTable,
    * adds a textArea to display the updates.
    * The toolBar is given the MyJTable component the textArea and the column names.
    * The panel is set visible.
    * </p2>
    * @since 1.0
    */
    private void initComponents(Interconnector interconnector) {
        setLayout(new BorderLayout(3,1));
    // ALTERED
        JLabel lbl = new JLabel();
        toolBar = new MyToolBar(interconnector,lbl);
        table = MyJTable.getMyJTable(columnNames,data,this,textArea,lbl);
        add(toolBar,BorderLayout.NORTH);
        add(new JScrollPane(table));
        add(textArea,BorderLayout.SOUTH);
        toolBar.setTable(table);
        toolBar.setTextArea(textArea);
        toolBar.setColumnNames(columnNames);
        setVisible(true);
        interconnector.setColumns(columnNames);
        
    }
    
    public MyTextArea getTextArea(){
        return textArea;
    }
    
    public MyJTable getTable() {
        return table;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public MyToolBar getToolBar() {
        return toolBar;
    }
    
    public static String[] getDataList(){
        return columnNames;
    }
    
    
}
