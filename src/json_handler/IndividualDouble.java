package json_handler;

import java.util.Comparator;

public class IndividualDouble implements Comparator<IndividualDouble>{
    
    private String item1 = null, item2 = null, category1, category2;
    private int number = 0;
    
    public IndividualDouble(String category1, String category2, String item1, String item2){
        this.item1 = item1;
        this.item2 = item2;
        this.category1 = category1;
        this.category2 = category2;
        number = 0;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category) {
        this.category1 = category;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category) {
        this.category2 = category;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item) {
        this.item1 = item;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item) {
        this.item2 = item;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public void addNumber(){
        this.number++;
    }
    
    @Override
    public int compare(IndividualDouble indA,IndividualDouble indB) {
        // TODO: Handle null x values
        int startComparison = compare(indA.getNumber(), indB.getNumber());
        return startComparison != 0 ? startComparison
                                    : compare(indA.getNumber(), indB.getNumber());

        
    }
    private static int compare(long a, long b) {
        return a < b ? -1
             : a > b ? 1
             : 0;
        }
}
