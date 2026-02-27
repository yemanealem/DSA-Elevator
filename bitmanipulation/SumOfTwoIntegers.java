public class SumOfTwoIntegers {

    /*
     * LeetCode 371 - Sum of Two Integers
     *
     * Compute sum without using '+' or '-'.
     *
     * Idea:
     * - XOR gives sum without carry
     * - AND + shift gives carry
     * - Repeat until carry becomes 0
     *
     * Time Complexity: O(1)  (max 32 iterations)
     * Space Complexity: O(1)
     */

    public static int getSum(int a, int b) {

        while (b != 0) {

            int carry = (a & b) << 1;  // calculate carry
            a = a ^ b;                // sum without carry
            b = carry;                // move carry forward
        }

        return a;
    }

    public static void main(String[] args) {

        System.out.println(getSum(1, 2));     // 3
        System.out.println(getSum(-2, 3));    // 1
        System.out.println(getSum(-5, -7));   // -12
    }
}
