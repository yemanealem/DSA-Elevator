import java.util.*;

public class SlidingPuzzleBFS {
    private final String target = "123450";
    private final int[][] neighbors = {
        {1,3}, {0,2,4}, {1,5}, {0,4}, {1,3,5}, {2,4}
    };

    public int slidingPuzzle(int[][] board) {
        String start = boardToString(board);
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(start);
        visited.add(start);
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String state = queue.poll();
                if (state.equals(target)) return moves;

                int zeroIndex = state.indexOf('0');
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

        return -1;
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
        for (int[] row : board) for (int num : row) sb.append(num);
        return sb.toString();
    }

    public static void main(String[] args) {
        SlidingPuzzleBFS sp = new SlidingPuzzleBFS();
        int[][] board = {{1,2,3},{4,0,5}};
        System.out.println(sp.slidingPuzzle(board)); // Output: 1
    }
}
