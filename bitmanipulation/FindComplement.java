// LeetCode 476: Number Complement
// Problem:
// Given a positive integer num, return its complement. The complement of 
// a number is obtained by flipping all bits in its binary representation.
// Ignore leading zeros in the binary form.
//
// Example:
// Input: num = 5  (binary: 101) → Output: 2  (binary: 010)
// Input: num = 1  (binary: 1)   → Output: 0  (binary: 0)
//
// How it works:
// 1. Build a mask that has all 1s in the same length as num.
//    For example, num = 5 (101), mask = 111 (7).
//    We build the mask by left-shifting and OR'ing 1 until mask >= num.
// 2. XOR num with the mask flips all bits: num ^ mask.
//    This gives the complement of num.

public class FindComplement {

    static class Solution {
        public int findComplement(int num) {
            int mask = 1;
            while (mask < num) {
                mask = (mask << 1) | 1; // create a mask with all 1s
            }
            return num ^ mask; // XOR flips all bits
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] testCases = {5, 1, 10, 7};

        for (int n : testCases) {
            System.out.println("Number: " + n + " → Complement: " + sol.findComplement(n));
        }
    }
}
