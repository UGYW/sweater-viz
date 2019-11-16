package analysis;

import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

public class Analytics {
    private static Analytics analytics_instance = null;

    // TODO: decide exactly what attributes to include

    private Analytics() {
        // TODO
    }

    public static Analytics getInstance()
    {
        if (analytics_instance == null)
            analytics_instance = new Analytics();

        return analytics_instance;
    }

    public IntervalCategoryDataset getDataset() {
        // TODO
        return new TaskSeriesCollection();
    }

    public void setStaticAttrbPlaceholder() {
        // TODO: Change the signature when more complete
    }

    public void setDynamicAttrbPlaceholder() {
        // TODO: Change the signature when more complete
    }

    public IntervalCategoryDataset getMockDataset() {

        TaskSeries series1 = new TaskSeries("Deadlocked");

        series1.add(new Task("Thread 1",
                Date.from(LocalDate.of(2017,7,3).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7,7).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series1.add(new Task("Thread 2",
                Date.from(LocalDate.of(2017, 7,10).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 14).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series1.add(new Task("Thread 3",
                Date.from(LocalDate.of(2017, 7,17).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 21).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));


        TaskSeries series2 = new TaskSeries("Locked");

        series2.add(new Task("Thread 1",
                Date.from(LocalDate.of(2017, 7,3).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7,5).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series2.add(new Task("Thread 2",
                Date.from(LocalDate.of(2017, 7, 6).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 17).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series2.add(new Task("Thread 3",
                Date.from(LocalDate.of(2017, 7, 18).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 27).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        TaskSeries series3 = new TaskSeries("Running");

        series3.add(new Task("Thread 1",
                Date.from(LocalDate.of(2017, 7,2).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 4).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series3.add(new Task("Thread 2",
                Date.from(LocalDate.of(2017, 7, 9).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 12).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series3.add(new Task("Thread 3",
                Date.from(LocalDate.of(2017, 7, 16).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 17).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        TaskSeriesCollection dataset = new TaskSeriesCollection();
        dataset.add(series1);
        dataset.add(series2);
        dataset.add(series3);
        return dataset;
    }

}
