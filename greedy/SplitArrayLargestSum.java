public class SplitArrayLargestSum {

    /*
     * LeetCode Problem: Split Array Largest Sum
     *
     * Question:
     * Given an integer array nums and an integer k,
     * split the array into k non-empty subarrays such that
     * the largest sum among them is minimized.
     *
     * Return the minimized largest sum.
     *
     * Example:
     * Input: nums = [7, 2, 5, 10, 8], k = 2
     * Output: 18
     *
     * How It Works (Binary Search + Greedy Validation):
     *
     * Idea:
     * The answer (maximum subarray sum) lies between:
     *   left  = max(nums)
     *   right = sum(nums)
     *
     * We binary search this range and check feasibility.
     *
     * Feasibility Check (Greedy):
     * - Traverse array and keep running sum.
     * - If sum exceeds mid, start new subarray.
     * - Count subarrays.
     * - If subarrays <= k → mid is valid.
     *
     * If valid → try smaller mid.
     * If not → try larger mid.
     *
     * Trace Example:
     * nums = [7, 2, 5, 10, 8]
     * k = 2
     *
     * Search Space:
     * left = 10 (max element)
     * right = 32 (sum)
     *
     * mid = 21
     * Greedy:
     *   [7,2,5] = 14
     *   [10]   = 10
     *   [8]    = 8
     *   → 3 parts ( > 2 ) → not valid
     *
     * mid = 27
     * Greedy:
     *   [7,2,5] = 14
     *   [10,8] = 18
     *   → 2 parts ( <= 2 ) → valid
     *
     * Continue binary search...
     * Final Answer = 18
     *
     * Running Time:
     * Time Complexity: O(n log(sum))
     *   - Binary search range + greedy check
     *
     * Space Complexity: O(1)
     */

    public static int splitArray(int[] nums, int k) {

        int left = 0;
        int right = 0;

        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (canSplit(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean canSplit(int[] nums, int k, int maxSum) {

        int count = 1;
        int currentSum = 0;

        for (int num : nums) {

            currentSum += num;

            if (currentSum > maxSum) {
                count++;
                currentSum = num;

                if (count > k) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int k = 2;

        System.out.println(splitArray(nums, k)); 
    }
}
