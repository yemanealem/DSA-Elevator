import java.util.*;

public class Subset{

    /*
     * LeetCode 78 - Subsets
     *
     * Given an integer array nums of unique elements,
     * return all possible subsets (the power set).
     *
     * Approach (Iterative Doubling):
     * 1. Start with an empty subset [[]]
     * 2. For each number:
     *      - Duplicate all existing subsets
     *      - Add the current number to each duplicated subset
     *
     * Example:
     * nums = [1,2,3]
     *
     * Start: [[]]
     * Add 1 → [[], [1]]
     * Add 2 → [[], [1], [2], [1,2]]
     * Add 3 → [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
     *
     * Time Complexity: O(n * 2^n)
     * Space Complexity: O(n * 2^n)
     */

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // start with empty subset

        for (int num : nums) {
            int size = result.size();

            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        List<List<Integer>> result = subsets(nums);

        System.out.println("All subsets:");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }
}
