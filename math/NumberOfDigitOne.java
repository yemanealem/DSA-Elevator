/**
 * LeetCode 233 - Number of Digit One
 *
 * PROBLEM:
 * Count how many times digit '1' appears in all numbers from 0 to n.
 *
 * Example:
 * n = 13
 * Numbers: 1, 10, 11, 12, 13
 * Output = 6
 *
 * ---------------------------------------------
 * IDEA (IMPORTANT):
 * Instead of checking every number, we analyze digit positions:
 * ones, tens, hundreds, etc.
 *
 * For each position, we split number into:
 *   high | current digit | low
 *
 * Example for n = 2345 and factor = 10:
 * high = 234
 * cur  = 4
 * low  = 5
 *
 * ---------------------------------------------
 * CASES:
 *
 * 1. cur == 0:
 *    contribution = high * factor
 *
 * 2. cur == 1:
 *    contribution = high * factor + (low + 1)
 *
 * 3. cur > 1:
 *    contribution = (high + 1) * factor
 *
 * ---------------------------------------------
 * TIME COMPLEXITY:
 * O(log10 n) -> we process each digit
 *
 * SPACE COMPLEXITY:
 * O(1)
 */

public class NumberOfDigitOne {

    /**
     * Counts total number of digit '1' from 0 to n
     */
    public int countDigitOne(int n) {

        long factor = 1;   // current digit position (1, 10, 100, ...)
        int count = 0;

        /**
         * Loop through each digit position
         */
        while (factor <= n) {

            // STEP 1: extract high, current, low parts
            long high = n / (factor * 10);   // left side of current digit
            long cur  = (n / factor) % 10;   // current digit
            long low  = n % factor;          // right side of current digit

            /**
             * STEP 2: apply counting rules
             */

            // Case 1: current digit is 0
            if (cur == 0) {
                count += high * factor;
            }

            // Case 2: current digit is 1
            else if (cur == 1) {
                count += high * factor + (low + 1);
            }

            // Case 3: current digit > 1
            else {
                count += (high + 1) * factor;
            }

            // move to next digit position
            factor *= 10;
        }

        return count;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {

        NumberOfDigitOne solution = new NumberOfDigitOne();

        int n = 13;

        int result = solution.countDigitOne(n);

        System.out.println(
            "Number of digit '1' from 0 to " + n + " = " + result
        );
    }
}
