/*
LeetCode Problem: Partition Equal Subset Sum (Dynamic Programming)

QUESTION
You are given an integer array nums.
Return true if you can partition the array into two subsets such that
the sum of elements in both subsets is equal.

Example 1
Input: nums = [1,5,11,5]
Output: true

Explanation:
Subset1 = [1,5,5] → sum = 11
Subset2 = [11] → sum = 11

Example 2
Input: nums = [1,2,3,5]
Output: false

Explanation:
No way to split the array into two subsets with equal sum.

---------------------------------------------------------

KEY IDEA

If we divide the array into two subsets with equal sum:

Total sum = S

Each subset must equal:
target = S / 2

So the problem becomes:

"Can we find a subset whose sum equals target?"

This is the classic
SUBSET SUM problem.

---------------------------------------------------------

STEP 1 — CHECK TOTAL SUM

Example:
nums = [1,5,11,5]

Total sum:
1 + 5 + 11 + 5 = 22

Target = 22 / 2 = 11

Now we must check:
Is there a subset with sum = 11 ?

---------------------------------------------------------

STEP 2 — DYNAMIC PROGRAMMING TABLE

dp[i] = true
if we can create sum i using some elements.

Initialize:

dp[0] = true
because sum 0 is always possible.

Array size = target + 1

dp = [true,false,false,false,false,false,false,false,false,false,false,false]

---------------------------------------------------------

STEP 3 — PROCESS EACH NUMBER

Process number = 1

dp[1] = true

dp = [T,T,F,F,F,F,F,F,F,F,F,F]

---------------------------------------------------------

Process number = 5

dp[6] = true
dp[5] = true

dp = [T,T,F,F,F,T,T,F,F,F,F,F]

---------------------------------------------------------

Process number = 11

dp[11] = true

dp = [T,T,F,F,F,T,T,F,F,F,F,T]

We already reached target = 11

So answer = true

---------------------------------------------------------

STEP-BY-STEP TRACE

nums = [1,5,11,5]

target = 11

Possible subsets:

1 + 5 + 5 = 11

Subset1 = [1,5,5]
Subset2 = [11]

Equal partition exists → return TRUE

---------------------------------------------------------

TIME COMPLEXITY

O(n * target)

n = number of elements
target = sum / 2

---------------------------------------------------------

SPACE COMPLEXITY

O(target)

---------------------------------------------------------

WHY THIS IS DYNAMIC PROGRAMMING

This problem contains:

1. Overlapping Subproblems
2. Optimal Substructure

Instead of checking all subsets (which is exponential),
we store results in dp array.

---------------------------------------------------------
*/

public class PartitionEqualSubsetSum {

    public static boolean canPartition(int[] nums) {

        int sum = 0;

        for(int num : nums)
            sum += num;

        // if total sum is odd → cannot split equally
        if(sum % 2 != 0)
            return false;

        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];

        dp[0] = true;

        for(int num : nums) {

            for(int i = target; i >= num; i--) {

                dp[i] = dp[i] || dp[i - num];

            }
        }

        return dp[target];
    }

    public static void main(String[] args) {

        int[] nums = {1,5,11,5};

        boolean result = canPartition(nums);

        System.out.println("Can partition into equal subset: " + result);
    }
}
