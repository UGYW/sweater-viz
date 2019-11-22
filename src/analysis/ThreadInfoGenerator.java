package analysis;

import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.util.Date;
import java.util.List;

/**
 * Created by Andrew on 2019-11-21.
 */
public class ThreadInfoGenerator {

    List<String> threadDescriptions;
    List<ThreadInfo> threadInfos;

    public void generateThreadInfos()
    {


        //Get all tasks from dataset.
        //Get the name of each thread & amount of threads from dataset.
        Analytics analytics = Analytics.getInstance();
        TaskSeriesCollection dataset = (TaskSeriesCollection)analytics.getDataset();
        for (int i = 0; i < dataset.getSeriesCount(); i++)
        {
            TaskSeries series = dataset.getSeries(i);
            List<Task> tasks = series.getTasks();
            for (int j = 0; j < tasks.size(); j++)
            {
                //Checks every task to find a list of all names.
                String desc = tasks.get(j).getDescription();
                addDescString(desc);
            }
        }
        //Get tasks and assign to a threadinfo.
        for (int k = 0; k < threadDescriptions.size(); k++)
        {
            //Gather the info for a particular thread.
            ThreadInfo info = new ThreadInfo();
            info.setThreadName(threadDescriptions.get(k));
            for (int i = 0; i < dataset.getSeriesCount(); i++)
            {
                TaskSeries series = dataset.getSeries(i);
                List<Task> tasks = series.getTasks();
                for (int j = 0; j < tasks.size(); j++)
                {
                    //Find all tasks related to the current thread.
                    String threadName = tasks.get(j).getDescription();
                    if(threadName.equals(threadDescriptions.get(k)))
                    {
                        switch (i)
                        {
                            case 0:
                                 info.addRunningTask(tasks.get(j));
                            case 1:
                                info.addWaitingTask(tasks.get(j));
                            case 2:
                                info.addLockedTask(tasks.get(j));
                            case 3:
                                info.addTimed_WaitingTask(tasks.get(j));
                        }
                    }

                }
            }
            info = calculateOverallInfo(info);
            threadInfos.add(info);
        }
    }

    public ThreadInfo calculateOverallInfo(ThreadInfo info)
    {
        //Find start time
        Date earliestTime;
        Date latestTime;
        List<Task> tasks = info.getAllTasks();
        for(int i = 0; i < tasks.size(); i++)
        {
            if (i == 0)
            {
                earliestTime = tasks.get(i).getDuration().getStart();
            }
            //Iterate through all tasks and find the earliest start time.
        }
        //Find end time
        //Get status total
        //Get time total
    }

    public void addDescString(String desc)
    {
        if(!threadDescriptions.contains(desc))
        {
            threadDescriptions.add(desc);
        }
    }
}
