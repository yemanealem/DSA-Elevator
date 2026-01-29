public class MinimumCostToMoveChips {

    /*
     * LeetCode 1217: Minimum Cost to Move Chips to the Same Position
     *
     * You are given an integer array position where position[i] is the position
     * of the i-th chip.
     *
     * In one move, you can:
     * 1. Move a chip by two positions to the left or right with cost = 0
     * 2. Move a chip by one position to the left or right with cost = 1
     *
     * Return the minimum cost needed to move all the chips to the same position.
     *
     * Key Insight (Greedy):
     * - Moving by 2 costs nothing → chips can move freely within same parity
     * - Only moves between even ↔ odd positions cost 1
     * - Therefore, count how many chips are on even positions and odd positions
     * - Move the smaller group to the other → minimum cost
     */

    public static int minCostToMoveChips(int[] position) {
        int even = 0;
        int odd = 0;

        // Count chips on even and odd positions
        for (int p : position) {
            if ((p & 1) == 0) {
                even++;
            } else {
                odd++;
            }
        }

        // Minimum cost is moving the smaller group
        return Math.min(even, odd);
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        int[] position = {1, 2, 3};

        int result = minCostToMoveChips(position);

        System.out.println("Minimum cost to move all chips: " + result);
    }
}
