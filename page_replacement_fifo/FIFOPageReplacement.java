import java.util.*;

public class FIFOPageReplacement {
    public static void main(String[] args) {
        // Input reference string and number of frames
        int[] referenceString = {2, 3, 2, 1, 5, 2, 4, 5, 3, 2, 5, 2};
        int numFrames = 3;

        // Call the FIFO Page Replacement function
        fifoPageReplacement(referenceString, numFrames);
    }

    // Function to simulate FIFO page replacement
    public static void fifoPageReplacement(int[] referenceString, int numFrames) {
        // Create a Queue to simulate the frames (FIFO)
        Queue<Integer> frames = new LinkedList<>();
        // Set to store current pages in frames for faster lookup
        Set<Integer> pageSet = new HashSet<>();
        
        // Variable to count page faults
        int pageFaults = 0;

        // Process each page reference in the reference string
        for (int i = 0; i < referenceString.length; i++) {
            int currentPage = referenceString[i];
            // Check if the page is already in one of the frames
            if (!pageSet.contains(currentPage)) {
                // If the frame is full, remove the page from the front of the queue (FIFO)
                if (frames.size() == numFrames) {
                    int removedPage = frames.poll();  // Remove page from the front of the queue
                    pageSet.remove(removedPage);      // Remove from the set as well
                }

                // Add the new page to the frames and set
                frames.offer(currentPage);
                pageSet.add(currentPage);
                pageFaults++; // Increment page faults
                System.out.println("Page Fault: " + Arrays.toString(frames.toArray()));
            } else {
                // If the page is already in the frame, no page fault
                System.out.println("No Page Fault: " + Arrays.toString(frames.toArray()));
            }
        }

        // Print total number of page faults
        System.out.println("\nTotal Page Faults: " + pageFaults);
    }
}
