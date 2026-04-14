/*
    LeetCode 529 - Minesweeper

    🧩 Problem:
    Given a 2D board:
        'M' = mine
        'E' = empty

    Given a click position:
        - If it's a mine → change to 'X'
        - If it's empty:
            - Count adjacent mines
            - If count > 0 → put number ('1'–'8')
            - If count == 0 → mark 'B' and reveal neighbors

    ------------------------------------------------------------

    💡 How it works (DFS - Flood Fill):

    - Treat board like a graph
    - From clicked cell:
        1. Count adjacent mines
        2. If mines > 0 → stop
        3. Else → explore all 8 directions recursively

    ------------------------------------------------------------

    ⏱️ Time Complexity:
        O(m * n)
        - In worst case, we visit every cell once

    🧠 Space Complexity:
        O(m * n) (recursion stack in worst case)

    ------------------------------------------------------------

    ❗ Not Binary Search:
        - No sorted structure
        - Requires full exploration
*/

public class Minesweeper {

    private static final int[][] directions = {
        {-1, -1}, {-1, 0}, {-1, 1},
        {0, -1},          {0, 1},
        {1, -1},  {1, 0}, {1, 1}
    };

    public static char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0], c = click[1];

        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }

        dfs(board, r, c);
        return board;
    }

    private static void dfs(char[][] board, int r, int c) {
        // Boundary check
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length)
            return;

        if (board[r][c] != 'E') return;

        int mineCount = countMines(board, r, c);

        if (mineCount > 0) {
            // If mines nearby → place number and stop
            board[r][c] = (char)(mineCount + '0');
        } else {
            // No mines → mark as blank and explore neighbors
            board[r][c] = 'B';

            for (int[] dir : directions) {
                dfs(board, r + dir[0], c + dir[1]);
            }
        }
    }

    private static int countMines(char[][] board, int r, int c) {
        int count = 0;

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nc >= 0 &&
                nr < board.length && nc < board[0].length &&
                board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'E','E','E','E','E'},
            {'E','E','M','E','E'},
            {'E','E','E','E','E'},
            {'E','E','E','E','E'}
        };

        int[] click = {3, 0};

        char[][] result = updateBoard(board, click);

        // Print result
        for (char[] row : result) {
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }
}
