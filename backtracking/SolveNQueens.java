/*
LeetCode: N-Queens

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a board configuration of the n-queens' placement,
where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:
Input: n = 4
Output:
[
 [".Q..",
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",
  "Q...",
  "...Q",
  ".Q.."]
]

Time Complexity: O(N!)
Space Complexity: O(N)
*/

import java.util.*;

class Solution {

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> result = new ArrayList<>();

        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // row - col + n
        boolean[] diag2 = new boolean[2 * n]; // row + col

        backtrack(0, board, result, cols, diag1, diag2, n);

        return result;
    }

    private void backtrack(int row,
                           char[][] board,
                           List<List<String>> result,
                           boolean[] cols,
                           boolean[] diag1,
                           boolean[] diag2,
                           int n) {

        // ✅ Base Case: all queens placed
        if (row == n) {
            result.add(construct(board));
            return;
        }

        for (int col = 0; col < n; col++) {

            int d1 = row - col + n;  // main diagonal
            int d2 = row + col;      // anti-diagonal

            // ❌ If column or diagonal already used → skip
            if (cols[col] || diag1[d1] || diag2[d2])
                continue;

            // 🔥 Place Queen
            board[row][col] = 'Q';
            cols[col] = diag1[d1] = diag2[d2] = true;

            /*
            TRACE Example (n = 4)

            Row 0 → place at col 1
            Row 1 → place at col 3
            Row 2 → place at col 0
            Row 3 → place at col 2

            Valid board found.
            */

            backtrack(row + 1, board, result, cols, diag1, diag2, n);

            // 🔁 Backtrack (remove queen)
            board[row][col] = '.';
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }

    private List<String> construct(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }
}
