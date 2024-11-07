// Main.java
import java.util.*;

public class Main {

    // Calculate the average of a variable value for all processes
    public static float average(ArrayList<Process> P, String var) {
        int total = 0;
        for (Process temp : P) {
            total += temp.getVar(var);
        }
        return (float) total / P.size();
    }

    public static void main(String[] args) {
        /*
        Input description.
        First line contains an integer n
        the next n lines contains 2 space separated integers
        containing values for arrival time and burst time for
        example:
        2
        0 3
        1 2
        */
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter No of processes:");
        int n = sc.nextInt();
        int counter = 0;
        ArrayList<Process> P = new ArrayList<>(n);
        // Create a process object for each input and add to the process list
        for (int i = 0; i < n; i++) {
            Process temp = new Process();
            temp.setVar("pid", counter++);
            System.out.println("Enter Arrival Time:");
            temp.setVar("at", sc.nextInt());
            System.out.println("Enter Burst Time:");
            temp.setVar("bt", sc.nextInt());
            P.add(temp);
        }

        // Sort the process list by arrival time
        Collections.sort(P, new Comparator<Process>() {
            public int compare(Process first, Process second) {
                return first.getVar("at") - second.getVar("at");
            }
        });

        System.out.println("pid\tat\tbt\tct\ttat\twt");

        // Calculate completion time and display the details of the first process
        P.get(0).setVar("ct", P.get(0).getVar("at") + P.get(0).getVar("bt"));
        P.get(0).updateAfterCt();
        P.get(0).display();

        // Calculate completion time and display the details of the remaining processes
        for (int i = 1; i < P.size(); i++) {
            if (P.get(i).getVar("at") < P.get(i - 1).getVar("ct")) {
                P.get(i).setVar("ct", P.get(i - 1).getVar("ct") + P.get(i).getVar("bt"));
            } else {
                System.out.printf("curr['at'] : %d, prev['ct'] : %d\n\n", P.get(i).getVar("at"),
                        P.get(i - 1).getVar("ct"));
                P.get(i).setVar("ct", P.get(i).getVar("at") + P.get(i).getVar("bt"));
            }
            P.get(i).updateAfterCt(); // Update the turnaround time and waiting time for the current process
            P.get(i).display(); // Display the details of the current process
        }

        System.out.printf("Average waiting time : %f\n", average(P, "wt"));
        sc.close(); // Close the scanner
    }
}
