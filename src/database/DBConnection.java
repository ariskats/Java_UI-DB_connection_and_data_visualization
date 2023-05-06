package database;

import frame.Interconnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//--------------Database Connection------------------
//Singleton class

public class DBConnection {
    
    private String dbUser,dbPass,dbHost,dbPort,dbName;
    
    private static DBConnection single_instance = null;
    private Connection con = null;
    
    /**
    * Creates a constructor used by the Database to connect to the database.
    * @param dbUser  <The name of the user in the databse>
    * @param dbPass  <The password of the database>
    * @param dbHost  <The host of the database>
    * @param dbPort  <The port of the database>
    * @param dbName  <The name of the databse>
    */
    private DBConnection(String dbUser,String dbPass, String dbHost, String dbPort,String dbName,Interconnector interconnector){
        this.dbUser = dbUser;
        this.dbPass = dbPass;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbName = dbName;
        initComponents(interconnector);
    }
    
    private void initComponents(Interconnector interconnector) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            String url = "jdbc:mysql://" +dbHost+ ":" +dbPort+"/"+dbName;
            
            con = DriverManager.getConnection(url, dbUser, dbPass);
            interconnector.setConnection(con);
            System.out.println("Connection established successfully!");
                
            
        }catch(ClassNotFoundException e){
            System.out.println("Could not connect to database.");
        }catch(SQLException ex){
            System.out.println("ERROR: Connection not established.");
            //ex.printStackTrace(System.out);
            try{
                System.out.println("Conection yes?");
                con.clearWarnings();
            }catch(SQLException exe){
                exe.printStackTrace(System.out);
            }
        }
    }

    public Connection getCon() {
        return con;
    }
    
    /**
    * <p>The constructor [Singleton].
    * </p>
    * <p2>Creates the singleton constructor of the database.
    * </p2>
    * @since 1.0
    */
    public static DBConnection getDBConnection(String dbUser,String dbPass, String dbHost, String dbPort,String dbName, Interconnector interconnector){
        
        if(single_instance == null) single_instance = new DBConnection(dbUser,dbPass,dbHost,dbPort,dbName,interconnector);
        
        return single_instance;
    }
    
}
