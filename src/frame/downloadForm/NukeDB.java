package frame.downloadForm;

import frame.MyButton;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;

/*
 *  This class is used to display a form where the user selects 
 *  weather to nuke the local database or not.
 *  
 */

public class NukeDB extends JDialog{
    
    private MyButton yes,no;
    private Label really = new Label("Do you really want to nuke the database?");
    private static boolean go;
    
    public NukeDB(){
        super(new JFrame(), "Are you sure?",true);
        initComponents();
    }
    
    /**
    * <p>
    *   The initial components of this class.
    * </p>
    * <p2>
    *   This is where the form is created.
    *   Every time the user selects either option the form is terminated.
    * </p2>
    * @since 1.0
    */
    private void initComponents() {
        
        this.setLayout(new FlowLayout());
        
        yes = new MyButton("Yes","delete all rows","/images/Apply.png");
        no = new MyButton("No","cancel deletion","/images/Delete.png");
        
        NukeDB x = this;
        yes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                go = true;
                x.dispose();
            }
        });
        no.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                go = false;
                x.dispose();
            }
        });
        
        this.add(really);
        this.add(yes);
        this.add(no);
        
        this.setSize(300,200);
        this.setVisible(true);
    }
    
    public static boolean resolve(){
        return go;
    }
    
}
