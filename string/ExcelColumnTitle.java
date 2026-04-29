/*
 * LeetCode 168 - Excel Sheet Column Title
 *
 * Problem:
 * Convert a given column number into its Excel column title.
 *
 * Key Idea:
 * - This is a base-26 conversion WITHOUT zero
 * - So we decrement first to make it 0-based
 *
 * Example:
 * 28:
 * 28 - 1 = 27 → 'B'
 * 27 / 26 = 1
 * 1 - 1 = 0 → 'A'
 * Result = "AB"
 *
 * ------------------------------------------------------------
 * Time Complexity: O(log₍26₎ n)
 * Space Complexity: O(1) (excluding output)
 * ------------------------------------------------------------
 */

public class ExcelColumnTitle {

    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--; // 🔥 key step

            int rem = columnNumber % 26;
            sb.append((char) ('A' + rem));

            columnNumber /= 26;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        ExcelColumnTitle sol = new ExcelColumnTitle();
        System.out.println(sol.convertToTitle(28)); // AB
    }
}
