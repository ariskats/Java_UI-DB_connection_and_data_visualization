package frame.presentform;

import frame.*;
import frame.buttonActionListeners.ActionListenerRepresentForm;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import json_handler.JsonParser;

/*
 *  This class creates a form for the user to choose what and how to visualize it.
 *  It containts 2 jlabels 3 buttons and 2 drop downs.
 */

public class ChooseForm extends JDialog{
    private JsonParser jsonParser;
    private MyTextArea textArea;
    private JPanel panel = new JPanel();
    private static String asyouadequatelyputtheproblemischoice = "nada";
    private boolean proceed,windowClosed;
    private JLabel instructions1,labelBox1,labelBox2,instructions2,instructions3;
    
    private static JComboBox comboBox1,comboBox2;
    private MyButton pieChartButton,barChartButton,areaChartButton;
    
    private String[] arrayOfStrings1,arrayOfStrings2;
    private String itemSelected1,itemSelected2; //the selected item after it's been selected from the comboBox
    
    private ItemListenerComboBox itemListenerComboBox1,itemListenerComboBox2;
    private ActionListenerRepresentForm actionListenerRepresentFormPie,actionListenerRepresentFormBar,actionListenerRepresentFormArea;
    
    /**
    * Creates a constructor used by JTable to filter out the given parameters.
    * @param textArea  <The text area(console)>
    * @param jsonParser  <The class that contains the form for visualization>
    */
    public ChooseForm(MyTextArea textArea,JsonParser jsonParser){
        super(new JFrame(), "Visualization Form",true);
        this.textArea = textArea;
        this.jsonParser = jsonParser;
        arrayOfStrings1 = MyPanel.getDataList();
        arrayOfStrings2 = new String[arrayOfStrings1.length+1];
        for(int i=0;i<arrayOfStrings1.length;i++) arrayOfStrings2[i] = arrayOfStrings1[i];
        arrayOfStrings2[arrayOfStrings1.length] = "-----";
        jlabelInit();
        intiComponents();
        buttonCreator();
        addEverythingTogether();
    }

    /**
    * <p>The initial components of jlabelInit().
    * </p>
    * <p2>
    *   It initializes the labels for the form.
    * </p2>
    * @since 1.0
    */
    private void jlabelInit() {
        instructions1 = new JLabel("Select what to visualize");
        labelBox1 = new JLabel("Select item 1");
        labelBox1.setSize(2, 2);
        labelBox2 = new JLabel("Select item 2");
        instructions2 = new JLabel("Pie chart only works ");
        instructions3 = new JLabel("when only one item is selected");
        instructions2.setSize(10, 10);
    }
    
    /**
    * <p>The initial components of this class.
    * </p>
    * <p2>
    *   Initializes the combo boxes and adds window listener for the closure of the form.
    * </p2>
    * @since 1.0
    */
    private void intiComponents() {
        this.setSize(200, 300);
        this.setLocation(500, 280);
        
        comboBox1 = new JComboBox(arrayOfStrings1);
        comboBox2 = new JComboBox(arrayOfStrings2);
        comboBox1.setToolTipText("comboBox1");
        comboBox2.setToolTipText("comboBox2");
        panel.setLayout(new FlowLayout());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e)
            {
                windowClosed = false;
                jsonParser.setMethod(true);
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                windowClosed = false;
                jsonParser.setMethod(true);
            }
        });
    }

    /**
    * <p>The initial components of buttonCreator().
    * </p>
    * <p2>
    *   Initializes the buttons for this form.
    * </p2>
    * @since 1.0
    */
    private void buttonCreator() {
        pieChartButton = new MyButton("Pie Chart","Visual Representation with a pie chart","/images/Pie_chart.png");
        barChartButton = new MyButton("Bar Chart","Visual Representation with a bar chart","/images/3d_bar_chart.png");
        areaChartButton = new MyButton("Area Chart","Visual Representation with an area chart","/images/Report.png");
        
        addActionListener();
    }

    /**
    * <p>The initial components of addActionListener().
    * </p>
    * <p2>
    *   Adds to everything an action listener.
    * </p2>
    * @since 1.0
    */
    private void addActionListener() {
        
        itemListenerComboBox1 = new ItemListenerComboBox(this,comboBox1,comboBox1.getSelectedItem().toString(),arrayOfStrings1);
        itemListenerComboBox2 = new ItemListenerComboBox(this,comboBox2,comboBox2.getSelectedItem().toString(),arrayOfStrings2);
        
        comboBox1.addItemListener(itemListenerComboBox1);
        comboBox2.addItemListener(itemListenerComboBox2);
        
        actionListenerRepresentFormPie = new ActionListenerRepresentForm(this,"pie",proceed,textArea);
        actionListenerRepresentFormBar = new ActionListenerRepresentForm(this,"bar",proceed,textArea);
        actionListenerRepresentFormArea = new ActionListenerRepresentForm(this,"area",proceed,textArea);
        
        pieChartButton.setButtonActionListener(actionListenerRepresentFormPie);
        barChartButton.setButtonActionListener(actionListenerRepresentFormBar);
        areaChartButton.setButtonActionListener(actionListenerRepresentFormArea);
        
        if(actionListenerRepresentFormPie.isProceed()){
            proceed = true;
        }else if(actionListenerRepresentFormBar.isProceed()){
            proceed = true;
        }else if(actionListenerRepresentFormArea.isProceed()){
            proceed = true;
        }
    }

    /**
    * <p>The initial components of addEverythingTogether().
    * </p>
    * <p2>
    *   Adds everything in the form and finalizes it.
    * </p2>
    * @since 1.0
    */
    private void addEverythingTogether() {
        comboBox1.setSelectedItem(arrayOfStrings1[2]);
        comboBox2.setSelectedItem(arrayOfStrings2[30]);
        panel.add(instructions1);
        panel.add(labelBox1);
        panel.add(comboBox1);
        panel.add(labelBox2);
        panel.add(comboBox2);
        panel.add(pieChartButton);
        panel.add(barChartButton);
        panel.add(areaChartButton);
        panel.add(instructions2);
        panel.add(instructions3);
        this.add(panel);
        this.setVisible(true);
    }
    
    public void setItemSelected1(String itemSelected1) {
        this.itemSelected1 = itemSelected1;
    }

    public void setItemSelected2(String itemSelected2) {
        this.itemSelected2 = itemSelected2;
    }

    public String getItemSelected1() {
        return itemSelected1;
    }

    public String getItemSelected2() {
        return itemSelected2;
    }

    public MyButton getPieChartButton() {
        return pieChartButton;
    }

    public MyButton getBarChartButton() {
        return barChartButton;
    }

    public MyButton getAreaChartButton() {
        return areaChartButton;
    }

    public JDialog getDialog() {
        return this;
    }
    
    public static void saveChoices(String s){
        asyouadequatelyputtheproblemischoice = s;
        JsonParser.setCategories(comboBox1.getSelectedItem().toString(), comboBox2.getSelectedItem().toString());
    }
    
    public static String getChoice(){
        return asyouadequatelyputtheproblemischoice;
    }

    public boolean isProceed() {
        return proceed;
    }
    
}
