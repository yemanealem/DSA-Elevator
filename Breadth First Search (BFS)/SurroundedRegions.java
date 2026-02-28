import java.util.ArrayDeque;

public class SurroundedRegions {

    /*
     * LeetCode 130 - Surrounded Regions
     *
     * Approach:
     * 1. Push all border 'O' into a stack.
     * 2. Mark them as safe ('#') using DFS (iterative).
     * 3. Flip remaining 'O' to 'X'.
     * 4. Restore '#' back to 'O'.
     *
     * Why this works:
     * Only 'O' not connected to border are surrounded.
     *
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n) worst case (stack)
     */

    public static void solve(char[][] board) {

        int m = board.length;
        if (m == 0) return;

        int n = board[0].length;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // Step 1: Add border 'O' cells to stack and mark as safe '#'

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = '#';
                stack.push(i * n);
            }
            if (board[i][n - 1] == 'O') {
                board[i][n - 1] = '#';
                stack.push(i * n + (n - 1));
            }
        }

        for (int j = 1; j < n - 1; j++) { // avoid double corner check
            if (board[0][j] == 'O') {
                board[0][j] = '#';
                stack.push(j);
            }
            if (board[m - 1][j] == 'O') {
                board[m - 1][j] = '#';
                stack.push((m - 1) * n + j);
            }
        }

        // Step 2: DFS using stack
        while (!stack.isEmpty()) {

            int cell = stack.pop();
            int r = cell / n;
            int c = cell % n;

            // Check 4 directions

            // Up
            if (r > 0 && board[r - 1][c] == 'O') {
                board[r - 1][c] = '#';
                stack.push((r - 1) * n + c);
            }

            // Down
            if (r < m - 1 && board[r + 1][c] == 'O') {
                board[r + 1][c] = '#';
                stack.push((r + 1) * n + c);
            }

            // Left
            if (c > 0 && board[r][c - 1] == 'O') {
                board[r][c - 1] = '#';
                stack.push(r * n + c - 1);
            }

            // Right
            if (c < n - 1 && board[r][c + 1] == 'O') {
                board[r][c + 1] = '#';
                stack.push(r * n + c + 1);
            }
        }

        // Step 3: Flip surrounded and restore safe
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i][j] == 'O') {
                    board[i][j] = 'X'; // surrounded
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O'; // restore safe
                }
            }
        }
    }
}
