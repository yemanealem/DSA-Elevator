import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class SurroundedRegions {

    public static void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        int m = board.length;
        int n = board[0].length;

        // Run BFS on border cells
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') bfs(board, i, 0, m, n);
            if (board[i][n - 1] == 'O') bfs(board, i, n - 1, m, n);
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') bfs(board, 0, j, m, n);
            if (board[m - 1][j] == 'O') bfs(board, m - 1, j, m, n);
        }

        // Flip surrounded regions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }

    private static void bfs(char[][] board, int row, int col, int m, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(row * n + col);
        board[row][col] = '#';  // mark safe

        while (!queue.isEmpty()) {
            int cell = queue.poll();
            int r = cell / n;
            int c = cell % n;

            // Up
            if (r > 0 && board[r - 1][c] == 'O') {
                board[r - 1][c] = '#';
                queue.offer((r - 1) * n + c);
            }

            // Down
            if (r < m - 1 && board[r + 1][c] == 'O') {
                board[r + 1][c] = '#';
                queue.offer((r + 1) * n + c);
            }

            // Left
            if (c > 0 && board[r][c - 1] == 'O') {
                board[r][c - 1] = '#';
                queue.offer(r * n + c - 1);
            }

            // Right
            if (c < n - 1 && board[r][c + 1] == 'O') {
                board[r][c + 1] = '#';
                queue.offer(r * n + c + 1);
            }
        }
    }

    // Test
    public static void main(String[] args) {
        char[][] board = {
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };

        solve(board);

        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
