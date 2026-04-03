/**
 * Problem: String to Integer (atoi) - LeetCode 8
 *
 * Given a string, convert it to a 32-bit signed integer.
 *
 * Rules:
 * 1. Ignore leading whitespace.
 * 2. Check for an optional '+' or '-' sign.
 * 3. Read digits until a non-digit character is found.
 * 4. Convert the digits into an integer.
 * 5. If the value exceeds 32-bit integer range, clamp it:
 *      - Integer.MAX_VALUE =  2^31 - 1
 *      - Integer.MIN_VALUE = -2^31
 *
 * ------------------------------------------------------------
 * How it works:
 *
 * - We iterate through the string character by character.
 * - Skip spaces first.
 * - Determine the sign.
 * - Build the number digit by digit:
 *
 *      result = result * 10 + digit
 *
 * - Before adding a new digit, we check for overflow.
 *
 * Overflow Check:
 * - If result > (Integer.MAX_VALUE - digit) / 10
 *   → adding the next digit will overflow
 *   → return max or min value accordingly
 *
 * ------------------------------------------------------------
 * Example:
 * Input: "   -42"
 *
 * Steps:
 * 1. Skip spaces → "-42"
 * 2. Sign = -1
 * 3. Read digits:
 *      result = 0
 *      result = 0 * 10 + 4 = 4
 *      result = 4 * 10 + 2 = 42
 * 4. Apply sign → -42
 *
 * ------------------------------------------------------------
 * Time Complexity:
 * - O(n) → We scan the string once.
 *
 * Space Complexity:
 * - O(1) → Constant space used.
 *
 * ------------------------------------------------------------
 * Key Insight:
 * We build the number digit by digit while preventing overflow.
 */

public class Atoi {

    public static int myAtoi(String s) {

        int i = 0, n = s.length();

        // Step 1: Skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: Check sign
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        int result = 0;

        // Step 3: Process digits
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // Step 4: Overflow check
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // Build number
            result = result * 10 + digit;
            i++;
        }

        // Step 5: Apply sign
        return result * sign;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("   -42")); // -42
        System.out.println(myAtoi("4193 with words")); // 4193
        System.out.println(myAtoi("words and 987")); // 0
    }
}
