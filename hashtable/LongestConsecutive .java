import java.util.HashSet;

/*
LeetCode 128: Longest Consecutive Sequence
Problem: Given an unsorted integer array, return the length of the longest consecutive elements sequence.
Constraint: Must run in O(n) time.

Optimized Approach:
- Add all numbers to a HashSet (removes duplicates automatically).
- Iterate through the set (unique numbers only).
- Only start counting from a number that is the start of a sequence (num-1 not in set).
- Extend sequence forward using while loop.
- Track max length.

Trace Example:
nums = [100, 4, 200, 1, 3, 2]
1. Add numbers to set: {1,2,3,4,100,200}
2. num=100, 99 not in set → length=1
3. num=4, 3 in set → skip
4. num=200, 199 not in set → length=1
5. num=1, 0 not in set → 1,2,3,4 → length=4
6. Max length=4
*/

public class LongestConsecutive {

    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num); // remove duplicates automatically
        }

        int maxLength = 0;

        // iterate over unique numbers only
        for (int num : set) {
            if (!set.contains(num - 1)) { // start of a sequence
                int currentNum = num;
                int length = 1;

                while (set.contains(currentNum + 1)) { // extend sequence
                    currentNum++;
                    length++;
                }

                maxLength = Math.max(maxLength, length); // update max
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums1)); // 4

        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutive(nums2)); // 9

        int[] nums3 = {1,2,0,1};
        System.out.println(longestConsecutive(nums3)); // 3
    }
}
