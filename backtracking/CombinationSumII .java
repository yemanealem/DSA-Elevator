/*
LeetCode 40: Combination Sum II (Backtracking Solution)

Problem:
- Given an array of candidate numbers and a target number, find all unique combinations in candidates where the numbers sum to target.
- Each number in candidates may only be used once.
- The solution set must not contain duplicate combinations.

Example:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
 [1,1,6],
 [1,2,5],
 [1,7],
 [2,6]
]

Approach (Backtracking):
1. Sort the candidates array to handle duplicates easily.
2. Use a recursive backtracking function:
   - Track current combination (path) and remaining target.
   - Iterate through candidates starting from index 'start'.
   - Skip duplicates: if candidates[i] == candidates[i-1], continue.
   - If candidate > target, break (prune branch).
   - Add candidate to path, recurse with updated target and next index (i+1).
   - Remove last element after recursion (backtrack).
3. Add path to result when target == 0.

Time Complexity: O(2^n) in worst case (all subsets)
Space Complexity: O(target) recursion depth + O(n) path storage

Trace Example:
- Sort candidates: [1,1,2,5,6,7,10]
- Start with [] target=8
- Pick 1 (index 0) → path=[1], target=7
    - Pick 1 (index 1) → path=[1,1], target=6
        - Pick 6 (index 4) → path=[1,1,6], target=0 → add to result
    - Pick 2 (index 2) → path=[1,2], target=5 ...
    - Pick 5 (index 3) → path=[1,5], target=2 ...
    - Pick 7 (index 5) → path=[1,7], target=0 → add to result
- Continue recursion for remaining numbers...
*/

import java.util.*;

public class CombinationSumII {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // sort to handle duplicates
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int target, int start,
                                  List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path)); // found a valid combination
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // skip duplicates
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            if (candidates[i] > target) break; // pruning

            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, path, result); // i+1 because each number used once
            path.remove(path.size() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        List<List<Integer>> combinations = combinationSum2(candidates, target);
        System.out.println("Unique combinations summing to " + target + ":");
        for (List<Integer> comb : combinations) {
            System.out.println(comb);
        }
    }
}
