/*
===========================================================
📌 PROBLEM: Super Ugly Number

A super ugly number is a positive integer whose prime factors
are only in the given array "primes".

You are given:
- n → find the nth super ugly number
- primes[] → allowed prime factors

Return the nth super ugly number.

Example:
Input:
    n = 12
    primes = [2, 7, 13, 19]

Output:
    32

Sequence:
    1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32

===========================================================
🧠 HOW IT WORKS (IDEA)

We start from 1 and generate numbers using:
    next = prime × previous ugly number

We maintain:
- dp[] → stores ugly numbers
- index[] → pointer for each prime
- value[] → next candidate values

At each step:
1. Pick smallest value from value[]
2. Add it to dp[]
3. Move pointer(s) that produced it
4. Update new candidate

===========================================================
⏱ TIME COMPLEXITY

Time:  O(n × k)
Space: O(n + k)

where:
n = required ugly number index
k = number of primes

===========================================================
*/

public class SuperUglyNumber {

    public static int nthSuperUglyNumber(int n, int[] primes) {

        int k = primes.length;

        long[] dp = new long[n];     // store results
        int[] index = new int[k];    // pointers
        long[] value = new long[k];  // candidates

        dp[0] = 1;

        // initialize candidate values
        for (int i = 0; i < k; i++) {
            value[i] = primes[i];
        }

        for (int i = 1; i < n; i++) {

            // find minimum
            long min = value[0];
            for (int j = 1; j < k; j++) {
                min = Math.min(min, value[j]);
            }

            dp[i] = min;

            // update all primes that match min
            for (int j = 0; j < k; j++) {
                if (value[j] == min) {
                    index[j]++;
                    value[j] = dp[index[j]] * primes[j];
                }
            }
        }

        return (int) dp[n - 1];
    }

    public static void main(String[] args) {

        int n = 12;
        int[] primes = {2, 7, 13, 19};

        System.out.println(nthSuperUglyNumber(n, primes));
    }
}