import java.util.concurrent.Semaphore;

class ReaderWriterProblem {
    // Semaphore for controlling the access of readers and writers
    static Semaphore mutex = new Semaphore(1);      // Mutex to protect the count of active readers
    static Semaphore writeMutex = new Semaphore(1); // Semaphore for writers to have exclusive access

    static int readCount = 0;  // Count of active readers

    // Reader class
    static class Reader extends Thread {
        public void run() {
            try {
                // Reader acquires the mutex to update readCount
                mutex.acquire();
                readCount++;
                // If it's the first reader, acquire the writeMutex to block writers
                if (readCount == 1) {
                    writeMutex.acquire();
                }
                mutex.release();

                // Simulate reading
                System.out.println("Reader " + Thread.currentThread().getId() + " is reading...");
                Thread.sleep(1000);

                // Reader leaves, decrement the count of readers
                mutex.acquire();
                readCount--;
                // If it's the last reader, release the writeMutex to allow writers
                if (readCount == 0) {
                    writeMutex.release();
                }
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Writer class
    static class Writer extends Thread {
        public void run() {
            try {
                // Writer waits for exclusive access
                writeMutex.acquire();

                // Simulate writing
                System.out.println("Writer " + Thread.currentThread().getId() + " is writing...");
                Thread.sleep(2000);

                // Release write access after writing
                writeMutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method to test the Reader-Writer Problem
    public static void main(String[] args) {
        // Creating 3 readers and 2 writers
        Thread reader1 = new Reader();
        Thread reader2 = new Reader();
        Thread reader3 = new Reader();
        Thread writer1 = new Writer();
        Thread writer2 = new Writer();

        // Starting the threads
        reader1.start();
        reader2.start();
        reader3.start();
        writer1.start();
        writer2.start();
    }
}
