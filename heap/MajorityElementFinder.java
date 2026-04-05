import java.util.*;

/*
📌 Problem: Majority Element II (LeetCode 229)

Given an integer array nums of size n, return all elements that appear more than ⌊ n/3 ⌋ times.

🔹 Key Insight:
- At most 2 elements can appear more than n/3 times.
- Because if there were 3 such elements, total count would exceed n.

------------------------------------------------------------

🧠 Approach: Boyer-Moore Voting Algorithm (Optimized)

We maintain:
- 2 candidates (candidate1, candidate2)
- 2 counters (count1, count2)

🔹 Step 1: Find potential candidates
For each number:
1. If it matches candidate1 → increment count1
2. Else if it matches candidate2 → increment count2
3. Else if count1 == 0 → assign candidate1
4. Else if count2 == 0 → assign candidate2
5. Else → decrement both counts

👉 This "cancels out" non-majority elements.

------------------------------------------------------------

🔹 Step 2: Validate candidates
- Count occurrences of candidate1 and candidate2 again
- Add to result if count > n/3

------------------------------------------------------------

⏱ Time Complexity:
- O(n) → single pass + validation pass

📦 Space Complexity:
- O(1) → only a few variables used

------------------------------------------------------------

✅ Example:
Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
*/

public class MajorityElementFinder {

    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;

        // Step 1: Find candidates
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Step 2: Validate candidates
        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }

        List<Integer> result = new ArrayList<>();
        int threshold = nums.length / 3;

        if (count1 > threshold) result.add(candidate1);
        if (count2 > threshold) result.add(candidate2);

        return result;
    }

    // 🔹 Main method
    public static void main(String[] args) {
        MajorityElementFinder finder = new MajorityElementFinder();

        int[] nums = {1, 1, 1, 3, 3, 2, 2, 2};

        System.out.println("Input: " + Arrays.toString(nums));

        List<Integer> result = finder.majorityElement(nums);

        System.out.println("Majority Elements (> n/3): " + result);
    }
}
