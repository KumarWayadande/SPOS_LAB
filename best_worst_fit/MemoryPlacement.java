import java.util.*;

class MemoryBlock {
    int size;
    boolean isOccupied;

    public MemoryBlock(int size) {
        this.size = size;
        this.isOccupied = false;
    }
}

class Process {
    int pid;    // Process ID
    int size;   // Process size

    public Process(int pid, int size) {
        this.pid = pid;
        this.size = size;
    }
}

public class MemoryPlacement {
    
    // Best Fit Allocation
    static void bestFit(List<MemoryBlock> memory, List<Process> processes) {
        System.out.println("Using Best Fit Allocation:");
        
        for (Process p : processes) {
            int bestIndex = -1;
            int minWaste = Integer.MAX_VALUE;

            for (int i = 0; i < memory.size(); i++) {
                if (!memory.get(i).isOccupied && memory.get(i).size >= p.size) {
                    int waste = memory.get(i).size - p.size;
                    if (waste < minWaste) {
                        minWaste = waste;
                        bestIndex = i;
                    }
                }
            }

            if (bestIndex != -1) {
                memory.get(bestIndex).isOccupied = true;
                System.out.println("Process P" + p.pid + " allocated to memory block of size " + memory.get(bestIndex).size);
            } else {
                System.out.println("Process P" + p.pid + " cannot be allocated.");
            }
        }
    }

    // Worst Fit Allocation
    static void worstFit(List<MemoryBlock> memory, List<Process> processes) {
        System.out.println("\nUsing Worst Fit Allocation:");

        for (Process p : processes) {
            int worstIndex = -1;
            int maxWaste = Integer.MIN_VALUE;

            for (int i = 0; i < memory.size(); i++) {
                if (!memory.get(i).isOccupied && memory.get(i).size >= p.size) {
                    int waste = memory.get(i).size - p.size;
                    if (waste > maxWaste) {
                        maxWaste = waste;
                        worstIndex = i;
                    }
                }
            }

            if (worstIndex != -1) {
                memory.get(worstIndex).isOccupied = true;
                System.out.println("Process P" + p.pid + " allocated to memory block of size " + memory.get(worstIndex).size);
            } else {
                System.out.println("Process P" + p.pid + " cannot be allocated.");
            }
        }
    }

    public static void main(String[] args) {
        // Define some memory blocks (size)
        List<MemoryBlock> memory = new ArrayList<>();
        memory.add(new MemoryBlock(100));
        memory.add(new MemoryBlock(500));
        memory.add(new MemoryBlock(200));
        memory.add(new MemoryBlock(300));

        // Define some processes (size)
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 120));
        processes.add(new Process(2, 450));
        processes.add(new Process(3, 220));
        processes.add(new Process(4, 300));
        processes.add(new Process(5, 150));

        // Run Best Fit algorithm
        bestFit(memory, processes);

        // Reset memory occupancy before running Worst Fit
        for (MemoryBlock block : memory) {
            block.isOccupied = false;
        }

        // Run Worst Fit algorithm
        worstFit(memory, processes);
    }
}
