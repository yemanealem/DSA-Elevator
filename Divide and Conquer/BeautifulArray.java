import java.util.Arrays;

/*
Problem: Beautiful Array (LeetCode 932)

Given an integer n, return any permutation of the numbers [1, 2, ..., n]
such that for every i < k < j:
    nums[k] * 2 != nums[i] + nums[j]

------------------------------------------------------------
How it works (Divide & Conquer idea):

1. We build the solution starting from a smaller valid array.
2. At each step, we split numbers into:
   - Odd numbers  -> generated using (2*x - 1)
   - Even numbers -> generated using (2*x)

3. Why this works:
   - Odd and even numbers cannot form a valid average together.
   - This guarantees the condition nums[k] * 2 != nums[i] + nums[j].

4. Process:
   - Start with [1]
   - Repeatedly expand:
        odds first, then evens
   - Keep only values <= n

This is conceptually Divide & Conquer because:
   - We build smaller valid arrays
   - Then combine them to form a larger valid array

------------------------------------------------------------
Time Complexity:
   O(n)
   Each number is generated and processed a constant number of times.

Space Complexity:
   O(n)
   We use arrays to store the result.

------------------------------------------------------------
*/

public class BeautifulArray {

    public static int[] beautifulArray(int n) {
        int[] res = new int[n];
        res[0] = 1;

        int size = 1;

        while (size < n) {
            int[] temp = new int[n];
            int idx = 0;

            // Generate odd numbers
            for (int i = 0; i < size; i++) {
                int val = 2 * res[i] - 1;
                if (val <= n) {
                    temp[idx++] = val;
                }
            }

            // Generate even numbers
            for (int i = 0; i < size; i++) {
                int val = 2 * res[i];
                if (val <= n) {
                    temp[idx++] = val;
                }
            }

            res = temp;
            size = idx;
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 5;

        int[] result = beautifulArray(n);

        System.out.println("Beautiful Array for n = " + n + ":");
        System.out.println(Arrays.toString(result));
    }
}
