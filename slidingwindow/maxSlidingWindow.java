import java.util.*;

public class maxSlidingWindow {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) return new int[0];

        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        int idx = 0;

        for (int i = 0; i < n; i++) {
            int current = nums[i];

            // Remove indices out of window
            if (deque.peekFirst() != null && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Maintain decreasing order
            while (deque.peekLast() != null && nums[deque.peekLast()] < current) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // Add max to result
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
