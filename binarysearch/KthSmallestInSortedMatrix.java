public class KthSmallestInSortedMatrix {

    /*
     * PROBLEM:
     * Given an n x n matrix where each row and each column is sorted in ascending order,
     * find the kth smallest element in the matrix.
     *
     * Example:
     * matrix = [
     *   [1, 5, 9],
     *   [10, 11, 13],
     *   [12, 13, 15]
     * ]
     * k = 8
     * Output: 13
     *
     * ------------------------------------------------------------
     * HOW IT WORKS (Binary Search on Value Range):
     *
     * 1. The smallest element is at matrix[0][0]
     *    The largest element is at matrix[n-1][n-1]
     *
     * 2. We perform Binary Search between these values (NOT indices):
     *    - Pick mid value
     *    - Count how many elements are <= mid
     *
     * 3. If count < k:
     *       → kth element is larger → search right side
     *    Else:
     *       → kth element is smaller or equal → search left side
     *
     * 4. Continue until low == high
     *
     * ------------------------------------------------------------
     * COUNTING LOGIC (Efficient Trick):
     *
     * Start from bottom-left:
     * - If current <= mid → all elements above are also <= mid
     *                      → add (row + 1) and move right
     * - Else → move up
     *
     * This gives O(n) counting time.
     *
     * ------------------------------------------------------------
     * TIME COMPLEXITY:
     * - Binary search iterations: log(max - min)
     * - Each count operation: O(n)
     *
     * Total Time: O(n * log(max - min))
     *
     * SPACE COMPLEXITY:
     * - O(1) (no extra space used)
     */

    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];

        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countLessEqual(matrix, mid);

            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    // Helper function to count elements <= mid
    private static int countLessEqual(int[][] matrix, int mid) {
        int n = matrix.length;
        int row = n - 1;
        int col = 0;
        int count = 0;

        while (row >= 0 && col < n) {
            if (matrix[row][col] <= mid) {
                count += (row + 1);
                col++;
            } else {
                row--;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 5, 9},
            {10, 11, 13},
            {12, 13, 15}
        };

        int k = 8;
        System.out.println("Kth smallest element: " + kthSmallest(matrix, k));
    }
}
