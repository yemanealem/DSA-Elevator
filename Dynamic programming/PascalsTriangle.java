import java.util.*;

public class PascalsTriangle {

    /*
     QUESTION:
     ---------
     LeetCode: Pascal's Triangle

     Given an integer numRows, return the first numRows
     of Pascal’s Triangle.

     In Pascal’s Triangle:
       - The first and last element of each row is 1
       - Every other element is the sum of the two
         elements directly above it

     ------------------------------------------------
     DYNAMIC PROGRAMMING IDEA:
     ------------------------------------------------
     Each value depends on previously computed values.

     Rule:
       triangle[row][col] =
         triangle[row-1][col-1] + triangle[row-1][col]

     We build the triangle row by row (bottom-up).

     ------------------------------------------------
     BASE CASE:
     ------------------------------------------------
     Row 0 → [1]

     ------------------------------------------------
     STEP-BY-STEP EXAMPLE (numRows = 5):
     ------------------------------------------------
     Row 0: [1]
     Row 1: [1, 1]
     Row 2: [1, 2, 1]
     Row 3: [1, 3, 3, 1]
     Row 4: [1, 4, 6, 4, 1]

     ------------------------------------------------
     WHY THIS IS DYNAMIC PROGRAMMING:
     ------------------------------------------------
     - Each row uses values from the previous row
     - No recomputation
     - Builds solution incrementally

     ------------------------------------------------
     TIME & SPACE COMPLEXITY:
     ------------------------------------------------
     Time:  O(numRows²)
     Space: O(numRows²)
    */

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {
            List<Integer> currentRow = new ArrayList<>();

            for (int col = 0; col <= row; col++) {
                // First and last element of each row is 1
                if (col == 0 || col == row) {
                    currentRow.add(1);
                } else {
                    int value =
                        triangle.get(row - 1).get(col - 1) +
                        triangle.get(row - 1).get(col);
                    currentRow.add(value);
                }
            }

            triangle.add(currentRow);
        }

        return triangle;
    }

    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> result = generate(numRows);

        for (List<Integer> row : result) {
            System.out.println(row);
        }
    }
}
