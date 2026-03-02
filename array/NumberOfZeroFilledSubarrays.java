/**
 * LeetCode: Number of Zero-Filled Subarrays
 *
 * Question:
 * Given an integer array nums, return the number of subarrays
 * that consist only of zeros.
 *
 * Approach:
 * - Count consecutive zeros.
 * - For each group of k zeros, add k*(k+1)/2 to result.
 *
 * Why?
 * In a group of zeros of length k:
 * subarrays = 1 + 2 + ... + k = k*(k+1)/2
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class NumberOfZeroFilledSubarrays {

    public static long zeroFilledSubarrays(int[] nums) {
        long count = 0;
        long zeros = 0;

        for (int num : nums) {
            if (num == 0) {
                zeros++;
            } else {
                count += (zeros * (zeros + 1)) / 2;
                zeros = 0;
            }
        }

        // Add last group of zeros
        count += (zeros * (zeros + 1)) / 2;

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 0, 0, 0};

        System.out.println("Zero-filled subarrays: " + zeroFilledSubarrays(nums));
    }
}
