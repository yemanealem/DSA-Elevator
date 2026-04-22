import java.util.*;

/*
 * Problem: Sliding Window Maximum (LeetCode 239)
 *
 * Given an array of integers nums and an integer k, return an array of the maximum
 * values of each sliding window of size k.
 *
 * Example:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 *
 * ------------------------------------------------------------
 * How It Works:
 * ------------------------------------------------------------
 * We use a Deque (Double Ended Queue) to store indices of elements.
 *
 * Key idea:
 * - The deque stores indices in decreasing order of their values.
 * - The front of the deque always contains the index of the maximum element
 *   for the current window.
 *
 * Steps:
 * 1. Remove indices that are out of the current window (i - k + 1).
 * 2. Remove elements from the back of deque if they are smaller than current element,
 *    because they can never be the maximum.
 * 3. Add current index to the deque.
 * 4. When window size reaches k, take the front element as the maximum.
 *
 * ------------------------------------------------------------
 * Time Complexity:
 * ------------------------------------------------------------
 * O(n) — Each element is added and removed at most once.
 *
 * ------------------------------------------------------------
 * Space Complexity:
 * ------------------------------------------------------------
 * O(k) — Deque stores at most k elements.
 */

public class maxSlidingWindow {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) return new int[0];

        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        int idx = 0;

        for (int i = 0; i < n; i++) {
            int current = nums[i];

            // 1. Remove indices that are out of this window
            if (deque.peekFirst() != null && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 2. Maintain decreasing order in deque
            while (deque.peekLast() != null && nums[deque.peekLast()] < current) {
                deque.pollLast();
            }

            // 3. Add current index
            deque.offerLast(i);

            // 4. Add max to result when window is ready
            if (i >= k - 1) {
                result[idx++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] result = maxSlidingWindow(nums, k);

        System.out.println("Sliding Window Maximums:");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
