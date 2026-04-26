import java.util.*;

class Solution {

    // Declare at class level
    private Set<Character>[] rows = new HashSet[9];
    private Set<Character>[] cols = new HashSet[9];
    private Set<Character>[] boxes = new HashSet[9];

    public void solveSudoku(char[][] board) {

        // Initialize sets
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // Fill sets with existing numbers
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char num = board[r][c];
                if (num != '.') {
                    rows[r].add(num);
                    cols[c].add(num);
                    boxes[getBoxIndex(r, c)].add(num);
                }
            }
        }

        // Start backtracking
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int r, int c) {

        // If reached end → solved
        if (r == 9) return true;

        // Move to next row
        if (c == 9) return backtrack(board, r + 1, 0);

        // Skip filled cells
        if (board[r][c] != '.') {
            return backtrack(board, r, c + 1);
        }

        // Try digits 1–9
        for (char num = '1'; num <= '9'; num++) {

            int box = getBoxIndex(r, c);

            // O(1) check using hash sets
            if (rows[r].contains(num) ||
                cols[c].contains(num) ||
                boxes[box].contains(num)) {
                continue;
            }

            // Place number
            board[r][c] = num;
            rows[r].add(num);
            cols[c].add(num);
            boxes[box].add(num);

            // Recurse
            if (backtrack(board, r, c + 1)) return true;

            // Undo (backtrack)
            board[r][c] = '.';
            rows[r].remove(num);
            cols[c].remove(num);
            boxes[box].remove(num);
        }

        return false;
    }

    private int getBoxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }
}
