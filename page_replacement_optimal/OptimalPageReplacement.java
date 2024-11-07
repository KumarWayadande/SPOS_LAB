import java.util.*;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        // Input reference string and number of frames
        int[] referenceString = {2, 3, 2, 1, 5, 2, 4, 5, 3, 2, 5, 2};
        int numFrames = 3;

        // Call the Optimal Page Replacement function
        optimalPageReplacement(referenceString, numFrames);
    }

    // Function to simulate Optimal page replacement
    public static void optimalPageReplacement(int[] referenceString, int numFrames) {
        // A set to keep track of pages in memory (frames)
        Set<Integer> frames = new HashSet<>();
        // A list to store the current pages in frames (for replacement simulation)
        List<Integer> frameList = new ArrayList<>();
        
        // Variable to count page faults
        int pageFaults = 0;

        // Process each page in the reference string
        for (int i = 0; i < referenceString.length; i++) {
            int currentPage = referenceString[i];

            // If the page is not already in the frame
            if (!frames.contains(currentPage)) {
                // If there's space in the frames, just add the page
                if (frames.size() < numFrames) {
                    frames.add(currentPage);
                    frameList.add(currentPage);
                } else {
                    // If frames are full, replace the page using the optimal strategy
                    // Find the page to replace
                    int farthestIndex = -1;
                    int pageToRemove = -1;

                    // Check which page will not be used for the longest time in the future
                    for (int page : frameList) {
                        int nextUse = getNextUse(referenceString, i, page);
                        if (nextUse == -1) {
                            pageToRemove = page;
                            break;
                        }
                        if (nextUse > farthestIndex) {
                            farthestIndex = nextUse;
                            pageToRemove = page;
                        }
                    }

                    // Remove the page to replace and add the new page
                    frames.remove(pageToRemove);
                    frameList.remove(Integer.valueOf(pageToRemove));  // Remove by value

                    frames.add(currentPage);
                    frameList.add(currentPage);
                }
                // Increment the page fault count
                pageFaults++;
                System.out.println("Page Fault: " + Arrays.toString(frameList.toArray()));
            } else {
                System.out.println("No Page Fault: " + Arrays.toString(frameList.toArray()));
            }
        }

        // Print the total number of page faults
        System.out.println("\nTotal Page Faults: " + pageFaults);
    }

    // Helper function to find the next use index of a page after a certain point
    public static int getNextUse(int[] referenceString, int currentIndex, int page) {
        for (int i = currentIndex + 1; i < referenceString.length; i++) {
            if (referenceString[i] == page) {
                return i;
            }
        }
        return -1;  // Return -1 if the page will not be used again
    }
}
