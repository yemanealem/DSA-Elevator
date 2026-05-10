import java.util.*;

/*
LeetCode 85. Maximal Rectangle

Problem:
Given a rows x cols binary matrix filled with '0's and '1's,
find the largest rectangle containing only 1's and return its area.

----------------------------------------------------
OPTIMIZED DYNAMIC PROGRAMMING + MONOTONIC STACK
----------------------------------------------------

Key Idea:
For every row:
1. Build histogram heights.
2. Solve Largest Rectangle in Histogram.

Example:

Matrix Row:
1 1 1 1 1

Heights:
[3,1,3,2,2]

Then compute largest rectangle area in histogram.

----------------------------------------------------
WHY THIS IS FAST
----------------------------------------------------

This solution is optimized because:

1. Uses primitive int[] arrays only
   -> Faster than collections.

2. Uses ArrayDeque instead of Stack
   -> Stack is synchronized and slower.

3. Reuses the same stack object
   -> Less memory allocation.

4. Single-pass histogram processing
   -> O(cols)

----------------------------------------------------
TIME COMPLEXITY
----------------------------------------------------

O(rows * cols)

Each cell is processed once.

----------------------------------------------------
SPACE COMPLEXITY
----------------------------------------------------

O(cols)

For heights[] and stack.

----------------------------------------------------
HOW THE STACK WORKS
----------------------------------------------------

We maintain increasing heights.

When current height becomes smaller:
- Pop taller bars
- Calculate area immediately

Width calculation:

If stack empty:
    width = currentIndex

Else:
    width = currentIndex - stackTop - 1

----------------------------------------------------
*/

public class MaximalRectangle {

    public static int maximalRectangle(char[][] matrix) {

        // Edge case
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

     
        int[] heights = new int[cols];

        int maxArea = 0;

      
        ArrayDeque<Integer> stack = new ArrayDeque<>();

     
        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

             
                heights[c] = (matrix[r][c] == '1')
                        ? heights[c] + 1
                        : 0;
            }

            maxArea = Math.max(
                    maxArea,
                    largestRectangle(heights, stack)
            );
        }

        return maxArea;
    }

    private static int largestRectangle(
            int[] heights,
            ArrayDeque<Integer> stack
    ) {

        stack.clear();

        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {

            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty()
                    && heights[stack.peek()] > currentHeight) {

                int height = heights[stack.pop()];

                int width = stack.isEmpty()
                        ? i
                        : i - stack.peek() - 1;

                int area = height * width;

                if (area > maxArea) {
                    maxArea = area;
                }
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

        System.out.println("Maximum Rectangle Area = " + result);
    }
}