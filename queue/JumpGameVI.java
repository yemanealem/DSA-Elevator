public class JumpGameVI {

    /*
     * Problem:
     * Jump from index i to any j where:
     *      i < j ≤ i + k
     * Goal: Maximize score to reach last index.
     *
     * Optimized Approach:
     * - Use DP stored directly in nums
     * - Use array-based monotonic decreasing deque
     *
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */

    public int maxResult(int[] nums, int k) {

        int n = nums.length;

        // Array-based deque for better performance
        int[] deque = new int[n];
        int head = 0, tail = 0;

        // Start with index 0
        deque[tail++] = 0;

        for (int i = 1; i < n; i++) {

            // Remove indices outside the window
            while (head < tail && deque[head] < i - k) {
                head++;
            }

            // Update current value using best previous
            nums[i] += nums[deque[head]];

            // Maintain decreasing order
            while (head < tail && nums[i] >= nums[deque[tail - 1]]) {
                tail--;
            }

            deque[tail++] = i;
        }

        return nums[n - 1];
    }

    // Main method for testing
    public static void main(String[] args) {

        JumpGameVI solution = new JumpGameVI();

        int[] nums = {1, -1, -2, 4, -7, 3};
        int k = 2;

        int result = solution.maxResult(nums, k);

        System.out.println("Maximum Score: " + result);
    }
}
