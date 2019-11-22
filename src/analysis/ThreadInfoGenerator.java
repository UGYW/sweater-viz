package analysis;

import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrew on 2019-11-21.
 */
public class ThreadInfoGenerator {

    //TODO: Refactor this class.

    List<String> threadDescriptions = new ArrayList<String>();
    List<ThreadInfo> threadInfos = new ArrayList<ThreadInfo>();

    //Call generateTreadInfos() first before calling this.
    public List<ThreadInfo> getThreadInfos()
    {
        return threadInfos;
    }

    public void generateThreadInfos()
    {


        //Get all tasks from dataset.
        //Get the name of each thread & amount of threads from dataset.
        Analytics analytics = Analytics.getInstance();
        TaskSeriesCollection dataset = (TaskSeriesCollection)analytics.dataset;
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
        if (threadDescriptions == null)
        {
            return;
        }
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
                                 break;
                            case 1:
                                info.addWaitingTask(tasks.get(j));
                                break;
                            case 2:
                                info.addLockedTask(tasks.get(j));
                                break;
                            case 3:
                                info.addTimed_WaitingTask(tasks.get(j));
                                break;
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
        Date earliestTime = Date.from(LocalDate.of(1,1,1).atStartOfDay().toInstant(ZoneOffset.UTC));
        Date latestTime = Date.from(LocalDate.of(1,1,1).atStartOfDay().toInstant(ZoneOffset.UTC));
        List<Task> tasks = info.getAllTasks();
        //Iterate through all tasks and find the earliest start time and latest end time.
        for (int i = 0; i < tasks.size(); i++)
        {
            Date startTime = tasks.get(i).getDuration().getStart();
            Date endTime = tasks.get(i).getDuration().getEnd();
            if (i == 0)
            {
                //Initialize the earliest/latest time.
                earliestTime = tasks.get(i).getDuration().getStart();
                latestTime = tasks.get(i).getDuration().getEnd();
            }
            else
            {
                //Update the earliest/latest time.
                if (startTime.before(earliestTime))
                {
                    earliestTime = startTime;
                }
                if (endTime.after(latestTime))
                {
                    latestTime = endTime;
                }
            }
        }
        //Get time total
        long runningTime = calculateTotalTaskTime(info.getRunningTasks());
        long waitingTime = calculateTotalTaskTime(info.getWaitingTasks());
        long lockedTime = calculateTotalTaskTime(info.getLockedTasks());
        long timedWaitTime = calculateTotalTaskTime(info.getTimed_WaitingTasks());

        //Get status total
        int runningAmount = info.getRunningTasks().size();
        int waitingAmount = info.getWaitingTasks().size();
        int lockedAmount = info.getLockedTasks().size();
        int timedWaitAmount = info.getTimed_WaitingTasks().size();

        //Assign values to ThreadInfo.
        info.setThreadStartTime(earliestTime);
        info.setThreadEndTime(latestTime);

        info.setTotalRunningTime(runningTime);
        info.setTotalWaitingTime(waitingTime);
        info.setTotalLockedTime(lockedTime);
        info.setTotalTimed_WaitingTime(timedWaitTime);

        info.setTotalRunningAmount(runningAmount);
        info.setTotalWaitingAmount(waitingAmount);
        info.setTotalLockedAmount(lockedAmount);
        info.setTotalTimed_WaitingAmount(timedWaitAmount);

        return info;
    }

    public long calculateTotalTaskTime(List<Task> tasks)
    {
        long totalTimeMilliseconds = 0;

        for (int i = 0; i < tasks.size(); i++)
        {
            Date startTime = tasks.get(i).getDuration().getStart();
            Date endTime = tasks.get(i).getDuration().getEnd();
            long taskDuration = endTime.getTime() - startTime.getTime();
            totalTimeMilliseconds += taskDuration;
        }
        return totalTimeMilliseconds;
    }

    public void addDescString(String desc)
    {
        if(!threadDescriptions.contains(desc))
        {
            threadDescriptions.add(desc);
        }
    }
}
