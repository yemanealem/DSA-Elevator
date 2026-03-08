/**
 * Maximum Sum of Distinct Subarrays of Length K
 *
 * Problem:
 * Find maximum sum of subarrays of length k where all elements are distinct.
 *
 * How it works:
 * 1. Use sliding window of size k.
 * 2. Maintain a HashSet to track distinct elements in the window.
 * 3. If duplicate found, slide the window until all elements are distinct.
 * 4. Update maximum sum for valid windows.
 *
 * Example:
 * nums = [1,2,7,7,4,3,6], k = 3
 * Window [7,4,3] sum = 14 -> maximum sum
 *
 * Running Time: O(n), Space: O(k)
 */
import java.util.*;

class MaxSumDistinctSubarray {
    
    public int maximumSum(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int left = 0, sum = 0, maxSum = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Shrink window if duplicate found
            while (set.contains(nums[right])) {
                sum -= nums[left];
                set.remove(nums[left]);
                left++;
            }
            
            // Add new element
            set.add(nums[right]);
            sum += nums[right];
            
            // Check if window size is k
            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, sum);
                // Slide window by removing left element
                sum -= nums[left];
                set.remove(nums[left]);
                left++;
            }
        }
        
        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumDistinctSubarray sol = new MaxSumDistinctSubarray();
        int[] nums = {1,2,7,7,4,3,6};
        int k = 3;
        System.out.println("Maximum sum of distinct subarray: " + sol.maximumSum(nums, k));
        // Output: 14
    }
}
