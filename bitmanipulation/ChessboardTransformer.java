/*
Problem: Transform to Chessboard (LeetCode 782)

Given an n x n binary matrix, you can swap rows or columns.
Return the minimum number of swaps to transform it into a chessboard pattern,
or -1 if impossible.

A valid chessboard alternates like:
010101...
101010...

---------------------------------------
How it works:

1. Validity check:
   - Every cell must match either the base pattern or its inverse.
   - Verified using XOR consistency:
     board[i][j] ^ board[0][0] ^ board[i][0] ^ board[0][j]

2. Structure validation:
   - Only two row/column patterns are allowed.
   - Check balance of 0s and 1s in first row/column.

3. Swap calculation:
   - Compare against ideal alternating pattern (0101... or 1010...).
   - Each swap fixes 2 mismatches → swaps = mismatches / 2.

---------------------------------------
Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class ChessboardTransformer {

    public int movesToChessboard(int[][] board) {
        int n = board.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((board[i][j] ^ board[0][0] ^ board[i][0] ^ board[0][j]) != 0) {
                    return -1;
                }
            }
        }

        int rowSum = 0, colSum = 0;
        int rowSwap = 0, colSwap = 0;

        for (int i = 0; i < n; i++) {
            rowSum += board[0][i];
            colSum += board[i][0];

            if (board[i][0] == i % 2) rowSwap++;
            if (board[0][i] == i % 2) colSwap++;
        }

        if (rowSum < n / 2 || rowSum > (n + 1) / 2) return -1;
        if (colSum < n / 2 || colSum > (n + 1) / 2) return -1;

        if (n % 2 == 0) {
            rowSwap = Math.min(rowSwap, n - rowSwap);
            colSwap = Math.min(colSwap, n - colSwap);
        } else {
            if ((rowSwap & 1) == 1) rowSwap = n - rowSwap;
            if ((colSwap & 1) == 1) colSwap = n - colSwap;
        }

        return (rowSwap + colSwap) / 2;
    }

    public static void main(String[] args) {
        ChessboardTransformer transformer = new ChessboardTransformer();

        int[][] board = {
            {1, 1, 0},
            {0, 0, 1},
            {0, 0, 1}
        };

        System.out.println(transformer.movesToChessboard(board));
    }
}
