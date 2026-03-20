import java.util.HashSet;

/*
LeetCode 128: Longest Consecutive Sequence
Problem: Given an unsorted integer array, return the length of the longest consecutive elements sequence.
Constraint: O(n) time complexity.
Approach:
- Store all numbers in a HashSet for O(1) lookup.
- For each number, check if it is the start of a sequence (num-1 not in set).
- Count the length of consecutive numbers starting from this number.
- Track the maximum length found.
Trace Example:
nums = [100, 4, 200, 1, 3, 2]
1. Add all numbers to set: {1,2,3,4,100,200}
2. num=100, 99 not in set → start sequence → length=1
3. num=4, 3 in set → skip
4. num=200, 199 not in set → start sequence → length=1
5. num=1, 0 not in set → start sequence → 1,2,3,4 → length=4
6. Max length=4
*/

public class LongestConsecutive {

    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxLength = 0;

        for (int num : set) {
            // check if num is the start of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int length = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    length++;
                }

                maxLength = Math.max(maxLength, length);
            }
        }

        return maxLength;
    }

    // Example usage
    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println("Longest consecutive sequence length: " + longestConsecutive(nums1)); // 4

        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};
        System.out.println("Longest consecutive sequence length: " + longestConsecutive(nums2)); // 9
    }
}
