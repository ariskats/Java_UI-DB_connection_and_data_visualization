package frame;

import java.sql.Connection;

public class Interconnector {

    // there can only be one (It's like singleton, but not exactly... you get a non-null instance of the class only the first time you call "get()", so encapsulation is retained
    private static boolean flag = true;

    /**
     * To get an Interconnector instance, call Interconnector.get()
     *
     */
    private Interconnector() {
    }

    private static String[] columnNames;
    private static Connection conn;

    /**
     * Create an Interconnector.
     *
     * This class is used for carrying data throughout the program. Only one
     * instance of this class can exist at any time. It stores variables in a
     * static context but gives access to them only through the instance. This
     * ensures that encapsulation is retained.
     *
     * @return the single interconnector instance or null if the instance
     * already exists
     */
    public static Interconnector get() {
        if (flag) {
            flag = false;
            return new Interconnector();
        } else {
            return null;
        }
    }

    public void setConnection(Connection c) {
        conn = c;
    }

    public Connection getConnection() {
        return conn;
    }

    public void setColumns(String[] s) {
        columnNames = s;
    }

    public String[] getColumns() {
        return columnNames;
    }

}
