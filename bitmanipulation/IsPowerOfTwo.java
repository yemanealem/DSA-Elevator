// LeetCode 231: Power of Two
// Problem: Given an integer n, return true if it is a power of two, else false.
// Explanation: 
// A power of two has exactly one '1' bit in binary.
// Using bit manipulation: n & (n - 1) == 0 checks this condition in O(1) time.
// Edge cases: n <= 0 returns false.

public class IsPowerOfTwo {

    static class Solution {
        public boolean isPowerOfTwo(int n) {
            // Returns true if n is a power of two
            return n > 0 && (n & (n - 1)) == 0;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] testCases = {1, 2, 3, 4, 16, 18, 0, -8};

        for (int n : testCases) {
            System.out.println(n + " is power of two? " + sol.isPowerOfTwo(n));
        }
    }
}
