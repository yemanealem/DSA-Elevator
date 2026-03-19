/*
Problem: Daily Temperatures (LeetCode #739)

Description:
Given a list of daily temperatures `T`, return a list such that, for each day in the input, tells you **how many days you would have to wait until a warmer temperature**. 
If there is no future day for which this is possible, put 0 instead.

Example:
Input: T = [73, 74, 75, 71, 69, 72, 76, 73]
Output: [1, 1, 4, 2, 1, 1, 0, 0]

Explanation:
- Day 0: Temp=73 → next warmer day is day 1 (74) → answer 1
- Day 1: Temp=74 → next warmer day is day 2 (75) → answer 1
- Day 2: Temp=75 → next warmer day is day 6 (76) → answer 4
- Day 3: Temp=71 → next warmer day is day 5 (72) → answer 2
- Day 4: Temp=69 → next warmer day is day 5 (72) → answer 1
- Day 5: Temp=72 → next warmer day is day 6 (76) → answer 1
- Day 6: Temp=76 → no warmer day → answer 0
- Day 7: Temp=73 → no warmer day → answer 0

Approach: Monotonic Stack
- Use a **stack to keep indices** of temperatures.
- Traverse from left to right.
- While the current temperature is higher than the temperature at the top index of the stack:
    - Pop the index from the stack.
    - The number of days to wait = current index - popped index.
- Push the current index onto the stack.
- At the end, indices left in the stack have 0 (no warmer day).

Time Complexity: O(n) -> Each index is pushed and popped at most once
Space Complexity: O(n) -> Stack to store indices
*/

import java.util.*;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = 0; i < n; i++) {
            // Pop all indices with temperatures less than current temperature
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int idx = stack.pop();
                res[idx] = i - idx; // days until warmer temperature
            }
            stack.push(i); // push current index
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
