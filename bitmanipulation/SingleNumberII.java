public class SingleNumberII {

    /*
     * LeetCode 137 - Single Number II
     *
     * Problem:
     * Every element appears three times except for one.
     * Find that single one.
     *
     * Approach:
     * Use two variables (ones, twos) to track bits.
     *
     * Logic:
     * - 'ones' stores bits that appeared once.
     * - 'twos' stores bits that appeared twice.
     * - If a bit appears third time, it gets removed from both.
     *
     * Formula:
     * ones = (ones ^ num) & ~twos
     * twos = (twos ^ num) & ~ones
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */

    public static int singleNumber(int[] nums) {

        int ones = 0;
        int twos = 0;

        for (int num : nums) {

            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }

        return ones;
    }

    public static void main(String[] args) {

        int[] nums1 = {2, 2, 3, 2};
        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};

        System.out.println(singleNumber(nums1)); // 3
        System.out.println(singleNumber(nums2)); // 99
    }
}
