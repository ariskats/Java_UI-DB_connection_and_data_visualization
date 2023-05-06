package json_handler;

import java.util.Comparator;

public class Individual implements Comparator<Individual>{
    
    private String item = null, category;
    private int number = 0;
    
    public Individual(String category, String item){
        this.category = category;
        this.item = item;
        number = 0;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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
    public int compare(Individual indA,Individual indB) {
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
