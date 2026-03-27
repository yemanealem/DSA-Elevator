/**
 * Problem: Count Number of Nice Subarrays
 *
 * A "nice" subarray contains exactly k odd numbers.
 *
 * Approach:
 * - Use sliding window to count subarrays with at most k odd numbers.
 * - Then compute:
 *      exactly k = atMost(k) - atMost(k - 1)
 *
 * ------------------------------------------------------------
 * Running Time:
 * Time Complexity: O(n)
 * - Each element is processed at most twice (left and right pointer)
 *
 * Space Complexity: O(1)
 */

public class NiceSubarrays {

    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        int left = 0;
        int countOdd = 0;
        int result = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] % 2 == 1) {
                countOdd++;
            }

            while (countOdd > k) {
                if (nums[left] % 2 == 1) {
                    countOdd--;
                }
                left++;
            }

            // Add all subarrays ending at right
            result += right - left + 1;
        }

        return result;
    }

    public static void main(String[] args) {
        NiceSubarrays sol = new NiceSubarrays();

        int[] nums1 = {1, 1, 2, 1, 1};
        int k1 = 3;
        System.out.println(sol.numberOfSubarrays(nums1, k1)); // Output: 2

        int[] nums2 = {2, 4, 6};
        int k2 = 1;
        System.out.println(sol.numberOfSubarrays(nums2, k2)); // Output: 0
    }
}
