/*
LeetCode Problem: N-Queens

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a board configuration where:
'Q' indicates a queen
'.' indicates an empty space

A queen attacks:
1) Same column
2) Same diagonal (↘ row - col constant)
3) Same anti-diagonal (↙ row + col constant)

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

public class NQueens {

    public static List<List<String>> solveNQueens(int n) {

        List<List<String>> result = new ArrayList<>();

        // Create board
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        // Track used columns and diagonals
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n];  // row - col + n
        boolean[] diag2 = new boolean[2 * n];  // row + col

        backtrack(0, board, result, cols, diag1, diag2, n);

        return result;
    }

    private static void backtrack(int row,
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

            int d1 = row - col + n;  // main diagonal index
            int d2 = row + col;      // anti-diagonal index

            // ❌ If column or diagonal already occupied → skip
            if (cols[col] || diag1[d1] || diag2[d2])
                continue;

            // 🔥 Place Queen
            board[row][col] = 'Q';
            cols[col] = diag1[d1] = diag2[d2] = true;

            /*
            ---------------- TRACE EXAMPLE (n = 4) ----------------

            Step 1:
            Row 0 → Try col 0,1,2,3
            Suppose place at (0,1)

            Board:
            . Q . .
            . . . .
            . . . .
            . . . .

            Step 2:
            Row 1 → Try columns
            Safe position: (1,3)

            Board:
            . Q . .
            . . . Q
            . . . .
            . . . .

            Step 3:
            Row 2 → Safe position: (2,0)

            Board:
            . Q . .
            . . . Q
            Q . . .
            . . . .

            Step 4:
            Row 3 → Safe position: (3,2)

            Board:
            . Q . .
            . . . Q
            Q . . .
            . . Q .

            ✅ Valid solution found!
            -------------------------------------------------------
            */

            backtrack(row + 1, board, result, cols, diag1, diag2, n);

            // 🔁 Backtrack (remove queen)
            board[row][col] = '.';
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }

    private static List<String> construct(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }

    // MAIN METHOD
    public static void main(String[] args) {

        int n = 4;

        List<List<String>> solutions = solveNQueens(n);

        System.out.println("Total Solutions: " + solutions.size());
        System.out.println();

        for (List<String> sol : solutions) {
            for (String row : sol) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
