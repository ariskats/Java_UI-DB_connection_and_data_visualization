/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json_handler;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * Create a PieChart in a new JFrame.
 */
public class PieChart extends ApplicationFrame {
    
    private JFreeChart jfchart;
    List<Individual> list;
    

    /**
     * Create an AreaGraph.
     *
     * @param title the title of the chart
     * @param list the single list it will show
     */
    public PieChart(String title, List<Individual> list){
        super(title);
        this.list = list;
        this.setContentPane((createDemoPanel()));
        this.setSize(560,367);
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }
    
    
    public JPanel createDemoPanel(){
        JFreeChart chart = createChart(createDataset( )); 
        return new ChartPanel(chart);
    }
    
    private PieDataset createDataset(){
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(int i=0;i<list.size();i++) dataset.setValue( list.get(i).getItem() , list.get(i).getNumber());
        
        return dataset;
    }
    
    private JFreeChart createChart(PieDataset dataset){
        JFreeChart chart = ChartFactory.createPieChart(
            list.get(0).getCategory(), //chart tittle 
            dataset,        //data
            true,           //include legend
            true,
            false);
        jfchart = chart;
        return chart;
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
        File pieChart = new File(name+".jpeg");
        ChartUtilities.saveChartAsJPEG(pieChart, jfchart, width, height);
    }

}
