public class SplitArrayLargestSum {

    /*
     * LeetCode 410: Split Array Largest Sum
     *
     * Question:
     * Split the array into k non-empty subarrays such that
     * the largest sum among them is minimized.
     *
     * Approach:
     * Binary Search on Answer + Greedy validation.
     *
     * Why?
     * The answer lies between:
     *   max(nums)  and  sum(nums)
     *
     * For each mid:
     *   Check if we can split into <= k subarrays
     *   such that each subarray sum <= mid.
     *
     * Running Time:
     * Time Complexity: O(n log(sum))
     * Space Complexity: O(1)
     */

    public static int splitArray(int[] nums, int k) {

        int left = 0;
        int right = 0;

        // Define search space
        for (int num : nums) {
            left = Math.max(left, num); // minimum possible max sum
            right += num;               // maximum possible max sum
        }

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (canSplit(nums, k, mid)) {
                right = mid;  // try smaller max sum
            } else {
                left = mid + 1; // need larger max sum
            }
        }

        return left;
    }

    // Greedy validation
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

        System.out.println(splitArray(nums, k)); // Output: 18
    }
}
