/*
==================== PROBLEM ====================
Find the nth digit in the infinite sequence:
"1234567891011121314..."

Example:
n = 11 → Output = 0 (because sequence is ...9,10,11 → 10 is the 10th & 11th digit)

=================================================
HOW IT WORKS (INTUITION)
=================================================
We group digits by length:

1-digit numbers: 1 - 9      → 9 numbers → 9 * 1 digits
2-digit numbers: 10 - 99    → 90 numbers → 90 * 2 digits
3-digit numbers: 100 - 999  → 900 numbers → 900 * 3 digits

Step 1:
Find which group contains the nth digit using binary search style logic.

Step 2:
Find the exact number in that group.

Step 3:
Find the exact digit inside that number.

=================================================
TIME COMPLEXITY
=================================================
Time Complexity: O(log n)
Space Complexity: O(1)
=================================================
*/

public class NthDigit {

    public int findNthDigit(int n) {

        long len = 1;      // digit length (1-digit, 2-digit, ...)
        long count = 9;    // how many numbers in this digit length
        long start = 1;    // starting number of current block

        // Step 1: Find the correct digit block
        while (n > len * count) {
            n -= len * count;
            len++;
            count *= 10;
            start *= 10;
        }

        // Step 2: Find exact number
        start += (n - 1) / len;

        // Step 3: Find digit inside number
        String s = Long.toString(start);
        return s.charAt((int)((n - 1) % len)) - '0';
    }

    public static void main(String[] args) {
        NthDigit solution = new NthDigit();

        System.out.println(solution.findNthDigit(11));  // Output: 0
        System.out.println(solution.findNthDigit(3));   // Output: 3
        System.out.println(solution.findNthDigit(15));  // Output: 2
    }
}
