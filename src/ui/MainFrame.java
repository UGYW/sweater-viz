package ui;

import analysis.Analytics;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
        JFreeChart chart = ChartFactory.createGanttChart(
                "Multithread Visualization", // Chart title
                "Threads", // Y-Axis Label
                "Time (INSERT UNITS HERE)", // X-Axis Label
                dataset, true, false, false);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

        // General Configurations
        setSize(MAIN_GUI_WIDTH, MAIN_GUI_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}