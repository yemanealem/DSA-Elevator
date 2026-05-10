import java.util.*;

/*
LeetCode 85. Maximal Rectangle

Problem:
Given a rows x cols binary matrix filled with '0's and '1's,
find the largest rectangle containing only 1's and return its area.

How It Works:
1. Treat each row as the base of a histogram.
2. Build heights[]:
   - If matrix[row][col] == '1', increase height.
   - Else reset height to 0.
3. For every row:
   - Convert it into a histogram.
   - Use Largest Rectangle in Histogram algorithm.
4. Keep track of the maximum area.

Example Histogram:
Row: 1 1 1 1 1
Heights: [3,1,3,2,2]

Then compute largest rectangle in histogram.

Time Complexity:
- O(rows * cols)

Space Complexity:
- O(cols)

This is the optimal solution.
*/

public class MaximalRectangle {

    public static int maximalRectangle(char[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] heights = new int[cols];

        int maxArea = 0;

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                if (matrix[r][c] == '1') {
                    heights[c] += 1;
                } else {
                    heights[c] = 0;
                }
            }

            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private static int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;

        for (int i = 0; i <= heights.length; i++) {

            int currentHeight = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty()
                    && currentHeight < heights[stack.peek()]) {

                int height = heights[stack.pop()];

                int width;

                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }

                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

  
    public static void main(String[] args) {

        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        int result = maximalRectangle(matrix);

        System.out.println("Max Rectangle Area: " + result);
    }
}