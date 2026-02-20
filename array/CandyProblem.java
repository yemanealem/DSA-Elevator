/*
LeetCode 135 – Candy Problem

Problem:
Given an array ratings where ratings[i] is the rating of the i-th child.
- Each child must get at least 1 candy.
- Children with a higher rating than their neighbor must get more candies.
Return the minimum total number of candies required.

Example:
Input: ratings = [1,0,2]
Output: 5
Explanation: Candy distribution: [2,1,2]

Approach:
- Use a two-pass greedy algorithm.
- Left-to-right pass: each child gets more candy than left neighbor if rating is higher.
- Right-to-left pass: use a single variable to track decreasing slope and take max with left-to-right.
- Sum the max candies for each child.

Time Complexity: O(n) → two linear passes through the array.
Space Complexity: O(n) → only one array for left-to-right pass.

Trace Example (ratings = [1,0,2]):

Left-to-right pass:
Index 0: rating=1 → left[0]=1
Index 1: rating=0 → left[1]=1
Index 2: rating=2 → rating>left neighbor? yes → left[2]=left[1]+1=2
Left array after pass: [1,1,2]

Right-to-left pass (using variable 'right'):
Index 2: rating=2 → right=1 → total += max(left[2], right)=max(2,1)=2 → total=2
Index 1: rating=0 → rating<next? yes → right=1 → total += max(left[1], right)=max(1,1)=1 → total=3
Index 0: rating=1 → rating>next? yes → right=right+1=2 → total += max(left[0], right)=max(1,2)=2 → total=5

Final total candies = 5
*/

public class CandyProblem {

    public static int candy(int[] ratings) {
        int n = ratings.length;
        if (n == 0) return 0;

        int[] left = new int[n];
        for (int i = 0; i < n; i++) left[i] = 1;

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }

        int total = 0;
        int right = 1;

        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else if (i < n - 1 && ratings[i] <= ratings[i + 1]) {
                right = 1;
            }
            total += Math.max(left[i], right);
        }

        return total;
    }

    public static void main(String[] args) {
        int[] ratings1 = {1, 0, 2};
        int[] ratings2 = {1, 2, 3, 2, 1};
        int[] ratings3 = {1, 3, 4, 5, 2};
        System.out.println(candy(ratings1)); // 5
        System.out.println(candy(ratings2)); // 9
        System.out.println(candy(ratings3)); // 11
    }
}
