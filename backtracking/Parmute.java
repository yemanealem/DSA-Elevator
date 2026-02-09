class Solution {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> current,
                           boolean[] used, List<List<Integer>> result) {

        // base case
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current)); // make a copy
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;   // skip used numbers

            used[i] = true;
            current.add(nums[i]);

            backtrack(nums, current, used, result);

            // backtrack
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}
