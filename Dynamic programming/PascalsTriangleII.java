import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {

    /*
     * PROBLEM: Pascal's Triangle II
     * --------------------------------------------
     * Given an integer rowIndex, return the rowIndex-th row of Pascal's Triangle.
     *
     * Each number in Pascal's Triangle is the sum of the two numbers above it.
     *
     * Example:
     * Input: rowIndex = 3
     * Output: [1, 3, 3, 1]
     *
     * --------------------------------------------
     * HOW IT WORKS (Dynamic Programming):
     * --------------------------------------------
     * We build only one row instead of full triangle.
     *
     * Start with:
     * row = [1]
     *
     * For each new row:
     * - Expand the list
     * - Update values from RIGHT TO LEFT
     *
     * Formula:
     * row[j] = row[j] + row[j - 1]
     *
     * Why right-to-left?
     * → To avoid overwriting values before using them.
     *
     * --------------------------------------------
     * TIME & SPACE COMPLEXITY:
     * --------------------------------------------
     * Time Complexity:  O(n^2)
     * Space Complexity: O(n)
     *
     * where n = rowIndex
     */

    public List<Integer> getRow(int rowIndex) {

        List<Integer> row = new ArrayList<>();

        row.add(1);

        for (int i = 1; i <= rowIndex; i++) {

            row.add(0);

            for (int j = i; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }

        return row;
    }
    
}
