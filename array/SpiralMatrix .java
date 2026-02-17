import java.util.*;

/*
LeetCode 54 - Spiral Matrix

Problem:
Given an m x n matrix, return all elements of the matrix in spiral order.

Spiral order means:
1. Left → Right
2. Top → Bottom
3. Right → Left
4. Bottom → Top
Repeat while shrinking boundaries.

Example Trace:

Input matrix:
[
 [1, 2, 3],
 [4, 5, 6],
 [7, 8, 9]
]

Step-by-step spiral traversal:

Initial boundaries:
top = 0, bottom = 2, left = 0, right = 2

1️⃣ Traverse Left → Right (top row)
Add: 1 2 3
top = 1

2️⃣ Traverse Top → Bottom (right column)
Add: 6 9
right = 1

3️⃣ Traverse Right → Left (bottom row)
Add: 8 7
bottom = 1

4️⃣ Traverse Bottom → Top (left column)
Add: 4
left = 1

5️⃣ Remaining layer:
Left → Right (only element left)
Add: 5
Boundaries crossed → stop

Final Spiral Order:
[1, 2, 3, 6, 9, 8, 7, 4, 5]
*/

public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0)
            return result;

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            // 1️⃣ Left → Right (top row)
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // 2️⃣ Top → Bottom (right column)
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // 3️⃣ Right → Left (bottom row)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // 4️⃣ Bottom → Top (left column)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }

    // Default main method
    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        List<Integer> result = spiralOrder(matrix);

        System.out.println("Spiral Order: " + result);
    }
}
