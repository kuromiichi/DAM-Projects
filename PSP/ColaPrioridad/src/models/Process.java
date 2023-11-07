package src.models;

public class Process {
    private int pid;
    private int totalExecTime;
    private int accessTime;
    private int priority;
    private int execTime = 0;

    public Process(int pid, int totalExecTime, int accessTime, int priority) {
        this.pid = pid;
        this.totalExecTime = totalExecTime;
        this.accessTime = accessTime;
        this.priority = priority;
    }

    public int[] getProcessData() {
        return new int[]{pid, totalExecTime, accessTime, priority};
    }

    public int getPid() {
        return pid;
    }

    public int getTotalExecTime() {
        return totalExecTime;
    }

    public int getAccessTime() {
        return accessTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getExecTime() {
        return execTime;
    }

    public void setExecTime(int execTime) {
        this.execTime = execTime;
    }

    public boolean hasFinished() {
        return execTime == totalExecTime;
    }

    public String toString() {
        return Integer.toString(pid);
    }
}
