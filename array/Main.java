class IsValidSudoku {
    
    public boolean isValidSudoku(char[][] board) {
        
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                
                char current = board[r][c];
                
                if (current == '.') {
                    continue;
                }
                
                int num = current - '1';   // convert '1'-'9' to 0-8
                int boxIndex = (r / 3) * 3 + (c / 3);
                
                if (rows[r][num] || cols[c][num] || boxes[boxIndex][num]) {
                    return false;
                }
                
                rows[r][num] = true;
                cols[c][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        
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
