/*
Question:
Given an integer, swap at most one pair of digits to get the maximum possible number.

How it works:
- Traverse digits from right to left.
- Keep track of the maximum digit seen so far.
- If a smaller digit appears before a larger one, mark it for swap.
- Only perform the best (leftmost) swap.

Time Complexity: O(n)
*/

public class MaximumSwap {

    public static int maximumSwap(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        
        int n = digits.length;
        int maxIndex = n - 1;
        int swapI = -1, swapJ = -1;

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] > digits[maxIndex]) {
                maxIndex = i;
            } else if (digits[i] < digits[maxIndex]) {
                swapI = i;
                swapJ = maxIndex;
            }
        }

        // If we found a swap
        if (swapI != -1) {
            char temp = digits[swapI];
            digits[swapI] = digits[swapJ];
            digits[swapJ] = temp;
        }

        return Integer.parseInt(new String(digits));
    }

    public static void main(String[] args) {
        int num = 2736;
        System.out.println(maximumSwap(num)); // Output: 7236
    }
}
