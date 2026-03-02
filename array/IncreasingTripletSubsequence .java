/**
 * Question:
 * Determine if there exists a triplet (i < j < k) such that:
 * nums[i] < nums[j] < nums[k]
 *
 * Example to trace:
 * nums = [1, 2, 3, 4]
 *
 * Trace:
 * first = 1
 * second = 2
 * next = 3 -> greater than second => triplet exists (1,2,3)
 *
 * Explanation:
 * - 'first' stores smallest so far
 * - 'second' stores smallest greater than first
 * - if we find num > second → triplet found
 *
 * Running Time: O(n)
 * Space: O(1)
 */

public class IncreasingTripletSubsequence {

    public static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {

            // if smaller than first, update first
            if (num <= first) {
                first = num;
            }
            // else if smaller than second but greater than first
            else if (num <= second) {
                second = num;
            }
            // if greater than both → triplet found
            else {
                return true;
            }

            // ----- TRACE (example: [1,2,3,4]) -----
            // Step 1: num=1 -> first=1
            // Step 2: num=2 -> second=2
            // Step 3: num=3 -> >second => return true
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        System.out.println(increasingTriplet(nums));
    }
}
