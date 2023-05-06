package frame;

import javax.swing.JTextArea;

/*
 *  This class creates a text area that is locates in the bottom of the main frame.
 *  It is used to display and keep the last action the user has done.
 */

public class MyTextArea extends JTextArea{
    
    private String text;
    
    /**
    * Creates a constructor used by the MyJPanel component.
    */
    public MyTextArea(){
        super();
        initComponents();
        addAction("Console Begin.");
    }

    /**
    * <p>The initial components of this constructor.
    * </p>
    * <p2>Sets the component to not be editable and sets it to be visible
    * </p2>
    * @since 1.0
    */
    private void initComponents() {
        setEditable(false);
        setVisible(true);
    }
    
    /**
    * <p>addAction.
    * </p>
    * <p2>Every time a component that uses the MyTextArea component calls this method, 
    * the text displayed is changes and it displays the given text.
    * </p2>
    * @param String <The String component to change the text of the MyTextArea component.>
    * @since 1.0
    */
    public void addAction(String text){
        //text.concat(text);
        setText(text);
    }
    
}
