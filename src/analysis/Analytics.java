package analysis;

import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.io.*;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Scanner;

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
        String startTime = "";
        String setStartTime = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        TaskSeries seriesLocked = new TaskSeries("Locked");
        TaskSeries seriesWaiting = new TaskSeries("Waiting");
        TaskSeries seriesRunnable = new TaskSeries("Runnable");
        TaskSeries seriesTimedWaiting = new TaskSeries("TimedWaiting");
        String [] times = new String[1000];

        try {
            BufferedReader br = new BufferedReader(new FileReader("analysisData.txt"));
            // read the first line from the text file
            String fileRead = br.readLine();
            int i = 0;
            while (fileRead != null) {
                if (fileRead.contains("threadSet")) {
                    String[] st = fileRead.split("time: ");
                    times[i] = st[1];
                    i++;
                    fileRead = br.readLine();
                }
                else  {
                    fileRead = br.readLine();
                    }
                }
            }
        // handle exceptions
        catch (FileNotFoundException fnfe)
        {
            System.out.println("file not found");
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("analysisData.txt"));
            // read the first line from the text file
            String fileRead = br.readLine();

            if (fileRead.contains("start time")) {
                String [] st = fileRead.split(":");
                startTime = st[1];
                fileRead = br.readLine();
            }
            int j = -1;
            // loop until all lines are read
            while (fileRead != null) {
                if (fileRead.contains("threadSet")) {
                    j++;
                    if (times[j+1] == null) {
                        times[j+1] = times[j];
                    }
                    fileRead = br.readLine();
                }
                if (fileRead.contains("thread info")) {
                    String[] threadInfo = fileRead.split(" ");
                    String name = "Thread " + threadInfo[3];
                    int size = threadInfo.length;
                    String status = threadInfo[size-1];
                    switch(status) {
                        case "RUNNABLE":
                            Long sTimeRun = Long.parseLong(times[j]);
                            Long fTimeRun = Long.parseLong(times[j+1]);
                            seriesRunnable.add(new Task(name, new Date(sTimeRun), new Date(fTimeRun)));
                            break;
                        case "WAITING":
                            Long sTimeWait = Long.parseLong(times[j]);
                            Long fTimeWait = Long.parseLong(times[j+1]);
                            seriesWaiting.add(new Task(name, new Date(sTimeWait), new Date(fTimeWait)));
                            break;
                        case "TIMED_WAITING":
                            Long sTime = Long.parseLong(times[j]);
                            Long fTime = Long.parseLong(times[j+1]);
                            seriesTimedWaiting.add(new Task(name, new Date(sTime), new Date(fTime)));
                            break;
                        case "LOCKED":
                            Long lsTime = Long.parseLong(times[j]);
                            Long lfTime = Long.parseLong(times[j+1]);
                            seriesLocked.add(new Task(name, new Date(lsTime), new Date(lfTime)));
                            break;
                        default: break;
                    }
                    fileRead = br.readLine();
                }
                else {
                    fileRead = br.readLine();
                }
            }
        }
        // handle exceptions
        catch (FileNotFoundException fnfe)
        {
            System.out.println("file not found");
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        /*
        if (seriesRunnable.getItemCount() != 0) {
            dataset.add(seriesRunnable);
        }
        if (seriesWaiting.getItemCount() != 0) {
            dataset.add(seriesWaiting);
        }
        if (seriesLocked.getItemCount() != 0) {
            dataset.add(seriesLocked);
        }
        if (seriesTimedWaiting.getItemCount() != 0) {
            dataset.add(seriesTimedWaiting);
        }*/
        dataset.add(seriesRunnable);
        dataset.add(seriesWaiting);
        dataset.add(seriesLocked);
        dataset.add(seriesTimedWaiting);
        return dataset;
    }

    public void setStaticAttrbPlaceholder() {
        // TODO: Change the signature when more complete
    }

    public void setDynamicAttrbPlaceholder() {
        // TODO: Change the signature when more complete
    }

    public IntervalCategoryDataset getMockDataset() {

        TaskSeries series0 = new TaskSeries("Deadlocked");


        Task task0 = new Task("Thread 0",
                Date.from(LocalDate.of(2017,7,3).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7,7).atStartOfDay().toInstant(ZoneOffset.UTC))
        );
        series0.add(task0);
//        series0.add(new Task("Thread 0",
//                Date.from(LocalDate.of(2017,7,3).atStartOfDay().toInstant(ZoneOffset.UTC)),
//                Date.from(LocalDate.of(2017, 7,7).atStartOfDay().toInstant(ZoneOffset.UTC))
//        ));

        series0.add(new Task("Thread 1",
                Date.from(LocalDate.of(2017, 7,10).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 14).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series0.add(new Task("Thread 2",
                Date.from(LocalDate.of(2017, 7,17).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 21).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));


        TaskSeries series1 = new TaskSeries("Locked");
        series1.add(new Task("Thread 0",
                Date.from(LocalDate.of(2017, 7,3).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7,5).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series1.add(new Task("Thread 1",
                Date.from(LocalDate.of(2017, 7, 6).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 17).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series1.add(new Task("Thread 2",
                Date.from(LocalDate.of(2017, 7, 18).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 27).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        TaskSeries series2 = new TaskSeries("Running");

        series2.add(new Task("Thread 0",
                Date.from(LocalDate.of(2017, 7,2).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 4).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series2.add(new Task("Thread 1",
                Date.from(LocalDate.of(2017, 7, 9).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 12).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        series2.add(new Task("Thread 2",
                Date.from(LocalDate.of(2017, 7, 16).atStartOfDay().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDate.of(2017, 7, 17).atStartOfDay().toInstant(ZoneOffset.UTC))
        ));

        TaskSeriesCollection dataset = new TaskSeriesCollection();
        dataset.add(series0);
        dataset.add(series1);
        dataset.add(series2);
        return dataset;
    }

}
