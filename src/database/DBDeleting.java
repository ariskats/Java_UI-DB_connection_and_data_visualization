package database;

import java.sql.Connection;
import java.sql.Statement;

public class DBDeleting {
    
    private Connection con;
    
    /**
    * Creates a constructor used by the Database to delete all the data from the database.
    * @param con  <Creates a constructor with parameters Connection>
    */
    public DBDeleting(Connection con){
        this.con = con;
        initComponents();
    }

    private void initComponents() {
        try{
            Statement stmt = con.createStatement();
            
            String query = "DELETE FROM users";
                        
            int x = stmt.executeUpdate(query);
            
            System.out.println(x+" rows affected!");
            
            stmt.close();
            
        }catch(Exception e){
            System.out.println("Error: could not delete from the databasu.");
        }
    }
    
}
