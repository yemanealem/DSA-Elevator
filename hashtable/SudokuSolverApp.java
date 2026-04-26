
/**
 * Class Name: SudokuSolverApp
 *
 * Description:
 * This program solves a 9x9 Sudoku puzzle using backtracking.
 * It uses optimized boolean arrays (instead of HashSet) to track:
 * - numbers used in each row
 * - numbers used in each column
 * - numbers used in each 3x3 box
 *
 * This ensures O(1) lookup and improves performance significantly.
 *
 * Approach:
 * 1. Initialize tracking arrays from the given board.
 * 2. Use backtracking to fill empty cells.
 * 3. Try digits 1–9 and validate using boolean arrays.
 * 4. If valid → place number and recurse.
 * 5. If stuck → undo (backtrack).
 *
 * Time Complexity:
 * Worst case: O(9^N), where N = empty cells
 */
public class SudokuSolverApp {

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

        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int r, int c) {

        if (r == 9) return true;
        if (c == 9) return backtrack(board, r + 1, 0);

        if (board[r][c] != '.') {
            return backtrack(board, r, c + 1);
        }

        int box = getBoxIndex(r, c);

        for (int num = 1; num <= 9; num++) {

            if (rows[r][num] || cols[c][num] || boxes[box][num]) {
                continue;
            }

            board[r][c] = (char) (num + '0');
            rows[r][num] = cols[c][num] = boxes[box][num] = true;

            if (backtrack(board, r, c + 1)) return true;

        
            board[r][c] = '.';
            rows[r][num] = cols[c][num] = boxes[box][num] = false;
        }

        return false;
    }

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

        // Print result
        System.out.println("Solved Sudoku:");
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
