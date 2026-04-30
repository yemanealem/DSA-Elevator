import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> queue = new ArrayDeque<>();
        
        int flip = 0; // current flip parity (0 or 1)
        int res = 0;

        for (int i = 0; i < n; i++) {

            // remove expired flips
            if (!queue.isEmpty() && queue.peekFirst() == i) {
                queue.pollFirst();
                flip ^= 1;
            }

            // if current bit after flips is 0, we must flip here
            if (nums[i] == flip) {
                if (i + k > n) {
                    return -1;
                }

                res++;
                flip ^= 1;           // start new flip effect
                queue.offer(i + k);  // mark when this flip expires
            }
        }

        return res;
    }
}
