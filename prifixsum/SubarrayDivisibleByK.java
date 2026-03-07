/*
 * QUESTION:
 * Subarray Sums Divisible by K
 * ---------------------------
 * Given an integer array nums and an integer k,
 * return the number of subarrays whose sum is divisible by k.
 *
 * A subarray sum is divisible by k if:
 * (prefixSum[i] - prefixSum[j]) % k == 0
 *
 * That means:
 * prefixSum[i] % k == prefixSum[j] % k
 *
 * So we count how many times each remainder occurs.
 *
 * HOW IT WORKS (Prefix Mod + HashMap):
 * ---------------------------
 * 1. Compute running prefix sum.
 * 2. Take modulo k (handle negative by adding k).
 * 3. If same remainder appeared before, add its count.
 * 4. Update remainder frequency.
 *
 * RUNNING TIME:
 * ---------------------------
 * - Time Complexity: O(n)
 * - Space Complexity: O(k) (for remainder map)
 *
 * This is optimal for this problem.
 */

import java.util.HashMap;

public class SubarrayDivisibleByK {

    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // base case: remainder 0 appears once

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            int remainder = prefixSum % k;
            if (remainder < 0) remainder += k; // handle negative remainder

            count += map.getOrDefault(remainder, 0);

            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        SubarrayDivisibleByK solution = new SubarrayDivisibleByK();

        int[] nums = {4, 5, 0, -2, -3, 1};
        int k = 5;

        System.out.println(solution.subarraysDivByK(nums, k)); // Output: 7
    }
}
