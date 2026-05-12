/*
===========================================================
📌 PROBLEM: Integer Break

We want to split n into at least 2 positive integers
to maximize product.

===========================================================
🧠 OPTIMAL IDEA (GREEDY)

Best strategy:
- Break into as many 3s as possible
- Adjust for remainder cases:
    - remainder 0 → all 3s
    - remainder 1 → replace last 3+1 → 2+2
    - remainder 2 → keep 2

===========================================================
⏱ TIME COMPLEXITY:
O(1)

📦 SPACE:
O(1)

===========================================================
*/

public class IntegerBreakOptimized {

    public static int integerBreak(int n) {

        if (n <= 3) return n - 1;

        int product = 1;

        // take as many 3s as possible
        while (n > 4) {
            product *= 3;
            n -= 3;
        }

        // multiply remaining part
        return product * n;
    }

    public static void main(String[] args) {

        System.out.println(integerBreak(10)); 
    }
}