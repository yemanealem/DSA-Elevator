/*
Question:
---------
LeetCode 650 - 2 Keys Keyboard

You start with one character 'A'.

Allowed operations:
1. Copy All
2. Paste

Return the minimum number of operations
to get exactly n 'A's.


Optimal Mathematical Idea:
--------------------------
The minimum operations equal the sum
of prime factors of n.

Why?
----
If n = a × b

We can:
1. Build a
2. Copy All
3. Paste (b - 1) times

Cost:
a + b

Breaking into smaller factors minimizes operations.


Example:
--------
n = 12

12 = 2 × 2 × 3

Operations:
2 + 2 + 3 = 7


Time Complexity:
----------------
O(sqrt(n))

Because we try divisors only up to sqrt(n).


Space Complexity:
-----------------
O(1)
*/

public class TwoKeysKeyboard {

    public static int minSteps(int n) {

        int operations = 0;

        // Start checking factors from 2
        for (int factor = 2; factor * factor <= n; factor++) {

            // Keep dividing while factor exists
            while (n % factor == 0) {

                operations += factor;

                n /= factor;
            }
        }

        // Remaining prime factor
        if (n > 1) {
            operations += n;
        }

        return operations;
    }

    public static void main(String[] args) {

        int n = 12;

        System.out.println(minSteps(n));
    }
}