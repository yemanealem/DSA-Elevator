import java.util.Arrays;

public class MatrixClockwiseRotator {

    /*
     * PROBLEM:
     * Given an n x n 2D matrix, rotate it 90 degrees clockwise in-place.
     *
     * CLARIFICATION:
     * - The matrix is square (n x n).
     * - You must modify the matrix directly (no extra 2D matrix).
     * - The rotation direction is clockwise.
     *
     * SOLUTION APPROACH:
     * 1. Transpose the matrix (swap matrix[i][j] with matrix[j][i]).
     * 2. Reverse each row.
     *
     * This two-step process results in a 90-degree clockwise rotation.
     *
     * RUNNING TIME:
     * Time Complexity: O(n^2)
     * Space Complexity: O(1) (in-place)
     */

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;

            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;

                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        MatrixClockwiseRotator rotator = new MatrixClockwiseRotator();
        rotator.rotate(matrix);

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
