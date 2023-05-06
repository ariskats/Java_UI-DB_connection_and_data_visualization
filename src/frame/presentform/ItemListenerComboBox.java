package frame.presentform;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import json_handler.AreaGraph;

/*
 *  This class listens to the items in the two comboboxes in the visualization form.
 *  
 */

public class ItemListenerComboBox implements ItemListener{

    private ChooseForm form;
    private JComboBox box;
    private String item;
    private String[] arrayOfItems;
    
    /**
    * Creates a constructor used by the visualization form.
    * @param form  <the form with the comboboxes and the 3 buttons>
    * @param box  <the dropdown>
    * @param item  <the name of the selected item>
    * @param arrayOfItems  <the array of items displayed in the combobox>
    */
    public ItemListenerComboBox(ChooseForm form,JComboBox box, String item,String[] arrayOfItems) {
        this.form = form;
        this.box = box;
        this.item = item;
        this.arrayOfItems = arrayOfItems;
    }
    
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(box.getToolTipText().equals("comboBox1")){
            form.setItemSelected1(box.getSelectedItem().toString());
        }else{
            if(!box.getSelectedItem().toString().equals("-----")){
                form.getPieChartButton().setEnabled(false);
            }else{
                form.getPieChartButton().setEnabled(true);
            }
            form.setItemSelected1(box.getSelectedItem().toString());
        }
    }
    
}
