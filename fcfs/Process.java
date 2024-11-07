// Process.java
public class Process {
    private int at; // arrival time
    private int bt; // burst time
    private int ct; // completion time
    private int tat; // turn around time
    private int wt; // waiting time
    private int pid; // process ID

    // Getter method to get a variable value of the process
    public int getVar(String var) {
        if (var.equals("at"))
            return at;
        if (var.equals("bt"))
            return bt;
        if (var.equals("ct"))
            return ct;
        if (var.equals("tat"))
            return tat;
        if (var.equals("wt"))
            return wt;
        return pid;
    }

    // Setter method to set a variable value of the process
    public void setVar(String var, int value) {
        if (var.equals("at"))
            at = value;
        else if (var.equals("bt"))
            bt = value;
        else if (var.equals("ct"))
            ct = value;
        else if (var.equals("tat"))
            tat = value;
        else if (var.equals("wt"))
            wt = value;
        else
            pid = value;
    }

    // Update the turn around time and waiting time after completion
    public void updateAfterCt() {
        tat = ct - at;
        wt = tat - bt;
    }

    // Display the process details
    public void display() {
        System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", pid, at, bt, ct, tat, wt);
    }
}
