/*
Question:
Given an integer, swap at most one pair of digits to get the maximum possible number.

How it works:
- Store the last index of each digit (0–9).
- Traverse the number from left to right.
- For each digit, try to find a bigger digit (9 down to current+1)
  that appears later in the number.
- If found, swap and return immediately.

Time Complexity: O(n)
*/

public class MaximumSwap {

    public static int maximumSwap(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        
        // Store last occurrence of each digit
        int[] last = new int[10];
        for (int i = 0; i < digits.length; i++) {
            last[digits[i] - '0'] = i;
        }

        // Try to improve each digit from left to right
        for (int i = 0; i < digits.length; i++) {
            int currentDigit = digits[i] - '0';

            // Check from 9 down to currentDigit + 1
            for (int d = 9; d > currentDigit; d--) {
                if (last[d] > i) {
                    // Swap
                    char temp = digits[i];
                    digits[i] = digits[last[d]];
                    digits[last[d]] = temp;

                    return Integer.parseInt(new String(digits));
                }
            }
        }

        return num;
    }

    public static void main(String[] args) {
        int num = 2736;
        System.out.println(maximumSwap(num)); // Output: 7236
    }
}
