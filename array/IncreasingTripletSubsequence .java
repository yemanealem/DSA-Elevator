/**
 * LeetCode: Increasing Triplet Subsequence
 *
 * Question:
 * Determine if there exists a triplet (i < j < k) such that:
 * nums[i] < nums[j] < nums[k]
 *
 * Approach:
 * - Track smallest and second smallest values.
 * - If we find a number larger than second, triplet exists.
 *
 * Example:
 * nums = [1, 2, 3, 4]
 *
 * Trace:
 * first = 1
 * second = 2
 * next = 3 -> greater than second => triplet found (1,2,3)
 *
 * Running Time: O(n)
 * Space: O(1)
 */

public class IncreasingTripletSubsequence {

    public static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true; // found num > second > first
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        System.out.println(increasingTriplet(nums));
    }
}
