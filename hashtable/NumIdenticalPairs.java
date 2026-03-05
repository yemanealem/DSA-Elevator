class Solution {

    /*
     LeetCode 1512 - Number of Good Pairs

     A pair (i, j) is good if:
       - i < j
       - nums[i] == nums[j]

     Approach:
     We count frequency of each number.
     If a number appears k times,
     number of good pairs = k * (k - 1) / 2.

     Time Complexity: O(n)
     Space Complexity: O(n)
    */

    public int numIdenticalPairs(int[] nums) {

        int[] freq = new int[101]; // constraint: 1 <= nums[i] <= 100
        int count = 0;

        for (int num : nums) {
            count += freq[num]; // existing identical numbers form pairs
            freq[num]++;
        }

        return count;
    }
}
