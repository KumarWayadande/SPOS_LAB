import java.util.*;

public class LRUPageReplacement {

    public static void main(String[] args) {
        // Input reference string and number of frames
        int[] referenceString = {2, 3, 2, 1, 5, 2, 4, 5, 3, 2, 5, 2};
        int numFrames = 3;

        // Call the LRU page replacement function
        lruPageReplacement(referenceString, numFrames);
    }

    // Function to simulate LRU page replacement
    public static void lruPageReplacement(int[] referenceString, int numFrames) {
        // A queue to store pages in memory
        LinkedList<Integer> frames = new LinkedList<>();
        // A set to quickly check if a page is in memory
        Set<Integer> memory = new HashSet<>();
        
        // Variable to count page faults
        int pageFaults = 0;

        // Process each page in the reference string
        for (int i = 0; i < referenceString.length; i++) {
            int currentPage = referenceString[i];

            // If the page is not already in the frame
            if (!memory.contains(currentPage)) {
                // If frames are full, remove the least recently used page
                if (frames.size() == numFrames) {
                    int removedPage = frames.removeFirst();  // Remove LRU
                    memory.remove(removedPage);  // Remove from set as well
                }
                
                // Add the new page to frames and memory
                frames.addLast(currentPage);
                memory.add(currentPage);

                // Increment the page fault count
                pageFaults++;
                System.out.println("Page Fault: " + Arrays.toString(frames.toArray()));
            } else {
                // If the page is already in the frame, move it to the most recently used
                frames.remove(Integer.valueOf(currentPage));
                frames.addLast(currentPage);
                System.out.println("No Page Fault: " + Arrays.toString(frames.toArray()));
            }
        }

        // Print the total number of page faults
        System.out.println("\nTotal Page Faults: " + pageFaults);
    }
}
