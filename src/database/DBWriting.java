package database;

import frame.Interconnector;
import frame.MyPanel;
import frame.MyProgressBar;
import frame.MyTextArea;
import frame.Table.MyJTable;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DBWriting {
    
    private MyTextArea textArea;
    private String[] arrayOfStrings;
    private Connection con;
    private Object[][] data;
    
    /**
    * Creates a constructor used by the Database to parse the data(textArea).
    * @param MyTextArea  <Creates a constructor with parameters MyTextArea>
    */
    public DBWriting(MyTextArea textArea, Interconnector interconnector){
        this.textArea = textArea;
        con = interconnector.getConnection();
        data = MyJTable.getData();
        arrayOfStrings = MyPanel.getDataList();
        initComponents();
    }

    /**
    * <p>The initial components of this constructor.
    * </p>
    * <p2>Parses the data taken from the textArea component,
    * if the textArea contains a String component that contains the character: " ' ",
    * then it's been replaced by "".
    * </p2>
    * @since 1.0
    */
    private void initComponents() {
        
        try{
            int cnt = 0;
            Statement stmt = con.createStatement();
            String query = "DELETE FROM users";
            stmt.executeUpdate(query);
            for(int i=0;i<data.length;i++){
                query = "INSERT INTO users ("
                        + "cmplnt_num,"
                        + "addr_pct_cd,"
                        + "boro_nm,"
                        + "cmplnt_fr_dt,"
                        + "cmplnt_fr_tm,"
                        + "cmplnt_to_dt,"
                        + "cmplnt_to_tm,"
                        + "crm_atpt_cptd_cd,"
                        + "hadevelopt,"
                        + "housing_psa,"
                        + "jurisdiction_code,"
                        + "juris_desc,"
                        + "ky_cd,"
                        + "law_cat_cd,"
                        + "loc_of_occur_desc,"
                        + "ofns_desc,"
                        + "parks_nm,"
                        + "patrol_boro,"
                        + "pd_cd,"
                        + "pd_desc,"
                        + "prem_typ_desc,"
                        + "rpt_dt,"
                        + "station_name,"
                        + "susp_age_group,"
                        + "susp_race,"
                        + "susp_sex,"
                        + "transit_district,"
                        + "vic_age_group,"
                        + "vic_race,"
                        + "vic_sex)"
                        + " VALUES ('";
                for(int e=0;e<arrayOfStrings.length;e++){
                    String temp;
                    try{
                        temp = data[i][e].toString();
                        if(temp.contains("'")){
                            temp=temp.replaceAll("'", "");
                        }
                        if(e == arrayOfStrings.length-1 ){
                            query += temp;
                        }
                        else query += temp+"','";
                    }catch(Exception yeet){
                        temp = "null";
                        if(e == arrayOfStrings.length-1 ){
                            query += temp;
                        }
                        else query += temp+"','";
                        continue;
                    }
                }
                query += "')";
                System.out.println("Inserting row " + (i+1) + " of " + data.length + "...");
                int x = stmt.executeUpdate(query);
                cnt += x;
            }
            
            System.out.println(cnt+" rows affected!");            
            stmt.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(), "There was a problem writing to the database."
                       , "Writing Failed", JOptionPane.ERROR_MESSAGE);
            textArea.addAction("There was a problem writing to the database.");
//            System.out.println(obj.toString());
            System.out.println("Error: Could not write in the databasu.");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(new JFrame(), "Successfully parsed the data."
                       , "Writing successful", JOptionPane.INFORMATION_MESSAGE);
        textArea.addAction("Successfully parsed the data.");
    }
}
