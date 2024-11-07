import java.util.*;

class Process {
    int pid; // Process ID
    int arrivalTime; // Arrival time
    int burstTime; // Burst time (remaining burst time will be modified later)
    int priority; // Priority of the process
    int waitingTime; // Waiting time
    int turnaroundTime; // Turnaround time
    int completionTime; // Completion time
    int remainingTime; // Remaining burst time (for preemption)

    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime; // Initialize remaining time to burst time
    }
}

class PreemptivePriorityScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get the number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        // List of processes
        ArrayList<Process> processes = new ArrayList<>();

        // Get the process details
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for process " + (i + 1) + ":");
            System.out.print("Arrival time: ");
            int arrivalTime = sc.nextInt();
            System.out.print("Burst time: ");
            int burstTime = sc.nextInt();
            System.out.print("Priority: ");
            int priority = sc.nextInt();

            processes.add(new Process(i + 1, arrivalTime, burstTime, priority));
        }

        // Perform Preemptive Priority Scheduling
        preemptivePriorityScheduling(processes);

        // Print the result
        System.out.println("\nProcess\tArrival\tBurst\tPriority\tCompletion\tTurnaround\tWaiting");
        for (Process p : processes) {
            System.out.println("P" + p.pid + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" + p.priority + "\t\t" +
                    p.completionTime + "\t\t" + p.turnaroundTime + "\t\t" + p.waitingTime);
        }

        // Calculate and display average waiting and turnaround times
        float avgWaitingTime = 0, avgTurnaroundTime = 0;
        for (Process p : processes) {
            avgWaitingTime += p.waitingTime;
            avgTurnaroundTime += p.turnaroundTime;
        }
        System.out.println("\nAverage Waiting Time: " + (avgWaitingTime / n));
        System.out.println("Average Turnaround Time: " + (avgTurnaroundTime / n));
    }

    public static void preemptivePriorityScheduling(ArrayList<Process> processes) {
        int time = 0; // Current time
        int completed = 0; // Number of completed processes
        int n = processes.size(); // Total number of processes

        while (completed < n) {
            // Find the process with the highest priority that has arrived and is not
            // completed
            Process currentProcess = null;
            int highestPriority = Integer.MAX_VALUE;

            for (Process p : processes) {
                if (p.arrivalTime <= time && p.remainingTime > 0 && p.priority < highestPriority) {
                    highestPriority = p.priority;
                    currentProcess = p;
                }
            }

            if (currentProcess == null) {
                // If no process is available to run, increment the time
                time++;
            } else {
                // Process the selected process for one unit of time
                currentProcess.remainingTime--;
                time++;

                // If the process has completed, calculate its metrics
                if (currentProcess.remainingTime == 0) {
                    currentProcess.completionTime = time;
                    currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
                    currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
                    completed++;
                }
            }
        }
    }
}
