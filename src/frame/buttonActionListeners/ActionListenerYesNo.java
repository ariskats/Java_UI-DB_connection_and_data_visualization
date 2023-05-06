package frame.buttonActionListeners;

import frame.downloadForm.ReplaceFile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *  This class listens for button presses of the buttons Yes/No.
 *  Weather Yes or No is pressed, it will trigger the equivalent action.
 *  After the selection the form that the buttons are used gets terminated.
 */

public class ActionListenerYesNo implements ActionListener{

    private String answer;
    private ReplaceFile form;
    
    /**
	 *  Create a ActionListenerYesNo.
	 *
	 *  @param answer   <What the button text was>
	 *  @param form  <the form the buttons are on>
	 */
    public ActionListenerYesNo(String answer,ReplaceFile form){
        this.answer = answer;
        this.form = form;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(answer.equals("Yes")){
            form.setReplacementMethod(true);
            form.dispose();
        }else{
            form.setReplacementMethod(false);
            form.dispose();
        }
    }
}
