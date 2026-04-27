/**
 * LeetCode 273 - Integer to English Words
 *
 * Problem:
 * Convert a non-negative integer into its English words representation.
 *
 * Example:
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 *
 * ---------------------------------------------
 * IDEA:
 * We break the number into groups of 3 digits:
 *   Billion | Million | Thousand | Hundreds
 *
 * Then convert each 3-digit group separately.
 *
 * ---------------------------------------------
 * TIME COMPLEXITY:
 * O(1) -> because we process at most 4 groups (fixed size)
 *
 * SPACE COMPLEXITY:
 * O(1) -> constant arrays used for mapping words
 */

public class IntegerToEnglishWords {

    private final String[] below20 = {
        "", "One", "Two", "Three", "Four", "Five", "Six",
        "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
        "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen"
    };

    private final String[] tens = {
        "", "", "Twenty", "Thirty", "Forty",
        "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public String numberToWords(int num) {

        if (num == 0) return "Zero";

        StringBuilder result = new StringBuilder();

    
        if (num >= 1_000_000_000) {
            result.append(helper(num / 1_000_000_000)).append(" Billion ");
            num %= 1_000_000_000;
        }

        if (num >= 1_000_000) {
            result.append(helper(num / 1_000_000)).append(" Million ");
            num %= 1_000_000;
        }

        if (num >= 1000) {
            result.append(helper(num / 1000)).append(" Thousand ");
            num %= 1000;
        }

        if (num > 0) {
            result.append(helper(num));
        }

      
        return result.toString().trim();
    }

  
    private String helper(int num) {
        StringBuilder sb = new StringBuilder();

        if (num >= 100) {
            sb.append(below20[num / 100]).append(" Hundred ");
            num %= 100;
        }

        if (num >= 20) {
            sb.append(tens[num / 10]).append(" ");
            num %= 10;
        }

        // Handle 1–19
        if (num > 0) {
            sb.append(below20[num]).append(" ");
        }

        return sb.toString();
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        IntegerToEnglishWords solution = new IntegerToEnglishWords();

        System.out.println(solution.numberToWords(123));
        // One Hundred Twenty Three

        System.out.println(solution.numberToWords(12345));
        // Twelve Thousand Three Hundred Forty Five

        System.out.println(solution.numberToWords(1000010));
        // One Million Ten

        System.out.println(solution.numberToWords(0));
        // Zero
    }
}
