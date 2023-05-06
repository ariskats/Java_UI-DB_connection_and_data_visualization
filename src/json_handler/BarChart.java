package json_handler;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChart extends ApplicationFrame{
    
    private JFreeChart barChart;
    private List<Individual> list;
    private List<IndividualDouble> listDouble;
    private String chartTitle, category1, category2;

    /**
     * Create an BarChart.
     *
     * @param applicationTitle the title of the chart
     * @param list the list it will show, in this case it is a single list
     */
    public BarChart(String applicationTitle, List<IndividualDouble> list) {
        super(applicationTitle);
        category1 = list.get(0).getCategory1();
        category2 = list.get(0).getCategory2();
        this.listDouble = list;
        chartTitle = category1 + " and " + category2;
        step2(createDatasetDouble());
    }

    /**
     * Create an BarChart.
     *
     * @param applicationTitle the title of the chart
     * @param list the list it will show, in this case it is a double list
     * @param singlecat determines that this is a single category graph, the
     * value of this doesn't really matter, only its existence
     */
    public BarChart(String applicationTitle, List<Individual> list, boolean singlecat) {
        super(applicationTitle);
        category1 = list.get(0).getCategory();
        this.list = list;
        chartTitle = category1;
        step2(createDataset());
    }
    
    // this is where both single and double paths collide, the algorithm is the same after this point
    private void step2(CategoryDataset dataset){
        
        barChart = ChartFactory.createBarChart(
                chartTitle,
                "Category",
                "Frequency",
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 376));
        setContentPane(chartPanel);
        this.setSize(560,367);
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }
    
    private CategoryDataset createDataset( ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  
        for(int i=0;i<list.size();i++){
            dataset.addValue( list.get(i).getNumber() , list.get(i).getItem() , category1 );         
        }               

        return dataset; 
    }
    
    private CategoryDataset createDatasetDouble( ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  
        for(int i=0;i<listDouble.size();i++){  
            dataset.addValue( listDouble.get(i).getNumber() , listDouble.get(i).getItem1() , listDouble.get(i).getItem2() );            
        }               

        return dataset; 
    }
    
    @Override
    public void windowClosing(WindowEvent e){
        this.setVisible(false);
        this.dispose();
    }

    /**
     * Save a JPEG image of the graph.
     *
     * @param width image width
     * @param height image height
     * @param name jpeg name
     * @throws java.io.IOException
     */
    public void saveAsJPEG(int width, int height, String name) throws IOException{
        File barChartFile = new File(name+".jpeg");
        ChartUtilities.saveChartAsJPEG(barChartFile, barChart, width, height);
    }
}

