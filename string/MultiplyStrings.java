/**
 * Problem: Multiply Strings (LeetCode 43)
 *
 * Given two non-negative integers num1 and num2 represented as strings,
 * return their product as a string.
 *
 * Constraints:
 * - You must NOT convert the strings directly into integers.
 * - The numbers can be very large.
 *
 * ------------------------------------------------------------
 * How it works:
 *
 * This simulates manual multiplication (like on paper), but instead of
 * building full intermediate numbers, we directly place results into
 * their correct positions using an array.
 *
 * Key Idea:
 * - Use an int array of size (n + m), where n and m are lengths of num1 and num2.
 * - Each multiplication contributes to two positions:
 *
 *      result[i + j + 1] → stores the current digit
 *      result[i + j]     → stores the carry
 *
 * Why do we add existing value?
 * - Multiple multiplications contribute to the same index,
 *   so we must ADD instead of overwrite:
 *
 *      sum = digit1 * digit2 + result[i + j + 1]
 *
 * Then split:
 *      digit  = sum % 10
 *      carry  = sum / 10
 *
 * ------------------------------------------------------------
 * Example:
 * num1 = "163", num2 = "95"
 *
 * We simulate:
 *   163 × 95 = 15485
 *
 * By filling the result array step-by-step.
 *
 * ------------------------------------------------------------
 * Time Complexity:
 * - O(n × m)
 *   We multiply every digit of num1 with every digit of num2.
 *
 * Space Complexity:
 * - O(n + m)
 *   For the result array.
 *
 * ------------------------------------------------------------
 * Key Insight:
 * Code = Paper multiplication, but we add values immediately
 * instead of building rows first.
 */
public class MultiplyStrings {

    public static String multiply(String num1, String num2) {

        // Edge case: anything multiplied by 0 is 0
        if (num1.equals("0") || num2.equals("0")) return "0";

        int n = num1.length();
        int m = num2.length();

        // Result array to store digits
        int[] result = new int[n + m];

        // Multiply digits from right to left
        for (int i = n - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';

            for (int j = m - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';

                int pos = i + j + 1;

                // Multiply and add existing value
                int sum = d1 * d2 + result[pos];

                // Store digit and carry
                result[pos] = sum % 10;
                result[pos - 1] += sum / 10;
            }
        }

        // Build result string (skip leading zeros)
        StringBuilder sb = new StringBuilder(n + m);

        int i = 0;
        while (i < result.length && result[i] == 0) i++;

        for (; i < result.length; i++) {
            sb.append(result[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("163", "95")); // Output: 15485
    }
}
