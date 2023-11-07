package src;

import javafx.util.Pair;
import src.services.CsvReader;
import src.models.PriorityQueue;
import src.models.Process;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Get data from CSV
        CsvReader reader = new CsvReader();
        Pair<Integer, Process[]> data = reader.read("data.csv");
        int timeSlice = data.getKey();
        Process[] processes = data.getValue();

        roundRobin(processes, timeSlice);
    }

    public static void roundRobin(Process[] processes, int timeSlice) {
        int maxPriority = getMaxPriority(processes);
        PriorityQueue<Process> pQueue = new PriorityQueue<>(maxPriority);

        System.out.println("Round Robin Simulation");
        System.out.println("Time slice: " + timeSlice);

        int cycle = 0;
        int cyclesCurrentProcess = 0;
        Process currentProcess = null;

        while (!allProcessesFinished(processes)) {
            // Add new processes
            for (Process p : processes) {
                if (p.getAccessTime() == cycle) pQueue.addProcess(p, p.getPriority());
            }

            if (currentProcess == null) {
                currentProcess = pQueue.removeProcess();
                cyclesCurrentProcess = 0;
            }

            // Print cycle information
            System.out.println("Round: " + cycle);
            System.out.println("Queues:");
            for (int i = 0; i <= maxPriority; i++) {
                System.out.println("Queue " + i + ": " + pQueue.getQueue(i).toString());
            }

            // Simulate cycle execution
            if (currentProcess != null) {
                int remainingCycles = currentProcess.getTotalExecTime() - currentProcess.getExecTime() - 1;
                System.out.println(
                        "Process: " + currentProcess.getPid() + " (cycles remaining: " + remainingCycles + ")"
                );
            } else {
                System.out.println("No process is being executed");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            cycle++;

            // Calculate next process
            if (currentProcess != null) {
                cyclesCurrentProcess++;
                int currentProcessExecTime = currentProcess.getExecTime() + 1;
                currentProcess.setExecTime(currentProcessExecTime);
                if (currentProcessExecTime == currentProcess.getTotalExecTime()) {
                    System.out.println("Process " + currentProcess.getPid() + " finished");
                    currentProcess = null;
                } else if (cyclesCurrentProcess == timeSlice) {
                    pQueue.addProcess(currentProcess, currentProcess.getPriority());
                    currentProcess = null;
                }
            }
        }
        System.out.println("Execution finished");
    }

    private static int getMaxPriority(Process[] processes) {
        return Arrays.stream(processes)
                .mapToInt(Process::getPriority)
                .max()
                .orElse(0);
    }

    private static boolean allProcessesFinished(Process[] processes) {
        for (Process p : processes) {
            if (!p.hasFinished()) return false;
        }
        return true;
    }
}
