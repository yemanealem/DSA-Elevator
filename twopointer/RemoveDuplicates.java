class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n; 

        int i = 2; // slow pointer: start writing from index 2

        for (int j = 2; j < n; j++) { // fast pointer scans from index 2
            if (nums[j] != nums[i - 2]) {
                nums[i] = nums[j];
                i++;
            }
        }

        return i; // new length
    }
}