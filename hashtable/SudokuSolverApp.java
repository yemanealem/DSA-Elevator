
/**
 * Class Name: SudokuSolverApp
 *
 * Problem (from LeetCode):
 * Given a 9x9 Sudoku board, fill the empty cells ('.') so that:
 * 1. Each row contains digits 1–9 without repetition
 * 2. Each column contains digits 1–9 without repetition
 * 3. Each 3x3 sub-box contains digits 1–9 without repetition
 *
 * You must modify the board in-place.
 *
 * ----------------------------------------------------------
 * How It Works:
 *
 * This solution uses BACKTRACKING + OPTIMIZATION
 *
 * 1. We use 3 boolean arrays:
 *    - rows[r][num] → is number used in row r?
 *    - cols[c][num] → is number used in column c?
 *    - boxes[b][num] → is number used in 3x3 box b?
 *
 * 2. These act like "hash tables" with O(1) lookup.
 *
 * 3. Backtracking:
 *    - Find an empty cell ('.')
 *    - Try numbers 1 → 9
 *    - If valid → place it
 *    - Recurse to next cell
 *    - If it fails → undo (backtrack)
 *
 * 4. Continue until the board is completely filled.
 *
 * ----------------------------------------------------------
 * Time Complexity:
 *
 * Worst Case:
 *   O(9^N)
 *   where N = number of empty cells
 *
 * Why?
 *   Each empty cell can try up to 9 possibilities.
 *
 * Practical Performance:
 *   Much faster due to pruning using boolean arrays (O(1) checks).
 *
 * Space Complexity:
 *   O(1) (fixed size 9x9 + helper arrays)
 */
public class SudokuSolverApp {

    // Fast lookup tables (better than HashSet)
    private boolean[][] rows = new boolean[9][10];
    private boolean[][] cols = new boolean[9][10];
    private boolean[][] boxes = new boolean[9][10];

    public void solveSudoku(char[][] board) {

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int num = board[r][c] - '0';
                    int box = getBoxIndex(r, c);

                    rows[r][num] = true;
                    cols[c][num] = true;
                    boxes[box][num] = true;
                }
            }
        }

        // Step 2: Start solving using backtracking
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int r, int c) {

        // If we reach row 9 → solved
        if (r == 9) return true;

        // Move to next row
        if (c == 9) return backtrack(board, r + 1, 0);

        // Skip already filled cells
        if (board[r][c] != '.') {
            return backtrack(board, r, c + 1);
        }

        int box = getBoxIndex(r, c);

        // Try numbers 1–9
        for (int num = 1; num <= 9; num++) {

            // Check if valid (O(1))
            if (rows[r][num] || cols[c][num] || boxes[box][num]) {
                continue;
            }

            // Place number
            board[r][c] = (char) (num + '0');
            rows[r][num] = cols[c][num] = boxes[box][num] = true;

            // Recurse
            if (backtrack(board, r, c + 1)) return true;

            // Undo (backtrack)
            board[r][c] = '.';
            rows[r][num] = cols[c][num] = boxes[box][num] = false;
        }

        return false;
    }

    // Convert (row, col) → box index (0–8)
    private int getBoxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }

    // Main method (entry point)
    public static void main(String[] args) {

        SudokuSolverApp solver = new SudokuSolverApp();

        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        solver.solveSudoku(board);

        // Print solved board
        System.out.println("Solved Sudoku:");
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
