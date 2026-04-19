import java.util.*;

public class MaxPointsOnLine {

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public int maxPoints(int[][] points) {
        if (points.length <= 2) return points.length;

        int maxPoints = 0;

        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> map = new HashMap<>();
            int duplicate = 1;
            int currentMax = 0;

            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                if (x1 == x2 && y1 == y2) {
                    duplicate++;
                    continue;
                }

                int dx = x2 - x1;
                int dy = y2 - y1;

                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;

                String slope = dx + "/" + dy;

                map.put(slope, map.getOrDefault(slope, 0) + 1);
                currentMax = Math.max(currentMax, map.get(slope));
            }

            maxPoints = Math.max(maxPoints, currentMax + duplicate);
        }

        return maxPoints;
    }

    public static void main(String[] args) {
        MaxPointsOnLine solution = new MaxPointsOnLine();

        int[][] points = {
            {1,1}, {2,2}, {3,3}, {3,4}, {5,6}
        };

        System.out.println("Max points on a line: " + solution.maxPoints(points));
    }
}
