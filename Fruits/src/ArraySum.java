import java.util.ArrayList;
import java.util.List;

public class ArraySum {
    public static int computeSum(int[] array, int numThreads) throws InterruptedException {
        int length = array.length;
        int chunkSize = length / numThreads;
        List<ThreadSum> threads = new ArrayList<>();

        // Create and start threads
        for (int i = 0; i < numThreads; i++) {
            int startIdx = i * chunkSize;
            int endIdx = (i == numThreads - 1) ? length : startIdx + chunkSize;
            ThreadSum thread = new ThreadSum(array, startIdx, endIdx);
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to complete and compute the final sum
        int totalSum = 0;
        for (ThreadSum thread : threads) {
            thread.join(); // Wait for the thread to finish
            totalSum += thread.getPartialSum();
        }

        return totalSum;
    }

    }