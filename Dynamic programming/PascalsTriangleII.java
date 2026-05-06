import java.util.*;

class Solution {

    /*
     * PROBLEM: Pascal's Triangle II
     * --------------------------------------------
     * Return the rowIndex-th row of Pascal's Triangle.
     *
     * HOW IT WORKS:
     * We build the row iteratively using in-place DP.
     * Each value depends on previous row values.
     *
     * Key optimization:
     * - Use fixed-size list initialized with 1s
     * - Update from right to left
     *
     * TIME COMPLEXITY:  O(n^2)
     * SPACE COMPLEXITY: O(n)
     */

    public List<Integer> getRow(int rowIndex) {

        List<Integer> row = new ArrayList<>(Collections.nCopies(rowIndex + 1, 1));

        for (int i = 1; i < rowIndex; i++) {

            int prev = 1; // row[0] is always 1

            for (int j = 1; j <= i; j++) {

                int current = row.get(j);

                row.set(j, current + prev);

                prev = current;
            }
        }

        return row;
    }
}