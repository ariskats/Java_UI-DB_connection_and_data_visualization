
package frame.databaseForm;

import frame.*;
import frame.buttonActionListeners.ActionListenerForm;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 *  This class creates a form for the user to input the credentials to connect to the local database.
 *  If the user decides to terminate the window, it will display an error message and will not connect.
 *  If the user decides to input wrong credentials it will display an error message and will not connect.
 *  Else it will display a confirmation message and connect to the database.
 */

public class DatabaseForm extends JDialog{
    
    private JFrame form = new JFrame();
    private boolean proceed;
    private Label userLabel = new Label("User");
    private Label passLabel = new Label("Password");
    private Label hostLabel = new Label("Host");
    private Label portLabel = new Label("Port");
    private Label nameLabel = new Label("Name");
        
    private TextField userField = new TextField(20);
    private TextField passField = new TextField(20);
    private TextField hostField = new TextField(20);
    private TextField portField = new TextField(20);
    private TextField nameField = new TextField(20);
    
    /**
	 *  Create a ActionListenerDelete.
	 *
	 *  @param proceed   <the boolean flag (false by default)>
	 */
    public DatabaseForm(boolean proceed){
        super(new JFrame(),"Database Form",true);
        this.proceed = proceed;
        initComponents();
    }

    private void initComponents() {
        
        this.setLayout(new GridLayout(6,2));
        
        MyButton send = new MyButton("Send","Commit the login info","/images/Forward.png");
        ActionListenerForm sendActionListener = new ActionListenerForm(this);
        send.addActionListener(sendActionListener);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e)
            {
                proceed = false;
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                proceed = false;
            }
        });
        
        userField.setText("root");
        passField.setText("root");
        hostField.setText("localHost");
        portField.setText("3306");
        nameField.setText("userdatabase");
        
        this.add(userLabel);
        this.add(userField);
        this.add(passLabel);
        this.add(passField);
        this.add(hostLabel);
        this.add(hostField);
        this.add(portLabel);
        this.add(portField);
        this.add(nameLabel);
        this.add(nameField);
        
        this.add(send, BorderLayout.CENTER);
        this.setSize(300,200);
        this.setVisible(true);
        
        this.setResizable(false); //<--doesnt work
        proceed = sendActionListener.isProceed();
    }
    
    public String getDbUser() {
        return userField.getText();
    }

    public String getDbPass() {
        return passField.getText();
    }

    public String getDbHost() {
        return hostField.getText();
    }

    public String getDbPort() {
        return portField.getText();
    }

    public String getDbName() {
        return nameField.getText();
    }

    public void setDbUser(String dbUser) {
        userField.setText(dbUser);
    }

    public void setDbPass(String dbPass) {
        passField.setText(dbPass);
    }

    public void setDbHost(String dbHost) {
        hostField.setText(dbHost);
    }

    public void setDbPort(String dbPort) {
        portField.setText(dbPort);
    }

    public void setDbName(String dbName) {
        nameField.setText(dbName);
    }

    public boolean isProceed() {
        return proceed;
    }
    
}
