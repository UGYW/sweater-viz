package analysis;

import org.jfree.data.gantt.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrew on 2019-11-21.
 */

public class ThreadInfo {

    private String threadName;
    private Date threadStartTime;
    private Date threadEndTime;
    private List<Task> runningTasks;
    private List<Task> waitingTasks;
    private List<Task> lockedTasks;
    private List<Task> timed_waitingTasks;
    private long totalRunningTime;
    private long totalWaitingTime;
    private long totalLockedTime;
    private long totalTimed_WaitingTime;
    private int totalRunningAmount;
    private int totalWaitingAmount;
    private int totalLockedAmount;
    private int TotalTimed_WaitingAmount;

    public ThreadInfo()
    {
        runningTasks = new ArrayList<Task>();
        waitingTasks = new ArrayList<Task>();
        lockedTasks = new ArrayList<Task>();
        timed_waitingTasks = new ArrayList<Task>();
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public List<Task> getAllTasks()
    {
        List<Task> allTasks = new ArrayList<Task>();
        allTasks.addAll(runningTasks);
        allTasks.addAll(waitingTasks);
        allTasks.addAll(lockedTasks);
        allTasks.addAll(timed_waitingTasks);
        return allTasks;
    }

    public List<Task> getRunningTasks() {
        return runningTasks;
    }

    public void addRunningTask(Task task) {
        this.runningTasks.add(task);
    }

    public List<Task> getWaitingTasks() {
        return waitingTasks;
    }

    public void addWaitingTask(Task task) {
        this.waitingTasks.add(task);
    }

    public List<Task> getLockedTasks() {
        return lockedTasks;
    }

    public void addLockedTask(Task task) {
        this.lockedTasks.add(task);
    }

    public List<Task> getTimed_WaitingTasks() {
        return timed_waitingTasks;
    }

    public void addTimed_WaitingTask(Task task) {
        this.timed_waitingTasks.add(task);
    }

    public Date getThreadStartTime() {
        return threadStartTime;
    }

    public void setThreadStartTime(Date threadStartTime) {
        this.threadStartTime = threadStartTime;
    }

    public Date getThreadEndTime() {
        return threadEndTime;
    }

    public void setThreadEndTime(Date threadEndTime) {
        this.threadEndTime = threadEndTime;
    }

    public long getTotalRunningTime() {
        return totalRunningTime;
    }

    public void setTotalRunningTime(long totalRunningTime) {
        this.totalRunningTime = totalRunningTime;
    }

    public long getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public void setTotalWaitingTime(long totalWaitingTime) {
        this.totalWaitingTime = totalWaitingTime;
    }

    public long getTotalLockedTime() {
        return totalLockedTime;
    }

    public void setTotalLockedTime(long totalLockedTime) {
        this.totalLockedTime = totalLockedTime;
    }

    public long getTotalTimed_WaitingTime() {
        return totalTimed_WaitingTime;
    }

    public void setTotalTimed_WaitingTime(long totalTimed_WaitingTime) {
        this.totalTimed_WaitingTime = totalTimed_WaitingTime;
    }

    public int getTotalRunningAmount() {
        return totalRunningAmount;
    }

    public void setTotalRunningAmount(int totalRunningAmount) {
        this.totalRunningAmount = totalRunningAmount;
    }

    public int getTotalWaitingAmount() {
        return totalWaitingAmount;
    }

    public void setTotalWaitingAmount(int totalWaitingAmount) {
        this.totalWaitingAmount = totalWaitingAmount;
    }

    public int getTotalLockedAmount() {
        return totalLockedAmount;
    }

    public void setTotalLockedAmount(int totalLockedAmount) {
        this.totalLockedAmount = totalLockedAmount;
    }

    public int getTotalTimed_WaitingAmount() {
        return TotalTimed_WaitingAmount;
    }

    public void setTotalTimed_WaitingAmount(int totalTimed_WaitingAmount) {
        TotalTimed_WaitingAmount = totalTimed_WaitingAmount;
    }
}
