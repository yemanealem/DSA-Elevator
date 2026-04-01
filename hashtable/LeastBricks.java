import java.util.*;

/*
================================= BRICK WALL PROBLEM =================================

Problem:
You are given a wall represented by rows of bricks.
Each row is a list of integers representing brick widths.

You need to draw a vertical line from top to bottom such that:
- The line crosses the fewest number of bricks
- You cannot draw the line on the rightmost edge of the wall

Return the minimum number of bricks that the line crosses.

--------------------------------------------------------------------------------------

Example:
Input:
wall = [
 [1,2,2,1],
 [3,1,2],
 [1,3,2],
 [2,4],
 [3,1,2],
 [1,3,1,1]
]

Output: 2

Explanation:
The optimal line passes through the gaps where the most brick edges align,
so it only crosses 2 bricks.

--------------------------------------------------------------------------------------

How It Works (HashMap + Prefix Sum):

1. For each row, compute prefix sums of brick widths.
   These represent possible "gap positions" between bricks.

2. For each prefix sum (except the last brick in each row):
   - Store how many rows have a gap at that position using a HashMap

3. The best vertical line is where the most rows have gaps aligned.

4. Let:
   maxEdges = maximum number of aligned gaps

   Then:
   Result = total rows - maxEdges

--------------------------------------------------------------------------------------

Key Idea:
Instead of checking all vertical lines, we track where brick edges occur
and choose the position with the maximum overlap.

--------------------------------------------------------------------------------------

Time Complexity: O(N)  (N = total number of bricks)
Space Complexity: O(N)

======================================================================================
*/

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxEdges = 0;

        for (List<Integer> row : wall) {
            int sum = 0;
            int size = row.size();

            for (int i = 0; i < size - 1; i++) {
                sum += row.get(i);

                int count = map.getOrDefault(sum, 0) + 1;
                map.put(sum, count);

                if (count > maxEdges) {
                    maxEdges = count;
                }
            }
        }

        return wall.size() - maxEdges;
    }
}
