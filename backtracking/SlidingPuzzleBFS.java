/*
LeetCode: Sliding Puzzle (2x3 Board)

Problem:
Given a 2x3 board with numbers 0-5 (0 = empty), you can move the empty tile (0) 
up, down, left, or right by swapping with an adjacent number. 

Return the minimum number of moves to reach the target configuration:
    [[1,2,3],
     [4,5,0]]

If it is impossible, return -1.

Example:
Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap 0 and 5 to reach target.

How it works:
1. Convert the 2D board into a string for easy comparison and state tracking.
2. Use BFS to explore all possible board states level by level:
   - Each BFS level represents all states reachable in 'moves' steps.
   - Queue stores states to explore.
   - Set 'visited' ensures we don't revisit the same state.
3. For each state:
   - Find the index of 0 (empty tile).
   - Swap 0 with each valid neighbor to generate next states.
   - Add unseen states to the queue.
4. Stop BFS when we reach the target configuration.
5. Return the number of moves it took, or -1 if impossible.

Optimizations:
- Represent the board as a string → easier state management and quick comparisons.
- Precompute neighbor indices for each tile → avoids recalculating valid moves.
- BFS guarantees minimal moves → no need for DFS/backtracking.
- Time Complexity: O(6!) = O(720) since 2x3 board has 6! possible states
- Space Complexity: O(6!) for queue and visited set
*/

import java.util.*;

public class SlidingPuzzleBFS {
    private final String target = "123450"; // target configuration as string
    // Neighbors for each index (0-5) on 2x3 board
    private final int[][] neighbors = {
        {1,3}, {0,2,4}, {1,5}, {0,4}, {1,3,5}, {2,4}
    };

    public int slidingPuzzle(int[][] board) {
        // Convert 2D board to string
        String start = boardToString(board);

        // BFS queue and visited set
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);

        int moves = 0;

        // BFS: each level = moves
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String state = queue.poll();
                if (state.equals(target)) return moves; // target reached

                int zeroIndex = state.indexOf('0'); // find empty tile
                for (int neighbor : neighbors[zeroIndex]) {
                    String nextState = swap(state, zeroIndex, neighbor);
                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.offer(nextState);
                    }
                }
            }
            moves++;
        }

        return -1; // impossible
    }

    private String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return new String(arr);
    }

    private String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board)
            for (int num : row)
                sb.append(num);
        return sb.toString();
    }

    public static void main(String[] args) {
        SlidingPuzzleBFS sp = new SlidingPuzzleBFS();
        int[][] board = {{1,2,3},{4,0,5}};
        System.out.println(sp.slidingPuzzle(board)); // Output: 1
    }
}
