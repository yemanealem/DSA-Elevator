/*
Question:
---------
LeetCode 646 - Maximum Length of Pair Chain

You are given an array of pairs where:

pairs[i] = [lefti, righti]

A pair p2 = [c, d] can follow pair p1 = [a, b] if:

b < c

Return the length of the longest chain that can be formed.

Example:
--------
Input:
pairs = [[1,2],[2,3],[3,4]]

Output:
2

Explanation:
The longest chain is:
[1,2] -> [3,4]


How Greedy Works:
-----------------
1. Sort pairs by their ending value.
2. Always pick the pair with the smallest ending first.
3. If the next pair starts after the current end,
   we can extend the chain.

Why?
----
Choosing the smallest ending leaves more room
for future pairs.

This is similar to the Activity Selection problem.


Time Complexity:
----------------
Sorting: O(n log n)
Traversal: O(n)

Total:
O(n log n)


Space Complexity:
-----------------
O(1)
Ignoring sorting space.
*/

import java.util.Arrays;

public class MaximumLengthOfPairChain {

    public static int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);

        int count = 1;

        int currentEnd = pairs[0][1];

        for (int i = 1; i < pairs.length; i++) {

            if (pairs[i][0] > currentEnd) {

                count++;

                currentEnd = pairs[i][1];
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int[][] pairs = {
                {1, 2},
                {2, 3},
                {3, 4}
        };

        System.out.println(findLongestChain(pairs));
    }
}