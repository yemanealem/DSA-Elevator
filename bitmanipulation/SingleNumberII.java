public class SingleNumberII {

    /*
     * LeetCode 137 - Single Number II
     *
     * Works for:
     * - Positive numbers
     * - Negative numbers
     *
     * Time: O(n)
     * Space: O(1)
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
        int[] nums3 = {-2, -2, -2, -5};  // negative test

        System.out.println(singleNumber(nums1)); // 3
        System.out.println(singleNumber(nums2)); // 99
        System.out.println(singleNumber(nums3)); // -5
    }
}
