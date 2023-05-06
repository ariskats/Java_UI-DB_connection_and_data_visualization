package frame;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton{
    
    private String buttonText;
    private String buttonToolTipText;
    private String buttonImagePath;
    private ActionListener buttonActionListener;
    
    /**
    * Creates a constructor used by the whole program, 
    * the majority of the buttons are made by this constructor.
    * The button starts by displaying the following components in the manner presented.
    * @param buttonText  <The name of the button>
    * @param buttonToolTipText  <The tool tip text of the button>
    * @param buttonImagePath  <The icon displayed after the text(if it exists)>
    */
    public MyButton(String buttonText, String buttonToolTipText, String buttonImagePath){
        this.buttonText = buttonText;
        this.buttonToolTipText = buttonToolTipText;
        this.buttonImagePath = buttonImagePath;
        initComponents();
    }

    /**
    * <p>The initial components of this constructor.
    * </p>
    * <p2>Sets the text of the button, sets the toolTipText of the button
    * ,sets the icon of the button and sets it visible.
    * </p2>
    * @since 1.0
    */
    private void initComponents() {
        setText(buttonText);
        setToolTipText(buttonToolTipText);
        setIcon(new ImageIcon(getClass().getResource(buttonImagePath)));
        setVisible(true);
    }

    /**
    * <p>Adds the ActionListener to the button.
    * @since 1.0
    */
    public void setButtonActionListener(ActionListener buttonActionListener) {
        addActionListener(buttonActionListener);
    }
    
}
