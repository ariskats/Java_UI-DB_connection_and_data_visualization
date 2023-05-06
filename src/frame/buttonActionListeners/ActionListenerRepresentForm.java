package frame.buttonActionListeners;

import frame.MyTextArea;
import frame.presentform.ChooseForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *  This class listens for button presses of the button in the represent form.
 */

public class ActionListenerRepresentForm implements ActionListener{
    
    private String buttonTxt;
    private ChooseForm form;
    private boolean proceed = false;
    private MyTextArea textArea;
    
    /**
	 *  Create a ActionListenerDelete.
	 *
	 *  @param form   <the represent form>
	 *  @param buttonTxt   <the text from the selected button>
	 *  @param proceed   <the boolean flag>
	 *  @param textArea   <the text area (console)>
	 */
    public ActionListenerRepresentForm(ChooseForm form, String buttonTxt,boolean proceed,MyTextArea textArea){
        this.buttonTxt = buttonTxt;
        this.form = form;
        this.proceed = proceed;
        this.textArea = textArea;
    }
    
    /**
    * <p>
    *   When action is performed.
    * </p>
    * <p2>
    *   Sends the text of the button to the ChooseForm class.
    *   Updates the text area with what button was pressed.
    *   Terminates the represent form.
    * </p2>
    * @since 1.0
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        ChooseForm.saveChoices(buttonTxt);
        proceed = true;
        textArea.addAction("Representing " + buttonTxt + " chart");
        form.getDialog().dispose(); //Disposes the JDialog after you press the repressent
    }

    public boolean isProceed() {
        return proceed;
    }
    
}
