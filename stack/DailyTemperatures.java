import java.util.Arrays;

/*
Problem: Daily Temperatures (LeetCode #739)
Optimized monotonic stack approach using an int array as stack for faster performance.
Time Complexity: O(n)
Space Complexity: O(n)
*/

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        int[] stack = new int[n]; // stack stores indices
        int top = -1; // stack pointer

        for (int i = 0; i < n; i++) {
            // While current temperature > temperature at top index in stack
            while (top >= 0 && T[i] > T[stack[top]]) {
                int idx = stack[top--]; // pop index
                res[idx] = i - idx;     // days until warmer temperature
            }
            stack[++top] = i; // push current index
        }

        return res;
    }

    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = solution.dailyTemperatures(T);
        System.out.println(Arrays.toString(result));
    }
}
