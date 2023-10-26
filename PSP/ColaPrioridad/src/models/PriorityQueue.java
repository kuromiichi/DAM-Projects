package src.models;

public class PriorityQueue<T> {
    private final Queue<T>[] queues;
    private final int maxPriorities;

    public PriorityQueue(int maxPriorities) {
        this.maxPriorities = maxPriorities;
        queues = new Queue[maxPriorities + 1];
        for (int i = 0; i <= maxPriorities; i++) {
            queues[i] = new Queue<>();
        }
    }

    public boolean addProcess(T process, int priority) {
        if (process == null) return false;
        if (priority < 0 || priority > maxPriorities) return false;
        return queues[priority].add(process);
    }

    public T removeProcess() {
        for (Queue<T> queue : queues) {
            if (!queue.isEmpty()) {
                return queue.remove();
            }
        }
        return null;
    }

    public Queue<T> getQueue(int priority) {
        return queues[priority];
    }
}
