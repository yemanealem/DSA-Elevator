import java.util.*;

public class CutOffTreesOptimized {

    static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    static int rows, cols;

    public static int cutOffTree(List<List<Integer>> forestList) {

        rows = forestList.size();
        cols = forestList.get(0).size();

        // Convert to int[][] for faster access
        int[][] forest = new int[rows][cols];
        List<int[]> trees = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                forest[i][j] = forestList.get(i).get(j);
                if (forest[i][j] > 1) {
                    trees.add(new int[]{forest[i][j], i, j});
                }
            }
        }

        // Sort trees by height
        trees.sort(Comparator.comparingInt(a -> a[0]));

        int totalSteps = 0;
        int sx = 0, sy = 0;

        // Reusable visited array with timestamp trick
        int[][] visited = new int[rows][cols];
        int visitMark = 1;

        for (int[] tree : trees) {
            int steps = bfs(forest, sx, sy, tree[1], tree[2], visited, visitMark++);
            if (steps == -1) return -1;

            totalSteps += steps;
            sx = tree[1];
            sy = tree[2];
        }

        return totalSteps;
    }

    private static int bfs(int[][] forest, int sx, int sy,
                           int tx, int ty,
                           int[][] visited, int mark) {

        if (sx == tx && sy == ty) return 0;

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = mark;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], steps = curr[2];

            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 &&
                    nx < rows && ny < cols &&
                    forest[nx][ny] != 0 &&
                    visited[nx][ny] != mark) {

                    if (nx == tx && ny == ty) {
                        return steps + 1;
                    }

                    visited[nx][ny] = mark;
                    queue.offer(new int[]{nx, ny, steps + 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<List<Integer>> forest = Arrays.asList(
            Arrays.asList(1,2,3),
            Arrays.asList(0,0,4),
            Arrays.asList(7,6,5)
        );

        System.out.println(cutOffTree(forest)); // 6
    }
}
