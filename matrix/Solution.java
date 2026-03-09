import java.util.*;

/**
 * LeetCode Problem: Game of Life
 *
 * You are given an m x n board representing the Game of Life.
 * Each cell can be:
 *  - 1: live
 *  - 0: dead
 * 
 * Each cell interacts with its 8 neighbors (horizontal, vertical, diagonal) and
 * the next state is determined by these rules:
 *  1. Any live cell with fewer than 2 live neighbors dies (underpopulation).
 *  2. Any live cell with 2 or 3 live neighbors lives on.
 *  3. Any live cell with more than 3 live neighbors dies (overpopulation).
 *  4. Any dead cell with exactly 3 live neighbors becomes a live cell (reproduction).
 * 
 * Task: Update the board to its next state **in-place**.
 *
 * Approach:
 *  - Use two passes:
 *      1. Mark cells that will change using temporary markers:
 *          - -1: live → dead
 *          -  2: dead → live
 *      2. Finalize the board by converting markers to 1 (live) or 0 (dead)
 *  - Use a directions array to check all 8 neighbors efficiently.
 *
 * Time Complexity: O(m * n) → each cell is visited once.
 * Space Complexity: O(1) extra space (in-place, ignoring output).
 */
public class Solution {

    // The Game of Life method
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        // Directions: 8 neighbors (row offset, column offset)
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
                        // -1 is previously live → dead, 1 is live
                        if (board[r][c] == 1 || board[r][c] == -1) {
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

    // Main method to test the Game of Life
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
