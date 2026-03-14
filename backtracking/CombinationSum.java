import java.util.*;

public class CombinationSum {

    /*
    ---------------------------------------------------------
    LeetCode 39 - Combination Sum
    ---------------------------------------------------------

    Approach: Backtracking (DFS)

    We explore all possible combinations.

    At each step:
    - Add number to current list
    - Recurse with reduced target
    - Backtrack (remove last element)

    Time Complexity: Exponential (O(2^n))
    Space Complexity: O(target) recursion depth
    ---------------------------------------------------------
    */

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates,
                                  int remaining,
                                  int start,
                                  List<Integer> current,
                                  List<List<Integer>> result) {

        // Base case: valid combination found
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            if (candidates[i] > remaining) continue;

            // Choose
            current.add(candidates[i]);

            // Explore (i, not i+1 because reuse allowed)
            backtrack(candidates, remaining - candidates[i], i, current, result);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }

    // Main method
    public static void main(String[] args) {

        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = combinationSum(candidates, target);

        System.out.println(result);
    }
}
