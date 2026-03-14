import java.util.*;

public class CombinationSum {

    /*
    ---------------------------------------------------------
    LeetCode 39 - Combination Sum
    ---------------------------------------------------------

    Question:
    Given an array of distinct positive integers (candidates)
    and a target integer, return all unique combinations
    where the chosen numbers sum to target.

    Rules:
    - You may use the same number unlimited times.
    - The order of combinations does not matter.

    ---------------------------------------------------------
    HOW IT WORKS (STEP-BY-STEP EXPLANATION)
    ---------------------------------------------------------

    This problem is solved using BACKTRACKING (DFS).

    Idea:
    We try every possible combination recursively.

    At each step:
    1. Choose a number.
    2. Subtract it from the remaining target.
    3. Continue exploring with updated target.
    4. If remaining == 0 → we found a valid combination.
    5. If remaining < 0 → stop (invalid path).
    6. After exploring, remove last element (backtrack)
       to try other possibilities.

    Why we pass "start index"?
    To avoid duplicate combinations.
    Since numbers can be reused, we call recursion
    with the same index (i), not i+1.

    ---------------------------------------------------------
    Example:
    candidates = [2,3,6,7]
    target = 7

    Possible paths:

    []
      ├── [2]
      │     ├── [2,2]
      │     │     ├── [2,2,2] (stop)
      │     │     └── [2,2,3] ✔
      │
      ├── [3]
      │     ├── [3,3]
      │
      └── [7] ✔

    ---------------------------------------------------------
    Time Complexity:
    Exponential (worst case O(2^n))

    Space Complexity:
    O(target) recursion depth
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

        // Base Case 1: valid combination found
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Base Case 2: invalid path
        if (remaining < 0) {
            return;
        }

        // Try each number starting from 'start'
        for (int i = start; i < candidates.length; i++) {

            // Choose current number
            current.add(candidates[i]);

            // Recurse (i, not i+1 because reuse is allowed)
            backtrack(candidates,
                      remaining - candidates[i],
                      i,
                      current,
                      result);

            // Backtrack (undo choice)
            current.remove(current.size() - 1);
        }
    }

    // Main method
    public static void main(String[] args) {

        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = combinationSum(candidates, target);

        System.out.println("All Combinations:");
        System.out.println(result);
    }
}
