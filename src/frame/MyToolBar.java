package frame;

import frame.Table.MyJTable;
import frame.buttonActionListeners.*;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import tableListener.ActionListenerTable;

/*
 *  This class creates the tool bar shown in the top of the main frame.
 *  it holds all the buttons and is used to connect the majority of the 
 *  components together.
 */

public class MyToolBar extends JToolBar{
    
    static boolean firstTime = true;
    private MyButton loadButton,connectButton,parseButton,representButton,infoButton,searchButton, deleteAllButton, dbButton, addButton;
    private Object[][] data;
    private ActionListenerConnect connectActionListener;
    private ActionListenerLoad loadActionListener;
    private ActionListenerParse parseActionListener;
    private ActionListenerRepresent representActionListener;
    private ActionListenerInfo infoActionListener;
    private ActionListenerSearch searchActionListener;
    private ActionListenerAdd addActionListener;
    private ActionListenerDBLoad dbActionListener;
    private ActionListenerDelete deleteActionListener;
    private MyJTable table;
    private MyFilter filter;
    private MyTextArea textArea;
    private String[] columnNames;
    
    /**
    * Creates a constructor used by the MyJFrame component to display the buttons.
     * @param interconnector
    */
    public MyToolBar(Interconnector interconnector,JLabel lbl){
        intiComponents(interconnector);
    // ALTERED
        addExtra(lbl);
    }

    /**
    * <p>The initial components of this constructor.
    * </p>
    * <p2>Creates the buttons, creates the action listeners, adds the creates action listeners,
    * adds a separator line.
    * Sets the MyToolBar component to not be able to float by the user.
    * adds all the buttons and a JLabel component at then end of the MyToolBar component.
    * </p2>
    * @since 1.0
    */
    private void intiComponents(Interconnector interconnector) {
        buttonCreation();
        listenerCreation(interconnector);
        addListeners();
        addSeparator();
        setFloatable(false); // σταματάει τις βόλτες του JToolBar
        add(loadButton);
        add(dbButton);
        add(representButton);
        add(parseButton);
        add(connectButton);
        add(addButton);
        add(searchButton);
        add(deleteAllButton);
        add(infoButton);
        add(new JLabel("Filter:"));
    }

    /**
    * <p>The initial components buttonCreation().
    * </p>
    * <p2>Initializes all the buttons that need to be added to the MyJToolBar component.
    * </p2>
    * @since 1.0
    */
    private void buttonCreation() {

        loadButton = new MyButton("Load File","Load data from a file or the internet","/images/Load.png");
        connectButton = new MyButton("Connect","Connects to the database","/images/Database.png");
        parseButton = new MyButton("Parse","Inserts table data into the database","/images/List.png");
        representButton = new MyButton("Represent","Shows the charts","/images/Report.png");
        infoButton = new MyButton("","What this is about","/images/Info.png");
        searchButton = new MyButton("Select","Select a single row to edit","/images/Search16.png");
        dbButton = new MyButton("Load DB","Load data from the database","/images/Download.png");
        addButton = new MyButton("Add","Add a row to the database","/images/Create.png");
        deleteAllButton = new MyButton("Nuke DB","Delete ALL rows from the database","/images/Radiation.png");
        
    }

    /**
    * <p>The initial components listenerCreation().
    * </p>
    * <p2>Initializes all the different action listeners that need to be added to each MyJButton component.
    * </p2>
    * @since 1.0
    */
    private void listenerCreation(Interconnector interconnector) {
        
        connectActionListener = new ActionListenerConnect(connectButton,loadButton,searchButton,dbButton,addButton,deleteAllButton,interconnector);
        parseActionListener = new ActionListenerParse(parseButton,interconnector);
        representActionListener = new ActionListenerRepresent(representButton);
        loadActionListener = new ActionListenerLoad(loadButton,parseButton,representButton);
        infoActionListener = new ActionListenerInfo();
        searchActionListener = new ActionListenerSearch(searchButton,interconnector);
        addActionListener = new ActionListenerAdd(addButton, interconnector);
        dbActionListener = new ActionListenerDBLoad(dbButton,parseButton,representButton,interconnector);
        deleteActionListener = new ActionListenerDelete(deleteAllButton,interconnector);
        
    }

    /**
    * <p>The initial components addListeners().
    * </p>
    * <p2>Sets all the different action listeners to each MyJButton component respectively.
    * </p2>
    * @since 1.0
    */
    private void addListeners() {
        loadButton.setButtonActionListener(loadActionListener);
        connectButton.setButtonActionListener(connectActionListener);
        parseButton.setButtonActionListener(parseActionListener);
        representButton.setButtonActionListener(representActionListener);
        infoButton.setButtonActionListener(infoActionListener);
        searchButton.setButtonActionListener(searchActionListener);
        addButton.setButtonActionListener(addActionListener);
        dbButton.setButtonActionListener(dbActionListener);
        deleteAllButton.setButtonActionListener(deleteActionListener);
    }

    /**
    * <p>The initial components addConsoleListener().
    * </p>
    * <p2>Sets the MyTextArea component to each action listener component respectively.
    * The MyTextArea component will be used to simulate a console.
    * </p2>
    * @since 1.0
    */
    private void addConsoleListener(){
        connectActionListener.setConsole(textArea);
        loadActionListener.setConsole(textArea);
        parseActionListener.setConsole(textArea);
        representActionListener.setConsole(textArea);
        infoActionListener.setConsole(textArea);
        searchActionListener.setConsole(textArea);
        addActionListener.setConsole(textArea);
        dbActionListener.setConsole(textArea);
        deleteActionListener.setConsole(textArea);
    }
    
    /**
    * <p>Sets the MyTextArea component to the MyToolBar component
    * and re-calls the addConsoleListener() to update the other affected components.
    * @since 1.0
    */
    public void setTextArea(MyTextArea textArea) {
        this.textArea = textArea;
        addConsoleListener();
    }
    
    /**
    * <p>
    * Sets the MyJTable component to the MyToolBar component
    * and re-adds the MyJTable component to LoadActionListener component and 
    * dbActionListener respectively to update the affected components.
    * </p>
    * <p2>
    * If this is the first time this method is called, then it will add a new
    *   MyFilter component to the MyToolBar component according to the given MyJTable component in the method.
    *   After that addition, it will repaint and revalidate the MyToolBar component 
    *   just in case there was a visible error.
    *   Finally it will flip the Boolean component firstTime flag to false, 
    *   to indicate that the first time was initiated.
    * Else it will remove the MyFilter component, it will initialize a new one and add it to 
    *   the MyToolBar component.
    *   Finally it will repaint and revalidate the MyToolBar component 
    *   just in case there was a visible error.
    * </p2>
    * @param MyJTable <The JTable>
    * @since 1.0
    */
    public void setTable(MyJTable table){
        this.table = table;
        loadActionListener.setTable(table);
        dbActionListener.setTable(table);
        if(firstTime){
            filter = new MyFilter(table);
            add(filter);
            repaint();
            revalidate();
            firstTime = false;
        }else{
            remove(filter);
            filter = new MyFilter(table);
            add(filter);
            repaint();
            revalidate();
        }
    }

    /**
    * <p>
    * Sets the data of the JTable component to the MyToolBar component,
    * and re-sets the data of the RepresentActionListener to update it.
    * </p>
    * @param Object[][] <Data of the JTable>
    * @since 1.0
    */
    public void setData(Object[][] data) {
        this.data = data;
        representActionListener.setData(data);
    }

    /**
    * <p>
    * Sets the set column names of the JTable component to the MyToolBar component,
    * and re-sets the data of the RepresentActionListener to update it.
    * </p>
    * @param String[] <The names of the columns>
    * @since 1.0
    */
    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
        representActionListener.setColumnNames(columnNames);
    }
    
    // ALTERED
    private void addExtra(JLabel lbl){
        MyButton mb = new MyButton("LISTEN", "listen here you lil shit", "/images/Load.png");
        mb.setButtonActionListener(new ActionListenerTable(lbl));
        add(mb);
    }
    
}
