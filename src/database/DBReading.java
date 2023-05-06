package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBReading {
    //NOT USED ANYMORE//
    private Connection con;
    
    /**
    * Creates a constructor used by the Database to read data from the database by 
    * using the connection to the database.
    * @param Connection  <Creates a constructor with parameters Connection>
    */
    public DBReading(Connection con){
        this.con = con;
        initComponents();
    }

    private void initComponents() {
        try{
            Statement stmt = con.createStatement();
            
            String query = "SELECT * FROM users";
            
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                
                System.out.println("This is the user from the database with id: " 
                    + id + " name: " + firstName 
                    + " last name: " + lastName
                    + " and email: " + email
                );
            }
            
            stmt.close();
            
        }catch(Exception e){
            System.out.println("Error: could not read from the databasu.");
        }
    }
}
