import java.util.*;

public class GameOfLife {

    // Directions: 8 neighbors (top-left, top, top-right, left, right, bottom-left, bottom, bottom-right)
    private static final int[][] directions = {
        {-1, -1}, {-1, 0}, {-1, 1},
        {0, -1},          {0, 1},
        {1, -1},  {1, 0}, {1, 1}
    };

    public static void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int liveNeighbors = countLiveNeighbors(board, row, col);

                // Rule 1 or 3: live cell dies
                if (board[row][col] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = -1; // mark as live→dead
                }

                // Rule 4: dead cell becomes live
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 2; // mark as dead→live
                }
            }
        }

        // Finalize the board
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] > 0) board[row][col] = 1;
                else board[row][col] = 0;
            }
        }
    }

    // Count live neighbors (1 or -1 → original live)
    private static int countLiveNeighbors(int[][] board, int row, int col) {
        int count = 0;
        int m = board.length;
        int n = board[0].length;

        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];

            if (r >= 0 && r < m && c >= 0 && c < n) {
                if (board[r][c] == 1 || board[r][c] == -1) count++;
            }
        }
        return count;
    }

    // Helper to print the board
    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Main method to test
    public static void main(String[] args) {
        int[][] board = {
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
        };

        System.out.println("Original Board:");
        printBoard(board);

        gameOfLife(board);

        System.out.println("\nNext Generation Board:");
        printBoard(board);
    }
}
