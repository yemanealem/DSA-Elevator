/*
 * QUESTION:
 * Subarray Sum Equals K
 * ---------------------------
 * Given an integer array nums and an integer k,
 * return the total number of continuous subarrays
 * whose sum equals k.
 *
 * HOW IT WORKS (Prefix Sum + HashMap):
 * ---------------------------
 * Let prefixSum[i] = sum of elements from index 0 to i.
 *
 * Sum of subarray (j+1 ... i) = prefixSum[i] - prefixSum[j]
 *
 * We need:
 * prefixSum[j] = prefixSum[i] - k
 *
 * If prefixSum[i] - k exists in the map, that means
 * a subarray ending at i has sum = k.
 *
 * We store prefix sum frequencies in a HashMap:
 * map[prefixSum] = how many times this sum appeared.
 *
 * RUNNING TIME:
 * ---------------------------
 * - Time Complexity: O(n)
 * - Space Complexity: O(n)
 *
 * This is optimal for this problem.
 */

import java.util.HashMap;

public class SubarraySum {

    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // base case: empty prefix sum

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            Integer freq = map.get(prefixSum - k);
            if (freq != null) {
                count += freq;
            }

            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        SubarraySum solution = new SubarraySum();

        int[] nums = {1, 1, 1};
        System.out.println(solution.subarraySum(nums, 2)); // Output: 2

        int[] nums2 = {1, 2, 3};
        System.out.println(solution.subarraySum(nums2, 3)); // Output: 2
    }
}
