
/*
LeetCode 50. Pow(x, n)

Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).

Example 1:
Input: x = 2.00000, n = 10
Output: 1024.00000

Example 2:
Input: x = 2.10000, n = 3
Output: 9.26100

Example 3:
Input: x = 2.00000, n = -2
Output: 0.25000

Explanation:
2^-2 = 1 / (2^2) = 1 / 4 = 0.25


--------------------------------------
OPTIMIZED RECURSIVE APPROACH
(Binary Exponentiation)
--------------------------------------

Instead of multiplying x n times,
we reduce the problem size by half each time.

If n is even:
    x^n = (x^(n/2)) * (x^(n/2))

If n is odd:
    x^n = x * (x^(n/2)) * (x^(n/2))

Time Complexity: O(log n)
Space Complexity: O(log n)  (recursion stack)

--------------------------------------
TRACE EXAMPLE: x = 2, n = 10
--------------------------------------

myPow(2,10)
→ 10 even
→ half = myPow(2,5)

myPow(2,5)
→ 5 odd
→ half = myPow(2,2)

myPow(2,2)
→ 2 even
→ half = myPow(2,1)

myPow(2,1)
→ 1 odd
→ half = myPow(2,0)

myPow(2,0)
→ return 1

Now go back:

myPow(2,1) = 2 * 1 * 1 = 2
myPow(2,2) = 2 * 2 = 4
myPow(2,5) = 2 * 4 * 4 = 32
myPow(2,10) = 32 * 32 = 1024
*/

class Solution {

    public double myPow(double x, int n) {

        // Handle negative exponent safely
        long N = n;   // convert to long to avoid overflow
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return power(x, N);
    }

    private double power(double x, long n) {

        // Base case
        if (n == 0) {
            return 1;
        }

        double half = power(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return x * half * half;
        }
    }
}
