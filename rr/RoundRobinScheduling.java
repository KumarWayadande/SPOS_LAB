import java.util.*;

class Process {
    int pid;           // Process ID
    int burstTime;     // Burst Time
    int remainingTime; // Remaining time for the process
    int waitingTime;   // Waiting Time
    int turnaroundTime; // Turnaround Time

    public Process(int pid, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class RoundRobinScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        // List to hold processes
        List<Process> processes = new ArrayList<>();

        // Input process details (Burst Time)
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Burst Time for Process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            processes.add(new Process(i + 1, burstTime));
        }

        // Input time quantum
        System.out.print("Enter the time quantum: ");
        int timeQuantum = sc.nextInt();

        // Call the round robin scheduler
        roundRobin(processes, timeQuantum);

        // Display process details
        System.out.println("\nProcess\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        int totalWaitingTime = 0, totalTurnaroundTime = 0;
        for (Process p : processes) {
            System.out.println("P" + p.pid + "\t" + p.burstTime + "\t\t" + (p.turnaroundTime) + "\t\t" + (p.turnaroundTime) + "\t\t" + p.waitingTime);
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
        }

        // Calculate and display average waiting and turnaround times
        float avgWaitingTime = (float) totalWaitingTime / n;
        float avgTurnaroundTime = (float) totalTurnaroundTime / n;

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }

    public static void roundRobin(List<Process> processes, int timeQuantum) {
        int currentTime = 0; // Track the current time
        Queue<Process> queue = new LinkedList<>();
        int totalProcesses = processes.size();
        int completedProcesses = 0;

        // Add all processes to the queue
        queue.addAll(processes);

        while (completedProcesses < totalProcesses) {
            Process currentProcess = queue.poll();

            if (currentProcess.remainingTime > timeQuantum) {
                // Process is not completed, update the remaining time and put it back in the queue
                currentProcess.remainingTime -= timeQuantum;
                currentTime += timeQuantum;
                queue.add(currentProcess);
            } else {
                // Process finishes execution
                currentTime += currentProcess.remainingTime;
                currentProcess.remainingTime = 0;

                // Calculate turnaround time and waiting time
                currentProcess.turnaroundTime = currentTime;
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;

                completedProcesses++;
            }
        }
    }
}
