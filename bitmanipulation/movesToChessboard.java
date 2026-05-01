class Solution {
    public int movesToChessboard(int[][] board) {
        int n = board.length;

        int rowMask = 0, colMask = 0;

        // convert first row and col to bitmask
        for (int i = 0; i < n; i++) {
            rowMask |= (board[0][i] << i);
            colMask |= (board[i][0] << i);
        }

        int reverseRowMask = ((1 << n) - 1) ^ rowMask;
        int reverseColMask = ((1 << n) - 1) ^ colMask;

        int rowCount = 0, colCount = 0;

        for (int i = 0; i < n; i++) {
            int rMask = 0, cMask = 0;

            for (int j = 0; j < n; j++) {
                rMask |= (board[i][j] << j);
                cMask |= (board[j][i] << j);
            }

            // validate rows
            if (rMask != rowMask && rMask != reverseRowMask) return -1;
            if (rMask == rowMask) rowCount++;

            // validate cols
            if (cMask != colMask && cMask != reverseColMask) return -1;
            if (cMask == colMask) colCount++;
        }

        int rowSwaps = getMinSwaps(rowMask, rowCount, n);
        int colSwaps = getMinSwaps(colMask, colCount, n);

        return rowSwaps + colSwaps;
    }

    private int getMinSwaps(int mask, int count, int n) {
        int ones = Integer.bitCount(mask);

        // invalid cases
        if (Math.abs(n - 2 * ones) > 1) return Integer.MAX_VALUE;

        int pattern1 = 0; // 0101...
        int pattern2 = 0; // 1010...

        for (int i = 0; i < n; i++) {
            pattern1 |= (i % 2) << i;
            pattern2 |= ((i + 1) % 2) << i;
        }

        int swaps1 = Integer.bitCount(mask ^ pattern1);
        int swaps2 = Integer.bitCount(mask ^ pattern2);

        if (n % 2 == 1) {
            if (ones * 2 < n) return swaps2 / 2;
            else return swaps1 / 2;
        }

        return Math.min(swaps1, swaps2) / 2;
    }
}
