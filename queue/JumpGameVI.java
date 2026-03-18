import java.util.Deque;
import java.util.LinkedList;

/*
 * JumpGameVI
 * ----------
 * Problem:
 * Given an integer array nums and an integer k,
 * you can jump from index i to any index j where:
 *      i < j ≤ i + k
 *
 * The goal is to reach the last index while maximizing
 * the total score collected.
 *
 * Approach:
 * - Use Dynamic Programming (dp[i])
 * - Use a Monotonic Decreasing Deque to keep track of
 *   the maximum dp value in the last k indices.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class JumpGameVI {

    public int maxResult(int[] nums, int k) {

        int n = nums.length;

        // dp[i] represents the maximum score to reach index i
        int[] dp = new int[n];

        // Deque to store indices in decreasing order of dp values
        Deque<Integer> deque = new LinkedList<>();

        dp[0] = nums[0];
        deque.offer(0);

        for (int i = 1; i < n; i++) {

            // Remove indices that are out of the window (i - k)
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // Best previous value is at the front
            dp[i] = nums[i] + dp[deque.peekFirst()];

            // Maintain monotonic decreasing order
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return dp[n - 1];
    }
}
