/*
LeetCode 50. Pow(x, n)

Implement pow(x, n), which calculates x raised to the power n (x^n).

-----------------------------------------
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
2^-2 = 1 / (2^2) = 1/4 = 0.25
-----------------------------------------

OPTIMIZED RECURSIVE APPROACH (Binary Exponentiation)

If n is even:
    x^n = (x^(n/2)) * (x^(n/2))

If n is odd:
    x^n = x * (x^(n/2)) * (x^(n/2))

We divide n by 2 each time → O(log n)

-----------------------------------------
TRACE: x = 2, n = 10

myPow(2,10)
→ power(2,10)

10 even
→ half = power(2,5)

5 odd
→ half = power(2,2)

2 even
→ half = power(2,1)

1 odd
→ half = power(2,0)

0 → return 1

Now build back:

power(2,1) = 2 * 1 * 1 = 2
power(2,2) = 2 * 2 = 4
power(2,5) = 2 * 4 * 4 = 32
power(2,10) = 32 * 32 = 1024

-----------------------------------------
Time Complexity: O(log n)
Space Complexity: O(log n)  (recursion stack)
-----------------------------------------
*/

public class PowerNumbe {

    // Main power method
    public double myPow(double x, int n) {

        long N = n;  // convert to long to avoid overflow

        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return power(x, N);
    }

    // Recursive helper
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

    // MAIN METHOD
    public static void main(String[] args) {

        PowerNumbe obj = new PowerNumbe();

        double result1 = obj.myPow(2.0, 10);
        System.out.println("2^10 = " + result1);

        double result2 = obj.myPow(2.0, -2);
        System.out.println("2^-2 = " + result2);

        double result3 = obj.myPow(2.1, 3);
        System.out.println("2.1^3 = " + result3);
    }
}
