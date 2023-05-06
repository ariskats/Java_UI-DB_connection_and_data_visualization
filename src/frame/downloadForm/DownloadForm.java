package frame.downloadForm;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.*;
import javax.servlet.http.HttpServlet;
import javax.swing.JDialog;
import javax.swing.JFrame;

/*
 *  This class creates a form for the user to select weather to download
 *  the file and replace it or load it from the already saved one.
 */

public class DownloadForm extends HttpServlet{
    
    private static JDialog dialog;  
    private JFrame form = new JFrame();
    private Label exists = new Label("Now Downloading");
    private boolean proceed;
    
    public DownloadForm(){
        initComponents();
    }
    
    private void initComponents() {
        dialog = new JDialog(new JFrame(), "Downloading",true);
        dialog.setLayout(new FlowLayout());
        
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e)
            {
                System.out.println("jdialog window closed event received");
                proceed = false;
            }

            public void windowClosing(WindowEvent e)
            {
                System.out.println("jdialog window closing event received");
                proceed = false;
            }
        });
        
        dialog.add(exists);
        
        dialog.setSize(300,200);
        dialog.setVisible(true);
        
        dialog.setResizable(false); //<--doesnt work
        form.setResizable(false); //<--doesnt work
        
    }
}
