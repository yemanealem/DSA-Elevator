import java.util.*;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // Edge case
        if (nums == null || k == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];
        
        Deque<Integer> deque = new ArrayDeque<>(); // stores indices
        
        for (int i = 0; i < n; i++) {
            
            // 1. Remove indices out of window
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 2. Remove smaller elements (maintain decreasing order)
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3. Add current index
            deque.offerLast(i);

            // 4. Add to result when window is full
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
}
