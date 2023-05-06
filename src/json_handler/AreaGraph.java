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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Shows an area graph in a new JFrame
 * Can be used with one or two categories
 *
 */
public class AreaGraph extends ApplicationFrame {

    private static final long serialVersionUID = 1L;
    private List<Individual> list;
    private List<IndividualDouble> listDouble;
    private String chartTitle, category1, category2;
    private JFreeChart aGraph;
    boolean singlecat;
    private JFreeChart areaGraph;

    /**
     * Create an AreaGraph.
     *
     * @param title the title of the graph
     * @param list the list it will show, in this case it is a single list
     * @param singlecat determines that this is a single category graph, the
     * value of this doesn't really matter, only its existence
     */
    public AreaGraph(String title, List<Individual> list, boolean singlecat) {
        super(title);
        this.list = list;
        category1 = list.get(0).getCategory();
        chartTitle = category1;
        step2(createDataset());
    }

    /**
     * Create an AreaGraph.
     *
     * @param title the title of the graph
     * @param list the list it will show, in this case it is a double list
     */
    public AreaGraph(String title, List<IndividualDouble> list) {
        super(title);
        listDouble = list;
        category1 = listDouble.get(0).getCategory1();
        category2 = listDouble.get(0).getCategory2();
        chartTitle = category1 + " and " + category2;
        step2(createDatasetDouble());
    }

    // this is where both single and double paths collide, the algorithm is the same after this point
    private void step2(DefaultCategoryDataset dataset) {

        areaGraph = ChartFactory.createAreaChart(
                chartTitle,
                "Category",
                "Frequency",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, true
        );
        ChartPanel chartPanel = new ChartPanel(areaGraph);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 376));
        setContentPane(chartPanel);
        this.setSize(560, 367);
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);

    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < list.size(); i++) {
            dataset.addValue(list.get(i).getNumber(), list.get(i).getItem(), category1);
        }

        return dataset;
    }

    private DefaultCategoryDataset createDatasetDouble() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < listDouble.size(); i++) {
            dataset.addValue(listDouble.get(i).getNumber(), listDouble.get(i).getItem1(), listDouble.get(i).getItem2());
        }

        return dataset;
    }

    @Override
    public void windowClosing(WindowEvent e) {
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
    public void saveAsJPEG(int width, int height, String name) throws IOException {
        File barChartFile = new File(name + ".jpeg");
        ChartUtilities.saveChartAsJPEG(barChartFile, aGraph, width, height);
    }

}
