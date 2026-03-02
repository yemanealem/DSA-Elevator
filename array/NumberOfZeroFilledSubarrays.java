/**
 * Question:
 * Given an integer array nums, return the number of subarrays
 * that consist only of zeros.
 *
 * Example:
 * nums = [0, 0, 1, 0, 0, 0]
 *
 * Zero groups:
 * - group1: 0,0  -> length = 2 -> subarrays = 2*3/2 = 3
 * - group2: 0,0,0 -> length = 3 -> subarrays = 3*4/2 = 6
 *
 * Total = 3 + 6 = 9
 *
 * Approach:
 * - Count consecutive zeros
 * - When a non-zero appears, compute subarrays from that streak
 * - Also compute after loop for last streak
 *
 * Formula:
 * For streak of k zeros:
 * subarrays = k * (k + 1) / 2
 *
 * Running Time:
 * - Time: O(n)
 * - Space: O(1)
 */

public class NumberOfZeroFilledSubarrays {

    public static long zeroFilledSubarrays(int[] nums) {
        long result = 0;
        int zeros = 0;

        for (int num : nums) {
            if (num == 0) {
                zeros++;
            } else if (zeros > 0) {
                result += (long) zeros * (zeros + 1) / 2;
                zeros = 0;
            }
        }

        // handle last streak of zeros
        if (zeros > 0) {
            result += (long) zeros * (zeros + 1) / 2;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 0, 0, 0};

        System.out.println("Zero-filled subarrays: " + zeroFilledSubarrays(nums));
    }
}
