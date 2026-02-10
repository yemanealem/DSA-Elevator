
public class Search2DMatrix {

    /*
    LeetCode Problem 74: Search a 2D Matrix

    Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. 
    This matrix has the following properties:

    1. Integers in each row are sorted from left to right.
    2. The first integer of each row is greater than the last integer of the previous row.

    Example 1:
    Input: matrix = [[1,3,5,7],
                     [10,11,16,20],
                     [23,30,34,60]], target = 3
    Output: true

    Example 2:
    Input: matrix = [[1,3,5,7],
                     [10,11,16,20],
                     [23,30,34,60]], target = 13
    Output: false

    Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 100
    -10^4 <= matrix[i][j], target <= 10^4

    Follow up: Can you write a solution with O(log(m * n)) time complexity?
    */

    /**
     * Function to search a target in a 2D matrix using binary search.
     * @param matrix: 2D sorted matrix
     * @param target: element to search
     * @return boolean: true if target exists, false otherwise
     * Time Complexity: O(log(m*n)) → binary search
     * Space Complexity: O(1)
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false; // empty matrix
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0, right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Convert mid to 2D indices
            int row = mid / n;
            int col = mid % n;

            if (matrix[row][col] == target) {
                return true; // target found
            } else if (matrix[row][col] < target) {
                left = mid + 1; // search right
            } else {
                right = mid - 1; // search left
            }
        }

        return false; // target not found
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };

        int target1 = 3;
        System.out.println(searchMatrix(matrix, target1)); // true

        int target2 = 13;
        System.out.println(searchMatrix(matrix, target2)); // false
    }
}

/*
Explanation:

1. Treat the 2D matrix as a flattened 1D sorted array of length m*n.
2. Use standard binary search:
   - left = 0, right = m*n - 1
   - mid = (left + right)/2
   - Convert mid to 2D indices: row = mid / n, col = mid % n
3. Compare matrix[row][col] with target:
   - If equal → return true
   - If less → move left = mid + 1
   - If greater → move right = mid - 1
4. Time Complexity: O(log(m*n)) → binary search on entire matrix
5. Space Complexity: O(1) → no extra space used
6. This solution satisfies the follow-up requirement for O(log(m*n)) complexity.
*/
