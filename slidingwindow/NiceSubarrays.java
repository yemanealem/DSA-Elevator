/**
 * Problem: Count Number of Nice Subarrays
 *
 * A "nice" subarray contains exactly k odd numbers.
 *
 * Optimization:
 * - Use a single sliding window
 * - Track:
 *    - count of odd numbers
 *    - left boundary of window
 * - For each valid window, count how many subarrays end at "right"
 *   that contain exactly k odd numbers.
 *
 * ------------------------------------------------------------
 * Running Time:
 * Time Complexity: O(n)
 * - Each element is visited at most twice.
 *
 * Space Complexity: O(1)
 */

public class NiceSubarrays {

    public int numberOfSubarrays(int[] nums, int k) {
        int left = 0;
        int countOdd = 0;
        int result = 0;

        // leftMost tracks the first valid position for exactly k odds
        int leftMost = 0;

        for (int right = 0; right < nums.length; right++) {

            // Count odd numbers
            if (nums[right] % 2 == 1) {
                countOdd++;
            }

            // When we exceed k, move left forward
            while (countOdd > k) {
                if (nums[left] % 2 == 1) {
                    countOdd--;
                }
                left++;
                leftMost = left;
            }

            // When we have exactly k odd numbers
            if (countOdd == k) {
                // Count how many ways to extend the left boundary
                int temp = leftMost;

                while (temp <= right && nums[temp] % 2 == 0) {
                    temp++;
                }

                result += (temp - leftMost + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        NiceSubarrays sol = new NiceSubarrays();

        int[] nums1 = {1, 1, 2, 1, 1};
        System.out.println(sol.numberOfSubarrays(nums1, 3)); // 2

        int[] nums2 = {2, 4, 6};
        System.out.println(sol.numberOfSubarrays(nums2, 1)); // 0
    }
}
