package frame.downloadForm;

import frame.MyButton;
import frame.buttonActionListeners.ActionListenerYesNo;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;

/*
 *  This class handles the replacement of the .json file.
 *  It displays a a window with two optoins for the user to choose.
 *  If the user selects yes, then the file will be downloaded and replaced.
 *  If the user selects no, then the file will be loaded from the already saved location.
 */

public class ReplaceFile extends JDialog{
    
    private MyButton yes,no;
    private Label exists = new Label("File already exists.\nDo you want to replace it?");
    private boolean method;
    private boolean wasWindowClosed,proceed;
    private DownloadController controller;
    
    /**
    * Creates a constructor used by the load button.
    * @param controller  <The class that contains the .json file that was been pre-handled>
    */
    public ReplaceFile(DownloadController controller){
        super(new JFrame(), "File already exists",true);
        this.controller = controller;
        initComponents();
    }
    
    
    /**
    * <p>The initial components of this class.
    * </p>
    * <p2>
    *   Creates a jdialog with two option for the user to choose.
    *   Yes/No area added to the jdialog and every time one is pressed it terminates the jdialog.
    * </p2>
    * @since 1.0
    */
    private void initComponents() {
        
        this.setLayout(new FlowLayout());
        
        yes = new MyButton("Yes","replaces the file","/images/Apply.png");
        no = new MyButton("No","doen't replace the file","/images/Delete.png");
        
        ActionListenerYesNo yesActionListener = new ActionListenerYesNo("Yes",this);
        ActionListenerYesNo noActionListener = new ActionListenerYesNo("No",this);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e)
            {
                wasWindowClosed = true;
                controller.setWasWindowClosed(true);
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                wasWindowClosed = true;
                controller.setWasWindowClosed(true);
            }
        });
        
        
        
        yes.setButtonActionListener(yesActionListener);
        no.setButtonActionListener(noActionListener);

        this.add(exists);
        this.add(yes);
        this.add(no);
        
        this.setSize(300,200);
        this.setVisible(true);
        
        this.setResizable(false); //<--doesnt work
//        if(!yesActionListener.equals(null)){
//            wasWindowClosed = false;
//        }else if(!noActionListener.equals(null)){
//            wasWindowClosed = false;
//        }
    }
    
    public void setReplacementMethod(boolean method){
        this.method = method;
        controller.setMethod(method);
    }
    
    public boolean replace(){
        return method;
    }

    public boolean isProceed() {
        return proceed;
    }
    
    public boolean isWindowClosed() {
        return wasWindowClosed;
    }
}
