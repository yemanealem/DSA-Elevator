public class Search2DMatrix {

    /**
     * Search for target in a 2D matrix using binary search.
     * @param matrix: sorted 2D matrix
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
                return true; // found target
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

        int target = 3;
        System.out.println(searchMatrix(matrix, target)); // true

        target = 13;
        System.out.println(searchMatrix(matrix, target)); // false
    }
}

/*
Explanation:

1. Treat the 2D matrix as a flattened 1D sorted array.
2. Use binary search:
   - left = 0, right = m*n - 1
   - mid = (left + right)/2
   - Convert mid → 2D indices: row = mid/n, col = mid%n
3. Compare matrix[row][col] with target.
   - If equal → return true
   - If less → search right
   - If more → search left
4. Time Complexity: O(log(m*n))
5. Space Complexity: O(1)
*/
