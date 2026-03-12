import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPickWithWeight {
    private int[] prefixSum;
    private int totalSum;

    // Constructor: builds prefix sum array
    public RandomPickWithWeight(int[] w) {
        int n = w.length;
        prefixSum = new int[n];
        prefixSum[0] = w[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i];
        }
        totalSum = prefixSum[n - 1];
    }

    // Pick an index randomly with probability proportional to weight
    public int pickIndex() {
        int target = ThreadLocalRandom.current().nextInt(totalSum) + 1;
        int idx = Arrays.binarySearch(prefixSum, target);
        if (idx < 0) {
            idx = - (idx + 1);
        }
        return idx;
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] w = {1, 3, 2};
        RandomPickWithWeight picker = new RandomPickWithWeight(w);

        // Run 20 picks to see the distribution
        int[] count = new int[w.length];
        for (int i = 0; i < 20; i++) {
            int idx = picker.pickIndex();
            count[idx]++;
            System.out.println("Picked index: " + idx);
        }

        System.out.println("\nDistribution:");
        for (int i = 0; i < count.length; i++) {
            System.out.println("Index " + i + ": " + count[i] + " picks");
        }
    }
}
