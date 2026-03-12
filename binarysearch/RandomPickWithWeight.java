import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * RandomPickWithWeight
 * 
 * Problem:
 * Given an array w of positive integers, pick an index randomly with probability proportional to its weight.
 * For example, if w = [1,3,2], the probability of picking index 0 is 1/6, index 1 is 3/6, and index 2 is 2/6.
 * 
 * Approach:
 * 1. Build a prefix sum array of weights.
 *    Example: w = [1,3,2] → prefixSum = [1,4,6]
 * 2. Generate a random integer target in range [1, totalWeight]
 * 3. Use binary search to find the first index where prefixSum[i] >= target.
 *    This guarantees the probability of each index is proportional to its weight.
 */
public class RandomPickWithWeight {
    private int[] prefixSum; // stores cumulative weights
    private int totalSum;    // total sum of all weights

    /**
     * Constructor: build the prefix sum array
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public RandomPickWithWeight(int[] w) {
        int n = w.length;
        prefixSum = new int[n];
        prefixSum[0] = w[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i];
        }
        totalSum = prefixSum[n - 1];
    }

    /**
     * pickIndex(): randomly pick an index with probability proportional to weight
     * 1. Generate random target in [1, totalSum]
     * 2. Use binary search on prefixSum to find the first index >= target
     * 
     * Time Complexity: O(log n) per pick
     * Space Complexity: O(1)
     */
    public int pickIndex() {
        // Random integer in [1, totalSum]
        int target = ThreadLocalRandom.current().nextInt(totalSum) + 1;

        // Binary search to find the first index where prefixSum[i] >= target
        int idx = Arrays.binarySearch(prefixSum, target);
        if (idx < 0) {
            // If target not found, binarySearch returns (-insertionPoint - 1)
            idx = - (idx + 1);
        }
        return idx;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] w = {1, 3, 2}; // weights array
        RandomPickWithWeight picker = new RandomPickWithWeight(w);

        // Run multiple picks to see distribution
        int[] count = new int[w.length];
        for (int i = 0; i < 20; i++) {
            int idx = picker.pickIndex();
            count[idx]++;
            System.out.println("Picked index: " + idx);
        }

        System.out.println("\nDistribution of picks:");
        for (int i = 0; i < count.length; i++) {
            System.out.println("Index " + i + ": " + count[i] + " picks");
        }
    }
}
