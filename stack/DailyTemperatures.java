import java.util.Arrays;

/*
Problem: Daily Temperatures (LeetCode #739)

Description:
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you 
how many days you would have to wait until a warmer temperature. If there is no future day for which 
this is possible, put 0 instead.

Example:
Input:  T = [73, 74, 75, 71, 69, 72, 76, 73]
Output:     [1, 1, 4, 2, 1, 1, 0, 0]

Approach: Optimized Monotonic Stack
- Use an int array as a stack to store indices of temperatures.
- Traverse temperatures from left to right.
- While current temperature is greater than the temperature at top of the stack:
    - Pop the index from the stack.
    - Calculate days to wait: current index - popped index.
- Push current index onto the stack.
- Indices left in the stack at the end have 0 (no warmer day).

Time Complexity: O(n) -> Each index pushed and popped at most once
Space Complexity: O(n) -> Stack stores indices

Step-by-step Trace:

T = [73, 74, 75, 71, 69, 72, 76, 73]

Stack stores indices (top points to last element)

Iteration trace:

i=0, T[i]=73, Stack=[], push 0 -> Stack=[0]
i=1, T[i]=74, Stack=[0], 74>73 -> pop 0, res[0]=1, push 1 -> Stack=[1]
i=2, T[i]=75, Stack=[1], 75>74 -> pop 1, res[1]=1, push 2 -> Stack=[2]
i=3, T[i]=71, Stack=[2], 71<75 -> push 3 -> Stack=[2,3]
i=4, T[i]=69, Stack=[2,3], 69<71 -> push 4 -> Stack=[2,3,4]
i=5, T[i]=72, Stack=[2,3,4]
       72>69 -> pop 4, res[4]=1
       72>71 -> pop 3, res[3]=2
       72<75 -> push 5 -> Stack=[2,5]
i=6, T[i]=76, Stack=[2,5]
       76>72 -> pop 5, res[5]=1
       76>75 -> pop 2, res[2]=4
       Stack empty -> push 6 -> Stack=[6]
i=7, T[i]=73, Stack=[6], 73<76 -> push 7 -> Stack=[6,7]

Final res = [1,1,4,2,1,1,0,0]
*/

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        int[] stack = new int[n]; // stack stores indices
        int top = -1; // stack pointer

        for (int i = 0; i < n; i++) {
            // Pop all indices with temperatures less than current temperature
            while (top >= 0 && T[i] > T[stack[top]]) {
                int idx = stack[top--]; // pop index
                res[idx] = i - idx;     // calculate days until warmer temperature
            }
            stack[++top] = i; // push current index
        }

        return res;
    }

    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = solution.dailyTemperatures(T);
        System.out.println(Arrays.toString(result)); // Output: [1, 1, 4, 2, 1, 1, 0, 0]
    }
}
