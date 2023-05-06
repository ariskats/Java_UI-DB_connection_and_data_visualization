package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import org.jfree.ui.RefineryUtilities;

/*
 *  This class creates the main frame used by the program.
 *  It is a singleton class
 */

public class MyFrame extends JFrame{
    
    private static MyFrame singelInstanceMyFrame = null;
    private MyPanel panel;
    private String title;
    
    /**
    * Creates a constructor used by Main to display all the information.
    * @param title  <The String component to set the title of the Frame>
    */
    private MyFrame(String title){
        super();
        this.title = title;
        Interconnector interconnector = Interconnector.get();
        initComponents(interconnector);
    }

    /**
    * <p>The initial components of this constructor.
    * </p>
    * <p2>Sets the title, centers the frame in the middle of the screen,
    * sets the default size, sets the location of the frame, adds the JPanel component,
    * sets the DefaultCloseOperation to terminate the program after the user closes the frame and 
    * sets the JFrame component visible.
    * </p2>
    * @since 1.0
    */
    private void initComponents(Interconnector interconnector) {
        setTitle(title);
        RefineryUtilities.centerFrameOnScreen(this);
        setSize(800,600);
        setLocation(250,100);
        JLabel lbl = new JLabel();
        panel = new MyPanel(interconnector);
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        //pack();
    }

    /**
    * <p>The constructor [Singleton].
    * </p>
    * <p2>Creates the singleton constructor of MyJFrame.
    * </p2>
    * @since 1.0
    */
    public static MyFrame getMyFrame(String title){
        if(singelInstanceMyFrame == null) singelInstanceMyFrame = new MyFrame(title);
        
        return singelInstanceMyFrame;
    }
}
