package frame.buttonActionListeners;

import frame.databaseForm.DatabaseForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *  This class listens for button presses of the button Send.
 *  Everytime it is pressed it will terminate the window form and send the user data.
 */

public class ActionListenerForm implements ActionListener{
    
    private boolean proceed = false;
    private DatabaseForm form;
    
    /**
	 *  Create a ActionListenerDelete.
	 *
	 *  @param form   <the form for the user to input the credentials>
	 */
    public ActionListenerForm(DatabaseForm form){
        this.form = form;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        form.setDbUser(form.getDbUser());
        form.setDbPass(form.getDbPass());
        form.setDbHost(form.getDbHost());
        form.setDbPort(form.getDbPort());
        form.setDbName(form.getDbName());
        proceed = true;
        form.dispose();
    }

    public boolean isProceed() {
        return proceed;
    }
    
}
