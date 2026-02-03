// LeetCode 342: Power of Four
// Returns true if n is a power of four using bit manipulation
public class IsPowerOfFour {

    static class Solution {
        public boolean isPowerOfFour(int n) {
            // Step 1: Check positive and power of two
            // Step 2: Check if single 1-bit is at odd position using mask
            return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] testCases = {1, 4, 16, 64, 5, 8, 0, -4};

        for (int n : testCases) {
            System.out.println(n + " is power of four? " + sol.isPowerOfFour(n));
        }
    }
}
