/*
LeetCode 904 – Fruit Into Baskets

Question:
You are given an integer array fruits where fruits[i] represents the type of fruit on the i-th tree.
You have two baskets, and each basket can only hold one type of fruit, but you can pick any amount of that fruit.
You want to pick as many fruits as possible in one contiguous segment of trees.
Return the maximum number of fruits you can collect.

Example:
Input: [0,1,2,2]
Output: 3
Explanation: We can pick [1,2,2] -> max 3 fruits.

Approach:
- Use a sliding window keeping track of the last two fruit types.
- Track lastFruitCount = consecutive count of lastFruit.
- If the new fruit is one of the last two, extend window.
- Else, reset window to lastFruitCount + 1 (new fruit starts a new window).
- Update max each step.
*/

public class TotalFruit {

    public int totalFruit(int[] fruits) {
        int ans = 0;
        int lastFruit = -1, secondLastFruit = -1; // last two fruit types
        int lastFruitCount = 0; // consecutive count of lastFruit
        int currentMax = 0; // current window length

        for (int fruit : fruits) {
            if (fruit == lastFruit || fruit == secondLastFruit) {
                // extend the window if fruit is one of the last two types
                currentMax++;
            } else {
                // new fruit type, reset window: last contiguous segment + new fruit
                currentMax = lastFruitCount + 1;
            }

            // update lastFruitCount and track last/secondLast fruit
            if (fruit == lastFruit) {
                lastFruitCount++;
            } else {
                lastFruitCount = 1;
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }

            ans = Math.max(ans, currentMax);
        }

        return ans;
    }

    public static void main(String[] args) {
        TotalFruit solution = new TotalFruit();

        int[] fruits = {0,1,2,2};

        /*
        Trace for [0,1,2,2]:
        Index  Fruit  Last  SecondLast  LastCount  CurrentMax  Max
        0      0      0     -1          1          1           1
        1      1      1      0          1          2           2
        2      2      2      1          1          2           2
        3      2      2      1          2          3           3
        Answer = 3
        */

        System.out.println("Max fruits collected: " + solution.totalFruit(fruits)); // Output: 3

        int[] fruits2 = {1,2,3,2,2};
        /*
        Trace for [1,2,3,2,2]:
        Index  Fruit  Last  SecondLast  LastCount  CurrentMax  Max
        0      1      1     -1          1          1           1
        1      2      2      1          1          2           2
        2      3      3      2          1          2           2
        3      2      2      3          1          2           2
        4      2      2      3          2          3           3
        Answer = 4
        */
        System.out.println("Max fruits collected: " + solution.totalFruit(fruits2)); // Output: 4
    }
}
