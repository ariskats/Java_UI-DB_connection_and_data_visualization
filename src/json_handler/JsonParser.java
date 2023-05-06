package json_handler;

import frame.MyTextArea;
import frame.presentform.ChooseForm;
import java.util.ArrayList;
import java.util.List;
import javax.json.*;

/*
 *  This class creates a form for the user to choose what and how to visualize it.
 *  It containts 2 jlabels 3 buttons and 2 drop downs.
 */

public class JsonParser {

    private MyTextArea textArea;
    private Object[][] data;
    private String[] columnNames;
    private boolean method;
    
    public JsonParser(MyTextArea textArea,Object[][] data,String[] columnNames){
        this.textArea = textArea;
        this.data = data;
        this.columnNames = columnNames;
    }
    
    private static ArrayList<Individual> list = new ArrayList<Individual>();
    private static ArrayList<IndividualDouble> listDouble = new ArrayList<IndividualDouble>();
    private static ArrayList<String> listOfValues = new ArrayList<String>();  
    
    private static String category1;
    private static String category2;
    private static boolean singlecat = false;
    
    private static JsonReader reader;
    private static JsonArray jsonArray;
    private static JsonObject obj;
    
    public void activate() {
        
        ChooseForm form = new ChooseForm(textArea,this);
        if(!method){
            textArea.setText("Displaying presentation form.");
            readTable();
            
        }else{
            textArea.setText("Closed the presentation form.");
        }
    }

    private static void theChart(List<Individual> list) {
        String choice = ChooseForm.getChoice();
        if(choice.equals("pie")) new PieChart("Pie Chart",list);
        if(choice.equals("bar")) new BarChart("Bar Graph",list,true);
        if(choice.equals("area")) new AreaGraph("Area Graph",list,true);   
    }

    private static void theChartDouble(List<IndividualDouble> listDouble) {
        String choice = ChooseForm.getChoice();
        if(choice.equals("bar")) new BarChart("Bar Graph",listDouble);
        if(choice.equals("area")) new AreaGraph("Area Graph",listDouble);   
    }

    private void readTable() {
        
        if (singlecat) {
            list = CategorizeBy(category1);
        } else {
            listDouble = CategorizeBy2(category1, category2);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println("list: " + i + " is the item: " + list.get(i).getItem() + " and has: " + list.get(i).getNumber());
        }
        for (int i = 0; i < listDouble.size(); i++) {
            System.out.println("listDouble: " + i + " is the item: " + listDouble.get(i).getItem1() + "/" + listDouble.get(i).getItem2() + " and has: " + listDouble.get(i).getNumber());
        }

        if (singlecat) {
            theChart(list);
        } else {
            theChartDouble(listDouble);
        }
    }

    private ArrayList<Individual> CategorizeBy(String category1) {
        ArrayList<Individual> list = new ArrayList<Individual>();
        Individual ind = null;
        int thesis=0;
        boolean flag = false;
        /*
         *  Finds the category in the columnNames to figure out the thesis.
        */
        for(int i=0;i<columnNames.length;i++){
            if(category1.equals(columnNames[i])){
                thesis = i;
                System.out.println("vrikame katigoria: " + category1.toString());
                break; //<-- 01-02-2020
            }
        }
        
        for(int i=0;i<data.length;i++){
            Object tempData2 = new Object();
            Object[] tempData = new Object[data[0].length];
            tempData = data[i];
            
            tempData2 = data[i][thesis];
            if(tempData2 == null){
                tempData2 = "null";
            }else if(tempData2.equals("null")){
                tempData2 = "null";
            }
            try{
                flag = false;
                
                outer: for(int e=0;e<list.size();e++){
//                    if(tempData2.equals("null")){
//                        list.get(e).addNumber();
//                        flag = true;
//                        break outer;
//                    }
                    if(tempData2.toString().equals(list.get(e).getItem())){
                        list.get(e).addNumber();
                        flag = true;
                        break outer;
                    }  
                }
            }catch(Exception e){
                e.printStackTrace();
                continue;
            }            
            
            if(!flag){
                ind = new Individual(category1,tempData2.toString());
                ind.addNumber();
                list.add(ind);
            }
        }
        return list;
    }

    private ArrayList<IndividualDouble> CategorizeBy2(String category1, String category2) {
        ArrayList<IndividualDouble> list = new ArrayList<IndividualDouble>();
        IndividualDouble ind = null;
        int poscat1=-1,poscat2=-1;
        boolean flag = false;
        
        for(int i=0;i<columnNames.length;i++){
            if(category1.equals(columnNames[i])){
                poscat1 = i;
            }
            if(category2.equals(columnNames[i])){
                poscat2 = i;
            }
        }
        
        for(int i=0;i<data.length;i++){
            Object tempData1 = new Object();
            Object tempData2 = new Object();
            tempData1 = data[i][poscat1];
            if(tempData1 == null){
                tempData1 = "null";
            }
            tempData2 = data[i][poscat2];
            if(tempData2 == null){
                tempData2 = "null";
            }
            try{
                flag = false;
                
                outer: for(int e=0;e<list.size();e++){
//                    if(tempData1.equals("null") || tempData2.equals("null")){
//                        list.get(e).addNumber();
//                        flag = true;
//                        break outer;
//                    }
                    if(tempData1.toString().equals(list.get(e).getItem1()) && tempData2.toString().equals(list.get(e).getItem2())){
                        list.get(e).addNumber();
                        flag = true;
                        break outer;
                    }  
                }
            }catch(Exception e){
                continue;
            }            
            
            if(!flag){
                ind = new IndividualDouble(category1, category2, tempData1.toString(),tempData2.toString());
                ind.addNumber();
                list.add(ind);
            }
        }
        return list;
    }

    private static String listAddValue(String s) {
        listOfValues.add(s);
        return s;
    }
    
    public static ArrayList<String> getList(){
        return listOfValues;
    }
    
    public static void setCategories(String s1, String s2){
        category1 = s1;
        category2 = s2;
        singlecat = s2.equals("-----");
    }

    public void setMethod(boolean method) {
        this.method = method;
    }
    
}



