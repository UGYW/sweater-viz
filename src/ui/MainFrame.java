package ui;

import analysis.Analytics;
import analysis.ThreadInfo;
import analysis.ThreadInfoGenerator;
import org.jfree.chart.*;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.data.category.IntervalCategoryDataset;

import javax.swing.*;
import java.util.List;

public class MainFrame extends JFrame {

    private static final int MAIN_GUI_WIDTH = 1200;
    private static final int MAIN_GUI_HEIGHT = 800;

    private ChartPanel panel;
    private JFreeChart chart;
    private List<ThreadInfo> infos;

    public MainFrame(String title) {
        super(title);
        // Create dataset
        Analytics analytics_instance = Analytics.getInstance();
//        IntervalCategoryDataset dataset = analytics_instance.getMockDataset();
        IntervalCategoryDataset dataset = Analytics.getInstance().parseDataset();

        ThreadInfoGenerator tiGenerator = new ThreadInfoGenerator();
        tiGenerator.generateThreadInfos();
        infos = tiGenerator.getThreadInfos(); //UI needs to get this.

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
        chart = ChartFactory.createGanttChart(
                "Multithread Visualization", // Chart title
                "Threads", // Y-Axis Label
                "Time (INSERT UNITS HERE)", // X-Axis Label
                dataset, true, true, false);

        // create panel
        panel = new ChartPanel(chart);
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

    private void displayInfoPopUp(ChartMouseEvent e) {
        // TODO - @David: Use info from entity here as keys to dict on analytic info
        CategoryItemEntity entity = (CategoryItemEntity) e.getEntity();
        System.out.println(entity.getSeries());  // @David - series is the category (deadlocked, running, etc)
        System.out.println(entity.getCategory());// @David - category is the thread number. it's confusing.
        String series;
        switch(entity.getSeries()) {
            case 0: series = " is runnable";
                    break;
            case 1: series = " is waiting";
                    break;
            case 2: series = " is locked";
                    break;
            case 3: series = " is timed waiting";
                    break;
            default: series = " went to default";
        }
        String thread = entity.getCategory().toString();
        for (int i = 0; i < infos.size(); i++) {
            if (thread.equals(infos.get(i).getThreadName())) {
                String threadStartTime = infos.get(i).getThreadStartTime().toString();
                String threadEndTime = infos.get(i).getThreadEndTime().toString();
                String totalWaitingTime = String.valueOf(infos.get(i).getTotalWaitingTime());
                String totalTimedWaitingTime = String.valueOf(infos.get(i).getTotalTimed_WaitingTime());
                String totalRunnableTime = String.valueOf(infos.get(i).getTotalRunningTime());
                String totalLockedTime = String.valueOf(infos.get(i).getTotalLockedTime());
                String totalWaitingAmount = String.valueOf(infos.get(i).getTotalWaitingAmount());
                String totalTimeWaitingAmount = String.valueOf(infos.get(i).getTotalTimed_WaitingAmount());
                String totalLockedAmount = String.valueOf(infos.get(i).getTotalLockedAmount());
                String totalRunnableAmount = String.valueOf(infos.get(i).getTotalRunningAmount());
            }
        }
        // TODO - decide exactly what to display here
        JOptionPane.showMessageDialog(panel, entity.getCategory() + series,
                "Thread Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

}