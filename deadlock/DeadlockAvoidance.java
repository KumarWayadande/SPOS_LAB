import java.util.Scanner;

public class DeadlockAvoidance {

    // Number of processes
    static final int P = 5;

    // Number of resources
    static final int R = 3;

    // Available resources (initially available)
    static int[] available = {3, 3, 2};

    // Maximum resources needed by each process
    static int[][] max = {
        {7, 5, 3},
        {3, 2, 2},
        {9, 0, 2},
        {2, 2, 2},
        {4, 3, 3}
    };

    // Resources allocated to each process
    static int[][] allocation = {
        {0, 1, 0},
        {2, 0, 0},
        {3, 0, 2},
        {2, 1, 1},
        {0, 0, 2}
    };

    // Remaining resources needed by each process
    static int[][] need = new int[P][R];

    public static void main(String[] args) {
        // Calculate the need matrix
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < R; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        // Request resources from user
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter process number to request resources (0 to 4): ");
        int process = sc.nextInt();
        System.out.println("Enter resource request (3 resources for each process): ");
        int[] request = new int[R];
        for (int i = 0; i < R; i++) {
            request[i] = sc.nextInt();
        }

        // Check if the request can be granted
        if (isRequestSafe(process, request)) {
            System.out.println("Request can be safely granted.");
            // Pretend we allocate the resources temporarily
            for (int i = 0; i < R; i++) {
                allocation[process][i] += request[i];
                available[i] -= request[i];
                need[process][i] -= request[i];
            }

            // Check for system safety
            if (isSystemSafe()) {
                System.out.println("The system is in a safe state.");
            } else {
                System.out.println("The system is in an unsafe state, rolling back the request.");
                // Rollback the allocation
                for (int i = 0; i < R; i++) {
                    allocation[process][i] -= request[i];
                    available[i] += request[i];
                    need[process][i] += request[i];
                }
            }
        } else {
            System.out.println("Request cannot be granted as it may lead to a deadlock.");
        }
    }

    // Check if the request is less than or equal to the needs and available resources
    public static boolean isRequestSafe(int process, int[] request) {
        for (int i = 0; i < R; i++) {
            if (request[i] > need[process][i]) {
                return false; // Request exceeds need
            }
            if (request[i] > available[i]) {
                return false; // Request exceeds available resources
            }
        }
        return true;
    }

    // Check if the system is in a safe state
    public static boolean isSystemSafe() {
        int[] work = available.clone();
        boolean[] finish = new boolean[P];
        int count = 0;

        while (count < P) {
            boolean found = false;
            for (int i = 0; i < P; i++) {
                if (!finish[i] && canProcessProceed(i, work)) {
                    // Pretend to allocate resources to process i
                    for (int j = 0; j < R; j++) {
                        work[j] += allocation[i][j];
                    }
                    finish[i] = true;
                    found = true;
                    count++;
                }
            }

            // If no process can proceed, break
            if (!found) {
                return false; // System is not in a safe state
            }
        }
        return true; // System is in a safe state
    }

    // Check if a process can proceed with the available resources
    public static boolean canProcessProceed(int process, int[] work) {
        for (int i = 0; i < R; i++) {
            if (need[process][i] > work[i]) {
                return false; // Process cannot proceed as it needs more resources than available
            }
        }
        return true; // Process can proceed
    }
}
