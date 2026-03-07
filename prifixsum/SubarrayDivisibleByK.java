/*
 * QUESTION:
 * Subarray Sums Divisible by K
 *
 * Given an integer array nums and an integer k,
 * return the number of subarrays whose sum is divisible by k.
 *
 * APPROACH (Prefix Remainder Counting):
 * ------------------------------------
 * A subarray sum is divisible by k if:
 *
 * (prefixSum[i] - prefixSum[j]) % k == 0
 *
 * This is equivalent to:
 *
 * prefixSum[i] % k == prefixSum[j] % k
 *
 * So we count occurrences of each remainder.
 *
 * COMPLEXITY:
 * - Time: O(n)
 * - Space: O(k)
 */

import java.util.HashMap;
import java.util.Map;

public class SubarrayDivisibleByK {

    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1); // base case: empty prefix

        int prefixSum = 0;
        int result = 0;

        for (int num : nums) {
            prefixSum += num;

            int remainder = prefixSum % k;
            if (remainder < 0) remainder += k; // normalize negative remainder

            result += remainderCount.getOrDefault(remainder, 0);

            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        SubarrayDivisibleByK solver = new SubarrayDivisibleByK();

        int[] nums = {4, 5, 0, -2, -3, 1};
        int k = 5;

        System.out.println(solver.subarraysDivByK(nums, k)); // Output: 7
    }
}
