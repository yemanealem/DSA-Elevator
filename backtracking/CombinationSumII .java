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

/*
Explanation:

1. Sort candidates: [1, 1, 2, 5, 6, 7, 10]
2. Start backtracking from index 0 with target 8.
3. Build combinations by adding numbers to path.
4. Skip duplicates to avoid repeated combinations.
5. Stop recursion when target < 0 or index out of bounds.

Trace Example:
- Start with [] target=8
- Pick 1 (index 0) → path=[1] target=7
  - Pick 1 (index 1) → path=[1,1] target=6
    - Pick 2 (index 2) → path=[1,1,2] target=4 ...
    - Pick 5 (index 3) → path=[1,1,5] target=1 ...
    - Pick 6 (index 4) → path=[1,1,6] target=0 → add to result
  - Pick 2 (index 2) → path=[1,2] target=5 ...
  - Pick 5 (index 3) → path=[1,5] target=2 ...
  - Pick 6 (index 4) → path=[1,6] target=1 ...
  - Pick 7 (index 5) → path=[1,7] target=0 → add to result

Time Complexity: O(2^n) in worst case (all subsets)
Space Complexity: O(target) recursion + O(n) path
*/
