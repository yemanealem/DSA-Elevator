/*
Online Stock Span Problem - Optimized Stack Solution

Problem:
- For each day's stock price, calculate the span of consecutive days
  where the price was less than or equal to today's price.

Approach:
- Use a monotonic stack (ArrayDeque) storing [price, span].
- For each new price:
    1. Initialize span = 1 (today itself)
    2. While stack top <= current price, pop and add its span
    3. Push [price, span] onto stack
    4. Return span

Trace Example:
Prices: 100, 80, 60, 70, 60, 75, 85

next(100): span=1 → push [100,1] → return 1
next(80) : 80<100 → span=1 → push [80,1] → return 1
next(60) : 60<80  → span=1 → push [60,1] → return 1
next(70) : 70>60 → pop 60 → span=2, 70<80 → push [70,2] → return 2
next(60) : 60<70 → push [60,1] → return 1
next(75) : 75>60 → pop 60 span=2, 75>70 → pop 70 span=4 → push [75,4] → return 4
next(85) : 85>75 → pop 75 span=5, 85>80 → pop 80 span=6 → push [85,6] → return 6

Time Complexity: O(n) amortized
Space Complexity: O(n)
*/

import java.util.ArrayDeque;
import java.util.Deque;

class StockSpanner {
    private Deque<int[]> stack; // Stack stores [price, span]

    public StockSpanner() {
        stack = new ArrayDeque<>(); // ArrayDeque is faster than Stack
    }

    public int next(int price) {
        int span = 1;

        // Merge previous smaller or equal prices
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }

        stack.push(new int[]{price, span});
        return span;
    }
}

public class Main {
    public static void main(String[] args) {
        StockSpanner s = new StockSpanner();

        int[] prices = {100, 80, 60, 70, 60, 75, 85};

        System.out.println("Price -> Span");
        for (int price : prices) {
            int span = s.next(price);
            System.out.println(price + " -> " + span);
        }
    }
}
