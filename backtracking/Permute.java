import java.util.ArrayList;
import java.util.List;

/*
QUESTION (LeetCode – Permutations):

Given an array of distinct integers nums,
return all possible permutations.

Example:
Input: nums = [1, 2, 3]
Output:
[
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
]

APPROACH:
- Use backtracking with in-place swapping
- Fix one position at a time
- Swap current index with every possible index
- Backtrack by swapping back
*/

public class Permute {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};

        Solution solution = new Solution();
        List<List<Integer>> result = solution.permute(nums);

        // Print all permutations
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
    }
}

class Solution {

    /*
    TRACE FOR nums = [1,2,3]

    Start: index = 0
    nums = [1,2,3]

    i = 0 → swap(0,0) → [1,2,3]
        index = 1
        i = 1 → swap(1,1) → [1,2,3]
            index = 2
            i = 2 → swap(2,2) → [1,2,3]
                index = 3 → SAVE [1,2,3]
            backtrack → swap(2,2)
        i = 2 → swap(1,2) → [1,3,2]
            index = 2
            i = 2 → swap(2,2)
                index = 3 → SAVE [1,3,2]
            backtrack → swap(2,2)
        backtrack → swap(1,2)

    i = 1 → swap(0,1) → [2,1,3]
        index = 1
        i = 1 → swap(1,1)
            index = 2 → SAVE [2,1,3]
        i = 2 → swap(1,2) → [2,3,1]
            index = 2 → SAVE [2,3,1]

    i = 2 → swap(0,2) → [3,2,1]
        index = 1
        i = 1 → swap(1,1)
            index = 2 → SAVE [3,2,1]
        i = 2 → swap(1,2) → [3,1,2]
            index = 2 → SAVE [3,1,2]

    END
    */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, result);
        return result;
    }

    private void backtrack(int[] nums, int index, List<List<Integer>> result) {

        // Base case: one full permutation is formed
        if (index == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }

        // Try every number for the current index
        for (int i = index; i < nums.length; i++) {

            // 1️⃣ Choose: place nums[i] at current index
            swap(nums, index, i);

            // 2️⃣ Explore: recurse for next index
            backtrack(nums, index + 1, result);

            // 3️⃣ Un-choose (Backtrack): restore array
            swap(nums, index, i);
        }
    }

    // Helper method to swap two elements
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
