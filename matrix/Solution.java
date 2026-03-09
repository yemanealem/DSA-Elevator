import java.util.*;

public class Solution {

    // The Game of Life method
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        // Directions: 8 neighbors
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        // First pass: mark cells that will change state
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int liveNeighbors = 0;

                // Count live neighbors
                for (int[] dir : directions) {
                    int r = row + dir[0];
                    int c = col + dir[1];
                    if (r >= 0 && r < m && c >= 0 && c < n) {
                        if (board[r][c] == 1 || board[r][c] == -1) { // originally live
                            liveNeighbors++;
                        }
                    }
                }

                // Rule 1 or 3: live cell dies
                if (board[row][col] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = -1; // live → dead
                }

                // Rule 4: dead cell becomes live
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 2; // dead → live
                }
            }
        }

        // Second pass: finalize the board
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1; // live
                } else {
                    board[row][col] = 0; // dead
                }
            }
        }
    }

    // Helper to print the board
    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Main method to test
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] board = {
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
        };

        System.out.println("Original Board:");
        printBoard(board);

        solution.gameOfLife(board);

        System.out.println("\nNext Generation Board:");
        printBoard(board);
    }
}
