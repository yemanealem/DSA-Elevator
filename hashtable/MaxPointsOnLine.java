import java.util.*;

public class MaxPointsOnLine {

    // Iterative GCD (faster than recursive)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;

        int result = 0;

        for (int i = 0; i < n; i++) {

            // 🔥 Optimization: early break
            if (result >= n - i) break;

            Map<Long, Integer> map = new HashMap<>();
            int duplicates = 1;
            int max = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                // Same point
                if (dx == 0 && dy == 0) {
                    duplicates++;
                    continue;
                }

                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;

                // Normalize sign (important!)
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    dy = 1;
                } else if (dy == 0) {
                    dx = 1;
                }

                // Encode (dx, dy) into one long
                long key = ((long) dx << 32) | (dy & 0xffffffffL);

                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);

                max = Math.max(max, count);
            }

            result = Math.max(result, max + duplicates);
        }

        return result;
    }

    public static void main(String[] args) {
        MaxPointsOnLine sol = new MaxPointsOnLine();

        int[][] points = {
            {1,1}, {2,2}, {3,3}, {3,4}, {5,6}
        };

        System.out.println(sol.maxPoints(points));
    }
}
