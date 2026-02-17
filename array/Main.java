/*
LeetCode 36 - Valid Sudoku

Problem:
Determine if a 9x9 Sudoku board is valid.

Rules:
1. Each row must contain digits 1-9 without repetition.
2. Each column must contain digits 1-9 without repetition.
3. Each of the nine 3x3 sub-boxes must contain digits 1-9 without repetition.
4. The board may contain '.' which represents empty cells.
5. The board does NOT need to be solvable — just valid so far.
*/

class IsValidSudoku {
    
    public boolean isValidSudoku(char[][] board) {
        
        // rows[r][num] -> checks if number already exists in row r
        boolean[][] rows = new boolean[9][9];
        
        // cols[c][num] -> checks if number already exists in column c
        boolean[][] cols = new boolean[9][9];
        
        // boxes[b][num] -> checks if number already exists in 3x3 box b
        boolean[][] boxes = new boolean[9][9];
        
        // Traverse every cell
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                
                char current = board[r][c];
                
                // Skip empty cells
                if (current == '.') {
                    continue;
                }
                
                // Convert char '1'-'9' to index 0-8
                int num = current - '1';
                
                // Calculate 3x3 box index
                int boxIndex = (r / 3) * 3 + (c / 3);
                
                // If number already exists → invalid
                if (rows[r][num] || cols[c][num] || boxes[boxIndex][num]) {
                    return false;
                }
                
                // Mark number as seen
                rows[r][num] = true;
                cols[c][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        
        // If no duplicates found
        return true;
    }
}


public class Main {
    public static void main(String[] args) {
        
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

        IsValidSudoku checker = new IsValidSudoku();
        boolean result = checker.isValidSudoku(board);

        System.out.println("Is Valid Sudoku? " + result);
    }
}
