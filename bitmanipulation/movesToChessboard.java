class Solution {
    public int movesToChessboard(int[][] board) {
        int n = board.length;

        int rowMask = 0, colMask = 0;

        // Build bitmasks from first row and first column
        for (int i = 0; i < n; i++) {
            rowMask |= (board[0][i] << i);
            colMask |= (board[i][0] << i);
        }

        int reverseRowMask = ((1 << n) - 1) ^ rowMask;
        int reverseColMask = ((1 << n) - 1) ^ colMask;

        int rowCount = 0, colCount = 0;

        for (int i = 0; i < n; i++) {
            int r = 0, c = 0;

            for (int j = 0; j < n; j++) {
                r |= (board[i][j] << j);
                c |= (board[j][i] << j);
            }

            if (r != rowMask && r != reverseRowMask) return -1;
            if (c != colMask && c != reverseColMask) return -1;

            if (r == rowMask) rowCount++;
            if (c == colMask) colCount++;
        }

        int rowSwaps = getSwaps(rowMask, rowCount, n);
        int colSwaps = getSwaps(colMask, colCount, n);

        if (rowSwaps == -1 || colSwaps == -1) return -1;

        return rowSwaps + colSwaps;
    }

    private int getSwaps(int mask, int count, int n) {

        int ones = Integer.bitCount(mask);

        // invalid distribution check
        if (n % 2 == 0) {
            if (ones != n / 2) return -1;
        } else {
            if (Math.abs(n - 2 * ones) != 1) return -1;
        }

        int pattern1 = 0; // 0101...
        int pattern2 = 0; // 1010...

        for (int i = 0; i < n; i++) {
            pattern1 |= (i % 2) << i;
            pattern2 |= ((i + 1) % 2) << i;
        }

        int swaps1 = Integer.bitCount(mask ^ pattern1);
        int swaps2 = Integer.bitCount(mask ^ pattern2);

        int res = Integer.MAX_VALUE;

        if (n % 2 == 0) {
            res = Math.min(swaps1, swaps2);
        } else {
            // choose correct parity pattern
            if (ones * 2 > n) {
                res = swaps1;
            } else {
                res = swaps2;
            }
        }

        return res / 2;
    }
}
