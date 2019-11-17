package ui;

import analysis.Analytics;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.data.category.IntervalCategoryDataset;

public class MainFrame extends JFrame {

    private static final int MAIN_GUI_WIDTH = 1200;
    private static final int MAIN_GUI_HEIGHT = 800;

    public MainFrame(String title) {
        super(title);
        // Create dataset
        Analytics analytics_instance = Analytics.getInstance();
        IntervalCategoryDataset dataset = analytics_instance.getMockDataset();
//        IntervalCategoryDataset dataset = Analytics.getDataset();

        // Create chart
        createChart(dataset);

        // General Configurations
        generalConfigs();
    }

    private void generalConfigs() {
        setSize(MAIN_GUI_WIDTH, MAIN_GUI_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createChart(IntervalCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createGanttChart(
                "Multithread Visualization", // Chart title
                "Threads", // Y-Axis Label
                "Time (INSERT UNITS HERE)", // X-Axis Label
                dataset, true, true, false);

        // create panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

        // add listener for popup
        // https://stackoverflow.com/questions/8218853/how-to-listen-for-clicks-in-java-jfreechart-using-events
        panel.addChartMouseListener(new ChartMouseListener() {
            public void chartMouseClicked(ChartMouseEvent e) {
                displayInfoPopUp(e);
            }
            public void chartMouseMoved(ChartMouseEvent e) {}
        });
    }

    public void displayInfoPopUp(ChartMouseEvent e) {
        // TODO
        System.out.println(e.getEntity());
    }

}