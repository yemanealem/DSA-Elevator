import java.util.Random;

class RandomPickWithWeight {
    private int[] prefixSum;
    private int totalSum;
    private Random rand;

    public RandomPickWithWeight(int[] w) {
        int n = w.length;
        prefixSum = new int[n];
        prefixSum[0] = w[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i];
        }
        totalSum = prefixSum[n - 1];
        rand = new Random();
    }

    public int pickIndex() {
        // Random number in range [1, totalSum]
        int target = rand.nextInt(totalSum) + 1;

        // Binary search to find first prefixSum >= target
        int low = 0, high = prefixSum.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prefixSum[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // Main for testing
    public static void main(String[] args) {
        int[] w = {1, 3, 2};
        RandomPickWithWeight picker = new RandomPickWithWeight(w);

        // Run 10 picks
        for (int i = 0; i < 10; i++) {
            System.out.println("Picked index: " + picker.pickIndex());
        }
    }
}
