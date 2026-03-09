import java.util.*;

/**
 * LeetCode Problem: Spiral Matrix
 * 
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * 
 * Example:
 * Input:
 * [
 *  [1, 2, 3],
 *  [4, 5, 6],
 *  [7, 8, 9]
 * ]
 * Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
 * 
 * Approach:
 * - Maintain four boundaries: top, bottom, left, right
 * - Traverse the matrix in this order:
 *   1. Top row (left to right)
 *   2. Right column (top to bottom)
 *   3. Bottom row (right to left)
 *   4. Left column (bottom to top)
 * - After each traversal, shrink the boundary inward
 * - Repeat until all elements are visited
 * 
 * Time Complexity: O(m * n) - each element is visited once
 * Space Complexity: O(1) extra space (output list not counted)
 */
public class SpiralMatrix {

    // Method to return elements in spiral order
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;  // edge case: empty matrix

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse top row from left to right
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++; // shrink top boundary

            // Traverse right column from top to bottom
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // shrink right boundary

            // Traverse bottom row from right to left, if still valid
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--; // shrink bottom boundary
            }

            // Traverse left column from bottom to top, if still valid
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // shrink left boundary
            }
        }

        return result;
    }

    // Main method to test the spiral order
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        List<Integer> spiral = spiralOrder(matrix);
        System.out.println("Spiral Order: " + spiral); // Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
    }
}
