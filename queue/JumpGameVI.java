public class JumpGameVI {

    /*
     * ================================
     * PROBLEM:
     * ================================
     * Given an integer array nums and an integer k.
     *
     * You start at index 0.
     *
     * From index i, you can jump to any index j such that:
     *      i < j ≤ i + k
     *
     * Your goal is to reach the last index
     * while maximizing the total score collected.
     *
     * ================================
     * CLARIFICATION:
     * ================================
     * - You must move forward only.
     * - You cannot jump beyond k steps.
     * - The objective is to maximize the sum.
     * - This is a Dynamic Programming problem.
     * - To optimize, we use a Monotonic Deque.
     *
     * ================================
     * APPROACH:
     * ================================
     * - Use nums array to store running dp values.
     * - Use array-based monotonic decreasing deque
     *   to keep track of maximum values in last k window.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */

    public int maxResult(int[] nums, int k) {

        int n = nums.length;

        // Array-based deque for performance
        int[] deque = new int[n];
        int head = 0, tail = 0;

        // Start from index 0
        deque[tail++] = 0;

        for (int i = 1; i < n; i++) {

            // Remove indices outside window
            while (head < tail && deque[head] < i - k) {
                head++;
            }

            // Update current score
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
