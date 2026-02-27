public class SumOfTwoIntegers {

    /*
     * LeetCode 371 - Sum of Two Integers (Without + or -)
     *
     * ------------------------------------------------------
     * Idea (Bit Manipulation)
     * ------------------------------------------------------
     *
     * We simulate binary addition:
     *
     * XOR (^)  -> sum without carry
     * AND (&)  -> find carry
     * SHIFT << -> move carry to next bit
     *
     * Repeat until no carry remains.
     *
     * ------------------------------------------------------
     * Trace Example: a = 5, b = 3
     * ------------------------------------------------------
     *
     * Binary:
     * 5 = 0101
     * 3 = 0011
     *
     * Iteration 1:
     * sum  = 0101 ^ 0011 = 0110 (6)
     * carry= 0101 & 0011 = 0001 -> <<1 = 0010 (2)
     *
     * Now:
     * a = 6
     * b = 2
     *
     * Iteration 2:
     * sum  = 0110 ^ 0010 = 0100 (4)
     * carry= 0110 & 0010 = 0010 -> <<1 = 0100 (4)
     *
     * Now:
     * a = 4
     * b = 4
     *
     * Iteration 3:
     * sum  = 0100 ^ 0100 = 0000
     * carry= 0100 & 0100 = 0100 -> <<1 = 1000 (8)
     *
     * Now:
     * a = 0
     * b = 8
     *
     * Iteration 4:
     * sum  = 0000 ^ 1000 = 1000 (8)
     * carry= 0000
     *
     * Stop (no carry)
     *
     * Result = 8
     *
     * ------------------------------------------------------
     * Why It Works:
     *
     * - XOR adds bits where they differ
     * - AND finds where both bits are 1 (carry)
     * - Shift moves carry to next position
     *
     * Two's complement representation allows this
     * to work for negative numbers too.
     *
     * Time: O(1) (max 32 iterations)
     * Space: O(1)
     */

    public static int getSum(int a, int b) {

        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }

        return a;
    }

    public static void main(String[] args) {

        System.out.println(getSum(5, 3));    
        System.out.println(getSum(-2, 3));  
        System.out.println(getSum(10, -7));  
    }
}
